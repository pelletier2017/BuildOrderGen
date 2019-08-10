package game.state;

public class GameStates {

    // not intended to be instantiated
    private GameStates() {}

    public GameState initialZergState() {
        UnitCollection collection = new UnitCollection();

        // TODO initial unit
        //collection.addUnits(ZergUnits.HATCHERY, 1);
        //collection.addUnits(ZergUnits.OVERLORD, 1);
        //collection.addUnits(ZergUnits.DRONE, 12);
        return new GameState(0, collection);
    }

}
