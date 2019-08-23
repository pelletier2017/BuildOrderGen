package game.state;

import buildorder.BuildOrder;
import buildorder.ReusedBuildOrderArrayList;
import game.stats.GameUnit;
import game.timeline.SimpleTimeline;
import game.timeline.Timeline;
import game.units.ZergUnits;

public class GameStates {

    // static class
    private GameStates() {}

    public static GameState initialZerg() {
        UnitCollection collection = ZergUnits.initialCollection();
        BuildOrder buildOrder = new ReusedBuildOrderArrayList();
        Timeline<GameUnit> timeline = new SimpleTimeline<>();
        return new GameState(collection, buildOrder, timeline);
    }

}
