package buildorder;

import game.stats.GameUnit;

import java.util.ArrayList;
import java.util.List;

public class BuildOrderArrayList implements BuildOrder {
    private List<BuildOrderStep> steps = new ArrayList<>();

    @Override
    public void addStep(int time, GameUnit gameUnit, int supply, int supplyCap) {
        // TODO potential slowdown by creating an object every iteration
        // Possible to allocate an array and re-use it throughout the searches
        steps.add(new BuildOrderStep(time, gameUnit, supply, supplyCap));
    }

    @Override
    public void popStep() {
        if (steps.isEmpty()) {
            throw new IllegalStateException("cannot pop from empty list");
        }
        steps.remove(steps.size() - 1);
    }

    @Override
    public int timeOfLastStep() {
        return steps.get(steps.size() - 1).getTime();
    }

    @Override
    public List<BuildOrderStep> steps() {
        return steps;
    }

    @Override
    public BuildOrder copy() {
        BuildOrderArrayList buildOrderArrayList = new BuildOrderArrayList();
        buildOrderArrayList.steps = new ArrayList<>(this.steps);
        return buildOrderArrayList;
    }

    @Override
    public boolean endsBefore(BuildOrder other) {
        if (other == null) {
            return true;
        }
        return this.timeOfLastStep() < other.timeOfLastStep();
    }
}
