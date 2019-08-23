package generator;

import buildorder.BuildOrder;
import game.cache.GameStateCache;
import game.state.GameState;
import game.state.UnitCollection;
import game.stats.GameUnit;
import game.timeline.Timeline;
import report.BuildOrderReporter;

import java.util.List;

public class BuildOrderGenerator {
    private BuildOrder bestBuildOrder;
    private GameState currentState;
    private UnitCollection desiredUnitCollection;
    private List<GameUnit> unitChoices;
    private GameStateCache gameStateCache;
    private Timeline<GameUnit> timeline;
    private int gameSecondsToSearch;

    private BuildOrderReporter reporter;

    private int waitsInARow;
    private int pathsChecked;

    private static final int MAX_SECONDS_WAITING = 10;

    public BuildOrderGenerator(GameState initialState, UnitCollection desiredUnitCollection, BuildOrderReporter reporter, GameStateCache gameStateCache, int gameSecondsToSearch) {
        this.desiredUnitCollection = desiredUnitCollection;
        this.unitChoices = desiredUnitCollection.unitList();
        this.reporter = reporter;
        this.gameStateCache = gameStateCache;
        this.currentState = initialState;
        this.gameSecondsToSearch = gameSecondsToSearch;

        this.bestBuildOrder = null;
        this.waitsInARow = 0;
        this.pathsChecked = 0;
    }

    public void search() {
        long startTime = System.currentTimeMillis();
        searchRecurs();
        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms to generate");
        System.out.println("paths checked: " + pathsChecked);
    }

    private void searchRecurs() {
        // TODO add check to see if time has passed time limit for program
        pathsChecked++;

        if (pathShouldEnd()) {
            return;
        }

        // check waiting first, so that it can cut paths that wait too long
        currentState.nextSecond();
        waitsInARow++;
        searchRecurs();
        currentState.lastSecond();

        for (GameUnit gameUnit : unitChoices) {
            if (currentState.canAfford(gameUnit)) {
                currentState.addUnit(gameUnit);
                searchRecurs();
                waitsInARow = 0;
                currentState.undoUnit(gameUnit);
            }
        }
    }

    private boolean pathShouldEnd() {
        if (currentState.satisfiesDesired(desiredUnitCollection)) {
            updateBestBuildOrder();
            return true;
        }

        // waiting too long will never be efficient
        // possible that rare cases like saving larva for many roaches could involve waiting but unlikely
        if (waitsInARow > MAX_SECONDS_WAITING) {
            return true;
        }

        // can't search tree infinitely deep
        if (currentState.getSecondsInGame() > gameSecondsToSearch) {
            return true;
        }

        // state seen before
        if (gameStateCache.contains(currentState)) {
            return true;
        }
        gameStateCache.add(currentState);

        return false;
    }

    private void updateBestBuildOrder() {
        BuildOrder currentBuildOrder = currentState.buildOrder();
        if (currentBuildOrder.endsBefore(bestBuildOrder)) {
            // copy because BuildOrder is mutable
            bestBuildOrder = currentBuildOrder.copy();
            reporter.report(bestBuildOrder);
        }
    }
}
