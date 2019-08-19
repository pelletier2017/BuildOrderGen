package generator;

import buildorder.BuildOrder;
import game.cache.GameStateCache;
import game.state.GameState;
import game.state.UnitCollection;
import game.stats.GameUnit;
import report.BuildOrderReporter;

import java.util.List;

public class BuildOrderGenerator {
    private BuildOrder bestBuildOrder;
    private GameState currentState;
    private UnitCollection desiredUnitCollection;
    private List<GameUnit> unitChoices;
    private GameStateCache gameStateCache;
    private int secondsToSearch;

    private BuildOrderReporter reporter;

    private int waitsInARow;
    private int pathsChecked;

    private static final int MAX_SECONDS_WAITING = 10;

    public BuildOrderGenerator(GameState initialState, UnitCollection desiredUnitCollection, BuildOrderReporter reporter, GameStateCache gameStateCache, int secondsToSearch) {
        this.desiredUnitCollection = desiredUnitCollection;
        this.unitChoices = desiredUnitCollection.unitList();
        this.reporter = reporter;
        this.gameStateCache = gameStateCache;
        this.currentState = initialState;
        this.secondsToSearch = secondsToSearch;

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

        if (waitsInARow > MAX_SECONDS_WAITING || currentState.getSecondsInGame() > secondsToSearch) {
            return;
        }

        if (gameStateCache.contains(currentState)) {
            return;
        }
        gameStateCache.add(currentState);
        pathsChecked++;

        if (currentState.satisfiesDesired(desiredUnitCollection)) {
            updateBestBuildOrder();
            return;
        }

        for (GameUnit gameUnit : unitChoices) {
            if (currentState.canAfford(gameUnit)) {
                currentState.addUnit(gameUnit);
                searchRecurs();
                waitsInARow = 0;
                currentState.undoUnit(gameUnit);
            }
        }

        currentState.nextSecond();
        waitsInARow++;
        searchRecurs();
        currentState.lastSecond();
    }

    private void updateBestBuildOrder() {
        BuildOrder currentBuildOrder = currentState.buildOrder();
        if (currentBuildOrder.endsBefore(bestBuildOrder)) {
            bestBuildOrder = currentBuildOrder.copy();
            reporter.report(bestBuildOrder);
        }
    }
}
