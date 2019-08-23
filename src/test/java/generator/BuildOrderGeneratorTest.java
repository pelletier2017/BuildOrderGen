package generator;

import buildorder.BuildOrder;
import buildorder.BuildOrderArrayList;
import game.cache.EmptyCache;
import game.cache.GameStateCache;
import game.cache.HashSetCache;
import game.state.GameState;
import game.state.GameStates;
import game.state.UnitCollection;
import game.units.ZergUnits;
import org.junit.Test;
import report.BuildOrderReporter;
import report.TimeAndSupplyFormatter;

import static junit.framework.TestCase.assertEquals;

/*
These timed tests are flaky and shouldn't be relied on with a high degree of accuracy.
 */
public class BuildOrderGeneratorTest {

    @Test
    public void testHashSetCache() {
        BuildOrder buildOrder = smallTest(new HashSetCache());
        BuildOrder expected = smallTestExpected();
        assertEquals(expected, buildOrder);
    }

    @Test
    public void testEmptyCache() {
        BuildOrder buildOrder = smallTest(new EmptyCache());
        BuildOrder expected = smallTestExpected();
        assertEquals(expected, buildOrder);
    }

    private BuildOrder smallTest(GameStateCache cache) {
        ZergUnits.initializeFromJson();

        int secondsToSearch = 8;
        GameState initialZergState = GameStates.initialZerg();

        UnitCollection desiredUnits = new UnitCollection();
        desiredUnits.add(ZergUnits.getHatchery());
        desiredUnits.add(ZergUnits.getOverlord(), 1);
        desiredUnits.add(ZergUnits.getDrone(), 14);

        BuildOrderReporter reporter = new BuildOrderReporter(new TimeAndSupplyFormatter(), System.out);

        BuildOrderGenerator generator = new BuildOrderGenerator(initialZergState, desiredUnits, reporter, cache, secondsToSearch);
        generator.search();

        return reporter.lastBuildOrder();
    }

    private BuildOrder smallTestExpected() {
        BuildOrder buildOrder = new BuildOrderArrayList();
        buildOrder.addStep(0, ZergUnits.getDrone(), 13, 15);
        buildOrder.addStep(0, ZergUnits.getDrone(), 14, 15);
        return buildOrder;
    }
}