package buildorder;

import game.stats.GameUnit;

import java.util.List;

public interface BuildOrder {
    void addStep(int time, GameUnit gameUnit, int supply, int supplyCap);
    void popStep();
    boolean endsBefore(BuildOrder other);
    int timeOfLastStep();
    List<BuildOrderStep> steps();
    BuildOrder copy();
}
