package game.state;

import buildorder.BuildOrder;
import buildorder.ReusedBuildOrderArrayList;
import game.stats.Cost;
import game.stats.GameUnit;
import game.stats.Income;
import game.units.ZergUnits;

import java.util.Objects;

public class GameState {

    private int secondsInGame;
    private UnitCollection units;

    private int minerals;
    private int gas;

    private BuildOrder buildOrder;

    public GameState(UnitCollection units) {
        this(0, 50, 0, units);
    }

    public GameState(int secondsInGame, int minerals, int gas, UnitCollection units) {
        this.secondsInGame = secondsInGame;
        this.units = units;
        this.buildOrder = new ReusedBuildOrderArrayList();
        this.minerals = minerals;
        this.gas = gas;
    }

    public void nextSecond() {
        secondsInGame++;

        Income income = units.getIncome();
        minerals += income.getMineralPerSec();
        gas += income.getGasPerSec();
    }

    public void lastSecond() {
        secondsInGame--;

        Income income = units.getIncome();
        minerals -= income.getMineralPerSec();
        gas -= income.getGasPerSec();

        if (minerals < 0 || gas < 0 || secondsInGame < 0) {
            throw new IllegalStateException("Illegal state");
        }
    }

    public void addUnit(GameUnit gameUnit) {
        Cost cost = gameUnit.getCost();
        minerals -= cost.getMinerals();
        gas -= cost.getGas();

        units.add(gameUnit);
        buildOrder.addStep(secondsInGame, gameUnit, units.getSupply().getCost(), units.getSupply().getCap());
    }

    public void undoUnit(GameUnit gameUnit) {
        Cost cost = gameUnit.getCost();
        minerals += cost.getMinerals();
        gas += cost.getGas();

        units.removeUnit(gameUnit);
        buildOrder.popStep();
    }

    public boolean canAfford(GameUnit gameUnit) {
        Cost cost = gameUnit.getCost();
        boolean hasMinerals = minerals >= cost.getMinerals();
        boolean hasGas = gas >= cost.getGas();
        boolean hasSupply = units.getSupply().canAdd(gameUnit.getSupply());

        return hasMinerals && hasGas && hasSupply;
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
        return this.units.contains(desiredUnits);
    }

    public BuildOrder buildOrder() {
        return buildOrder;
    }

    public static GameState initialZerg() {
        UnitCollection collection = ZergUnits.initialCollection();
        return new GameState(collection);
    }

    public int getSecondsInGame() {
        return secondsInGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return secondsInGame == gameState.secondsInGame &&
                minerals == gameState.minerals &&
                gas == gameState.gas &&
                units.equals(gameState.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondsInGame, units, minerals, gas);
    }
}
