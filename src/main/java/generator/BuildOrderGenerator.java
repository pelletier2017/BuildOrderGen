package generator;

import buildorder.ListBuildOrder;
import game.cache.GameStateCache;
import game.state.GameState;
import game.state.UnitCollection;
import game.stats.GameUnit;
import report.BuildOrderReporter;

import java.util.List;

public class BuildOrderGenerator {
    private ListBuildOrder bestBuildOrder;
    private GameState currentState;
    private UnitCollection desiredUnitCollection;
    private List<GameUnit> unitChoices;
    private GameStateCache gameStateCache;

    private BuildOrderReporter reporter;

    public BuildOrderGenerator(GameState initialState, UnitCollection desiredUnitCollection, BuildOrderReporter reporter, GameStateCache gameStateCache) {
        this.desiredUnitCollection = desiredUnitCollection;

        this.reporter = reporter;
        this.gameStateCache = gameStateCache;
        this.currentState = initialState;
        //this.unitChoices = getThem();

        this.bestBuildOrder = null;
    }

    public void generate() {
        // TODO add check to see if time has passed time limit for program

        if (gameStateCache.contains(currentState)) {
            return;
        }
        gameStateCache.add(currentState);

        if (currentState.satisfiesDesired(desiredUnitCollection)) {
            updateBestBuildOrder();
            return;
        }

        for (GameUnit gameUnit : unitChoices) {
            if (currentState.canAfford(gameUnit)) {
                currentState.addUnit(gameUnit);
                generate();
                currentState.undoUnit(gameUnit);
            }
        }
    }

    private void updateBestBuildOrder() {
        ListBuildOrder currentBuildOrder = currentState.buildOrder();
        if (currentBuildOrder.endsBefore(bestBuildOrder)) {
            bestBuildOrder = currentBuildOrder;
            reporter.report(bestBuildOrder);
        }
    }
}
