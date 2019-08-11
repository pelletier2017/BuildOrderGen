package buildorder;

import game.stats.GameUnit;

public interface BuildOrder {
    void addStep(int time, GameUnit gameUnit);
    void popStep();
    boolean endsBefore(BuildOrder other);
    int timeOfLastStep();
}
