import game.cache.EmptyCache;
import game.cache.GameStateCache;
import game.state.GameState;
import game.state.UnitCollection;
import game.units.ZergUnits;
import generator.BuildOrderGenerator;
import report.BuildOrderReporter;
import report.TimeAndSupplyFormatter;
import support.JsonUtils;

public class Main {

    public static void main(String[] args) {
        // sets up units statically, maybe better to not do it statically
        ZergUnits.initializeFromJson();

        GameState initialZergState = GameState.initialZerg();

        UnitCollection desiredUnits = new UnitCollection();
        desiredUnits.addUnit(ZergUnits.getDrone(), 15);

        BuildOrderReporter reporter = new BuildOrderReporter(new TimeAndSupplyFormatter(), System.out);

        GameStateCache cache = new EmptyCache();

        BuildOrderGenerator generator = new BuildOrderGenerator(initialZergState, desiredUnits, reporter, cache);
        generator.generate();

    }
}
