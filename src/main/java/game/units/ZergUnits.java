package game.units;

import game.stats.GameUnit;

/*
Holds 1 instance of each Zerg unit in a static variable
// TODO These GameUnit should be immutable, try to make setters private or protected
 */
public class ZergUnits {

    // does it need to be static?
    // try to make it not static, and see how inconvenient it is

    public static GameUnit drone = null;

    public static void initializeFromJson() {
        // read a List<GameUnit> and store each value
    }

}
