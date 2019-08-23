package buildorder;

import game.units.ZergUnits;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ReusedBuildOrderArrayListTest {

    @BeforeClass
    public static void setupClass() {
        ZergUnits.initializeFromJson();
    }

    @Test
    public void testEqualsOtherImplementation() {
        BuildOrder reusedBuildOrderArrayList = new ReusedBuildOrderArrayList();
        reusedBuildOrderArrayList.addStep(0, ZergUnits.getDrone(), 0, 3);

        BuildOrder buildOrderArrayList = new BuildOrderArrayList();
        buildOrderArrayList.addStep(0, ZergUnits.getDrone(), 0, 3);

        assertEquals(reusedBuildOrderArrayList, buildOrderArrayList);
    }

    @Test
    public void testNotEqualsButSameFirstFewSteps() {
        BuildOrder buildOrderA = new ReusedBuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 3);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 1, 3);

        BuildOrder buildOrderB = new ReusedBuildOrderArrayList();
        buildOrderB.addStep(0, ZergUnits.getDrone(), 0, 3);
        buildOrderB.addStep(0, ZergUnits.getDrone(), 1, 3);
        buildOrderB.addStep(1, ZergUnits.getDrone(), 2, 3);

        assertNotEquals(buildOrderA, buildOrderB);
    }

    @Test
    public void testPopStep() {
        BuildOrder buildOrderA = new ReusedBuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 3);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 1, 3);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 3, 3);
        buildOrderA.popStep();

        BuildOrder expected = new ReusedBuildOrderArrayList();
        expected.addStep(0, ZergUnits.getDrone(), 0, 3);
        expected.addStep(0, ZergUnits.getDrone(), 1, 3);

        assertEquals(expected, buildOrderA);
    }

    @Test
    public void testOverwriteOldStep() {
        BuildOrder buildOrderA = new ReusedBuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 3);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 1, 3);

        // undo step
        buildOrderA.addStep(0, ZergUnits.getDrone(), 3, 3);
        buildOrderA.popStep();

        buildOrderA.addStep(0, ZergUnits.getDrone(), 2, 3);

        BuildOrder expected = new ReusedBuildOrderArrayList();
        expected.addStep(0, ZergUnits.getDrone(), 0, 3);
        expected.addStep(0, ZergUnits.getDrone(), 1, 3);
        expected.addStep(0, ZergUnits.getDrone(), 2, 3);

        assertEquals(expected, buildOrderA);
    }
}