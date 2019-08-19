package game.state;

import game.units.ZergUnits;
import org.junit.Before;

import static org.junit.Assert.*;

public class UnitCollectionTest {

    @Before
    public void setUp() {
        ZergUnits.initializeFromJson();
    }

    public void testAddUnits() {
        UnitCollection collection = new UnitCollection();
        collection.add(ZergUnits.getDrone());


    }

}