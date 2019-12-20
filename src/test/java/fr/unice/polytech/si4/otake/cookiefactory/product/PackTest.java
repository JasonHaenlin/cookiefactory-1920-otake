package fr.unice.polytech.si4.otake.cookiefactory.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

/**
 * PackTest
 */
public class PackTest {

    PackType pt1 = new PackType(PackType.SMALL, 15, 15.);
    PackType pt2 = new PackType(PackType.SMALL, 15, 15.);
    PackType pt3 = new PackType(PackType.SMALL, 10, 15.);
    PackType pt4 = new PackType(PackType.BIG, 20, 15.);

    @Test
    public void packTypeTest() {
        assertEquals(pt1, pt2);
        assertNotEquals(pt3, pt2);
        assertNotEquals(pt3, pt4);
    }

    @Test
    public void packTypePack() {
        Pack p1 = new Pack("pack1", pt1);
        Pack p2 = new Pack("pack1", pt2);
        Pack p3 = new Pack("pack2", pt4);
        assertEquals(p1, p2);
        assertNotEquals(p3, p2);
    }

    @Test
    public void packWithBeverageTest() {
        Beverage b1 = new Beverage("coca", 8);
        Beverage b2 = new Beverage("bubble tea!", 40);
        Pack p1 = new Pack("pack1", pt1).withBeverage(b1);
        Pack p2 = new Pack("pack2", pt2).withBeverage(b2);
        Pack p3 = new Pack("pack3", pt3);

        assertEquals(pt1.getPrice() + (b1.getPrice() / 2.), p1.getPrice());
        assertEquals(pt2.getPrice() + (b2.getPrice() / 2.), p2.getPrice());
        assertEquals(pt3.getPrice(), p3.getPrice());
    }
}
