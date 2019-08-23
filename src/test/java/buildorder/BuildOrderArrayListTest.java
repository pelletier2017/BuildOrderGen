package buildorder;

import game.stats.GameUnit;
import game.units.ZergUnits;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuildOrderArrayListTest {

    @BeforeClass
    public static void setupClass() {
        ZergUnits.initializeFromJson();
    }

    @Test
    public void testEquals() {
        BuildOrder buildOrderA = new BuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 2, 4);

        BuildOrder buildOrderB = new BuildOrderArrayList();
        buildOrderB.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderB.addStep(0, ZergUnits.getDrone(), 2, 4);

        assertEquals(buildOrderA, buildOrderB);
    }

    @Test
    public void testNotEqualSupply() {
        BuildOrder buildOrderA = new BuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 2, 4);

        BuildOrder buildOrderB = new BuildOrderArrayList();
        buildOrderB.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderB.addStep(0, ZergUnits.getDrone(), 3, 4);

        assertNotEquals(buildOrderA, buildOrderB);
    }

    @Test
    public void testNotEqualTime() {
        BuildOrder buildOrderA = new BuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderA.addStep(1, ZergUnits.getDrone(), 2, 4);

        BuildOrder buildOrderB = new BuildOrderArrayList();
        buildOrderB.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderB.addStep(0, ZergUnits.getDrone(), 2, 4);

        assertNotEquals(buildOrderA, buildOrderB);
    }

    @Test
    public void testNotEqualSupplyCap() {
        BuildOrder buildOrderA = new BuildOrderArrayList();
        buildOrderA.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderA.addStep(0, ZergUnits.getDrone(), 2, 4);

        BuildOrder buildOrderB = new BuildOrderArrayList();
        buildOrderB.addStep(0, ZergUnits.getDrone(), 0, 4);
        buildOrderB.addStep(0, ZergUnits.getDrone(), 2, 5);

        assertNotEquals(buildOrderA, buildOrderB);
    }

    @Test
    public void testEqualsOtherImplementation() {
        BuildOrder buildOrderArrayList = new BuildOrderArrayList();
        buildOrderArrayList.addStep(0, ZergUnits.getDrone(), 0, 3);

        BuildOrder reusedBuildOrderArrayList = new ReusedBuildOrderArrayList();
        reusedBuildOrderArrayList.addStep(0, ZergUnits.getDrone(), 0, 3);

        assertEquals(buildOrderArrayList, reusedBuildOrderArrayList);
    }
}