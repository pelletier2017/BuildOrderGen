package game.units;

import game.state.UnitCollection;
import game.stats.GameUnit;
import support.JsonUtils;

import java.util.List;

/*
Holds 1 instance of each Zerg unit in a static variable
// TODO These GameUnit should be immutable, try to make setters private or protected
 */
public class ZergUnits {

    // does it need to be static?
    // try to make it not static, and see how inconvenient it is

    private static GameUnit drone;
    private static GameUnit overlord;
    private static GameUnit queen;
    private static GameUnit zerglingPair;
    private static GameUnit roach;
    private static GameUnit hydralisk;
    private static GameUnit mutalisk;
    // few more units

    private static GameUnit hatchery;
    private static GameUnit spawningPool;
    private static GameUnit spineCrawler;
    private static GameUnit sporeCrawler;
    private static GameUnit roachWarren;
    // few more buildings

    public static void initializeFromJson() {

        List<GameUnit> gameUnits = JsonUtils.readUnits();
        for (GameUnit gameUnit : gameUnits) {
            if (gameUnit.getName().equals("drone")) {
                drone = gameUnit;
            }
            if (gameUnit.getName().equals("overlord")) {
                overlord = gameUnit;
            }
            if (gameUnit.getName().equals("queen")) {
                queen = gameUnit;
            }
            if (gameUnit.getName().equals("zergling")) {
                zerglingPair = gameUnit;
            }
            if (gameUnit.getName().equals("roach")) {
                roach = gameUnit;
            }
            if (gameUnit.getName().equals("hydralisk")) {
                hydralisk = gameUnit;
            }
            if (gameUnit.getName().equals("mutalisk")) {
                mutalisk = gameUnit;
            }

            if (gameUnit.getName().equals("hatchery")) {
                hatchery = gameUnit;
            }
            if (gameUnit.getName().equals("spawning pool")) {
                spawningPool = gameUnit;
            }
            if (gameUnit.getName().equals("spine crawler")) {
                spineCrawler = gameUnit;
            }
            if (gameUnit.getName().equals("spore crawler")) {
                sporeCrawler = gameUnit;
            }
            if (gameUnit.getName().equals("roach warren")) {
                roachWarren = gameUnit;
            }
        }
    }

    public static UnitCollection initialCollection() {
        UnitCollection collection = new UnitCollection();
        collection.addUnit(hatchery);
        collection.addUnit(overlord);
        collection.addUnit(drone, 12);
        return collection;
    }

    public static GameUnit getDrone() {
        return drone;
    }

    public static GameUnit getOverlord() {
        return overlord;
    }

    public static GameUnit getQueen() {
        return queen;
    }

    public static GameUnit getZerglingPair() {
        return zerglingPair;
    }

    public static GameUnit getRoach() {
        return roach;
    }

    public static GameUnit getHydralisk() {
        return hydralisk;
    }

    public static GameUnit getMutalisk() {
        return mutalisk;
    }

    public static GameUnit getHatchery() {
        return hatchery;
    }

    public static GameUnit getSpawningPool() {
        return spawningPool;
    }

    public static GameUnit getSpineCrawler() {
        return spineCrawler;
    }

    public static GameUnit getSporeCrawler() {
        return sporeCrawler;
    }

    public static GameUnit getRoachWarren() {
        return roachWarren;
    }
}
