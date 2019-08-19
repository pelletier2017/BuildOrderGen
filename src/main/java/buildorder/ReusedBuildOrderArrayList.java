package buildorder;

import game.stats.GameUnit;

import java.util.ArrayList;
import java.util.List;

public class ReusedBuildOrderArrayList implements BuildOrder {
    private static final int MAX_NUM_STEPS = 100;

    private List<BuildOrderStep> steps = new ArrayList<>(MAX_NUM_STEPS);
    private int currentNumSteps = 0;

    public ReusedBuildOrderArrayList() {
        for (int i = 0; i < MAX_NUM_STEPS; i++) {
            steps.add(new BuildOrderStep());
        }
    }

    @Override
    public void addStep(int time, GameUnit gameUnit, int supply, int supplyCap) {
        if (currentNumSteps >= MAX_NUM_STEPS) {
            throw new IllegalArgumentException("Cannot go beyond " + MAX_NUM_STEPS + " using this build order list");
        }

        BuildOrderStep step = steps.get(currentNumSteps);
        step.setTime(time);
        step.setGameUnit(gameUnit);
        step.setSupply(supply);
        step.setSupplyCap(supplyCap);

        currentNumSteps++;
    }

    @Override
    public void popStep() {
        if (currentNumSteps == 0) {
            throw new IllegalStateException("cannot pop from empty list");
        }
        currentNumSteps--;
    }

    @Override
    public int timeOfLastStep() {
        return steps.get(currentNumSteps - 1).getTime();
    }

    @Override
    public List<BuildOrderStep> steps() {
        return steps.subList(0, currentNumSteps);
    }

    @Override
    public BuildOrder copy() {
        ReusedBuildOrderArrayList buildOrder = new ReusedBuildOrderArrayList();
        buildOrder.steps = new ArrayList<>(this.steps);
        buildOrder.currentNumSteps = this.currentNumSteps;
        return buildOrder;
    }

    @Override
    public boolean endsBefore(BuildOrder other) {
        if (other == null) {
            return true;
        }
        return this.timeOfLastStep() < other.timeOfLastStep();
    }
}
