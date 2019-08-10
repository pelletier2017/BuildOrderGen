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
    private UnitCollection unitCollection;
    private GameStateCache gameStateCache;

    private BuildOrderReporter reporter;

    public BuildOrderGenerator(GameState initialState, UnitCollection unitCollection, BuildOrderReporter reporter, GameStateCache gameStateCache) {
        this.unitCollection = unitCollection;
        this.reporter = reporter;
        this.gameStateCache = gameStateCache;
        this.currentState = initialState;

        this.bestBuildOrder = null;
    }

    public void generate(List<GameUnit> unitChoices) {
        // TODO wrap this in try/catch InterruptedException and return bestBuildOrder?

        if (gameStateCache.contains(currentState)) {
            return;
        }
        gameStateCache.add(currentState);

        if (currentState.satisfiesDesired(unitCollection)) {
            updateBestBuildOrder();
            return;
        }

        for (GameUnit gameUnit : unitChoices) {
            if (currentState.canAfford(gameUnit)) {
                currentState.addUnit(gameUnit);
                generate(unitChoices);
                currentState.undoUnit(gameUnit);
            }
        }
    }

    private void updateBestBuildOrder() {
        BuildOrder currentBuildOrder = currentState.buildOrder();
        if (currentBuildOrder.endsBefore(bestBuildOrder)) {
            bestBuildOrder = currentBuildOrder;
            reporter.report(bestBuildOrder);
        }
    }
}
