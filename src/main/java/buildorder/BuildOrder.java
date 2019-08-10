package buildorder;

import game.stats.GameUnit;

import java.util.List;

public class BuildOrder {
    private List<BuildOrderStep> steps;

    public void addStep(int time, GameUnit gameUnit) {
        // TODO potential slowdown by creating an object every iteration
        steps.add(new BuildOrderStep(time, gameUnit));
    }

    public void popStep() {
        if (steps.isEmpty()) {
            throw new IllegalStateException("cannot pop from empty list");
        }
        steps.remove(steps.size() - 1);
    }

    private int timeOfLastStep() {
        return steps.get(steps.size() - 1).getTime();
    }

    public boolean endsBefore(BuildOrder other) {
        if (other == null) {
            return true;
        }
        return this.timeOfLastStep() < other.timeOfLastStep();
    }
}
