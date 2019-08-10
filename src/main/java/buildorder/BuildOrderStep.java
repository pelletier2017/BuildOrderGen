package buildorder;

import game.stats.GameUnit;

public class BuildOrderStep {
    private int time;
    private GameUnit gameUnit;

    public BuildOrderStep(int time, GameUnit gameUnit) {
        this.time = time;
        this.gameUnit = gameUnit;
    }

    public int getTime() {
        return time;
    }

    public GameUnit getGameUnit() {
        return gameUnit;
    }

}
