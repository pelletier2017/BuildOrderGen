package game.state;

import buildorder.ListBuildOrder;
import game.stats.GameUnit;
import game.units.ZergUnits;

public class GameState {

    protected int secondsInGame;
    protected UnitCollection units;

    private ListBuildOrder buildOrder;

    public GameState(UnitCollection units) {
        this(0, units);
    }

    public GameState(int secondsInGame, UnitCollection units) {
        this.secondsInGame = secondsInGame;
        this.units = units;
        this.buildOrder = new ListBuildOrder();
    }

    public void addUnit(GameUnit gameUnit) {
        System.out.println("ADD UNIT not implemented");
        // also add to build order
    }

    public void undoUnit(GameUnit gameUnit) {
        System.out.println("UNDO UNIT not implemented");
        // also remove from build order
    }

    public boolean canAfford(GameUnit gameUnit) {
        return true;
    }

    protected void checkValidCreation(GameUnit gameUnit) {
        if (!canAfford(gameUnit)) {
            throw new IllegalArgumentException(String.format("Cannot afford new unit %s", gameUnit));
        }
    }

    public boolean isBefore(GameState other) {
        return this.secondsInGame < other.secondsInGame;
    }

//    public GameState deepCopy() {
//        return new GameState(secondsInGame, new Supply(supply.getCost(), supply.getCap()), unitCollection.copy());
//    }

    public boolean satisfiesDesired(UnitCollection desiredUnits) {
        // TODO iterate over gamestate and see if it has all the desired units
        return true;
    }

    public ListBuildOrder buildOrder() {
        return buildOrder;
    }

    public static GameState initialZerg() {
        UnitCollection collection = ZergUnits.initialCollection();
        return new GameState(collection);
    }
}
