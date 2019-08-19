import game.cache.GameStateCache;
import game.cache.HashSetCache;
import game.state.GameState;
import game.state.UnitCollection;
import game.units.ZergUnits;
import generator.BuildOrderGenerator;
import report.BuildOrderReporter;
import report.TimeAndSupplyFormatter;

public class Main {

    public static void main(String[] args) {
        // sets up units statically, maybe better to not do it statically
        ZergUnits.initializeFromJson();

        GameState initialZergState = GameState.initialZerg();

        UnitCollection desiredUnits = new UnitCollection();
        desiredUnits.add(ZergUnits.getHatchery());
        desiredUnits.add(ZergUnits.getOverlord(), 2);
        desiredUnits.add(ZergUnits.getDrone(), 18);

        BuildOrderReporter reporter = new BuildOrderReporter(new TimeAndSupplyFormatter(), System.out);

        GameStateCache cache = new HashSetCache();

        BuildOrderGenerator generator = new BuildOrderGenerator(initialZergState, desiredUnits, reporter, cache, 10);
        generator.search();

    }
}
