package game.state;

import org.junit.Test;

import static org.junit.Assert.*;

public class SupplyTest {

    @Test
    public void testGetters() {
        Supply supply = new Supply(0, 0);
        assertEquals(0, supply.getCost());
        assertEquals(0, supply.getCap());
    }

    @Test
    public void testSetters() {
        Supply supply = new Supply(0, 0);

        supply.setCost(18);
        supply.setCap(12);

        assertEquals(18, supply.getCost());
        assertEquals(12, supply.getCap());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructInvalidSupply() {
        Supply supply = new Supply(4, 3);
    }

    @Test
    public void testAdd() {
        Supply supply = new Supply(4, 13);
        Supply other = new Supply(20, 30);

        supply.add(other);

        assertEquals(24, supply.getCost());
        assertEquals(43, supply.getCap());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAdd() {
        Supply supply = new Supply(0, 0);
        Supply other = new Supply(2, 0);

        supply.add(other);
    }
}