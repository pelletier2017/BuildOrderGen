package generator;

import game.cache.EmptyCache;
import game.cache.GameStateCache;
import game.cache.HashSetCache;
import game.state.GameState;
import game.state.UnitCollection;
import game.units.ZergUnits;
import org.junit.Test;
import report.BuildOrderReporter;
import report.TimeAndSupplyFormatter;

/*
These timed tests are flaky and shouldn't be relied on with a high degree of accuracy.
 */
public class BuildOrderGeneratorTest {

    // 0.120 sec
    @Test
    public void testHashSetCache() {
        simpleTest(new HashSetCache(), 100);
    }

    // 1.7 seconds
    @Test
    public void testEmptyCache() {
        simpleTest(new EmptyCache(), 10);
    }

    private void simpleTest(GameStateCache cache, int secondsToSearch) {
        ZergUnits.initializeFromJson();

        GameState initialZergState = GameState.initialZerg();

        UnitCollection desiredUnits = new UnitCollection();
        desiredUnits.add(ZergUnits.getHatchery());
        desiredUnits.add(ZergUnits.getOverlord(), 2);
        desiredUnits.add(ZergUnits.getDrone(), 18);

        BuildOrderReporter reporter = new BuildOrderReporter(new TimeAndSupplyFormatter(), System.out);

        BuildOrderGenerator generator = new BuildOrderGenerator(initialZergState, desiredUnits, reporter, cache, secondsToSearch);
        generator.search();
    }
}