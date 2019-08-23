import game.cache.GameStateCache;
import game.cache.HashSetCache;
import game.state.GameState;
import game.state.GameStates;
import game.state.UnitCollection;
import game.stats.GameUnit;
import game.timeline.SimpleTimeline;
import game.timeline.Timeline;
import game.units.ZergUnits;
import generator.BuildOrderGenerator;
import report.BuildOrderReporter;
import report.TimeAndSupplyFormatter;

public class Main {

    public static void main(String[] args) {
        // sets up units statically, maybe better to not do it statically
        ZergUnits.initializeFromJson();

        GameState initialZergState = GameStates.initialZerg();

        UnitCollection desiredUnits = new UnitCollection();
        desiredUnits.add(ZergUnits.getHatchery());
        desiredUnits.add(ZergUnits.getOverlord(), 1);
        desiredUnits.add(ZergUnits.getDrone(), 13);

        BuildOrderReporter reporter = new BuildOrderReporter(new TimeAndSupplyFormatter(), System.out);

        GameStateCache cache = new HashSetCache();

        BuildOrderGenerator generator = new BuildOrderGenerator(initialZergState, desiredUnits, reporter, cache,30);
        generator.search();

    }
}
