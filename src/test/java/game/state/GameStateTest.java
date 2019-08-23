package game.state;

import buildorder.BuildOrder;
import buildorder.BuildOrderArrayList;
import game.timeline.SimpleTimeline;
import game.units.ZergUnits;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {

    @BeforeClass
    public static void setupClass() {
        ZergUnits.initializeFromJson();
    }

    @Test
    public void testAddingUnits() {
        GameState gameState = GameStates.initialZerg();

        gameState.addUnit(ZergUnits.getDrone());

        BuildOrder actualBuildOrder = gameState.buildOrder();
        BuildOrder expectedBuildOrder = new BuildOrderArrayList();
        expectedBuildOrder.addStep(0, ZergUnits.getDrone(), 13, 15);

        assertEquals(expectedBuildOrder, actualBuildOrder);
    }

    @Test
    public void testIsBefore() {

    }


}