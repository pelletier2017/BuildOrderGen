package buildorder;

import game.stats.GameUnit;

public class BuildOrderStep {
    private int time;
    private GameUnit gameUnit;
    private int supply;
    private int supplyCap;

    public BuildOrderStep() {
        this(0, null, 0, 0);
    }

    public BuildOrderStep(int time, GameUnit gameUnit, int supply, int supplyCap) {
        this.time = time;
        this.gameUnit = gameUnit;
        this.supply = supply;
        this.supplyCap = supplyCap;
    }

    public int getTime() {
        return time;
    }

    public GameUnit getGameUnit() {
        return gameUnit;
    }

    public int getSupply() {
        return supply;
    }

    public int getSupplyCap() {
        return supplyCap;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setGameUnit(GameUnit gameUnit) {
        this.gameUnit = gameUnit;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public void setSupplyCap(int supplyCap) {
        this.supplyCap = supplyCap;
    }
}
