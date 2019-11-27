package fr.unice.polytech.si4.otake.cookiefactory.shop;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;

public class shopTest {

    Shop testShop;
    RecipeBook factory;

    @Before
    public void shopCreation() {
        factory = new RecipeBook();
        testShop = new Shop("Antibes", 5, "Antibes-Cookie", 8, 19, factory);
    }

    // TODO
    @Ignore
    @Test
    public void addOrderTest() {
        // Cookie cookie = Recipe.SOOCHOCOLATE.create();
        // Order order = new Order(null, null, null);
        // order.addCookie(cookie);

        // SimpleDate appointmentDate = new SimpleDate("00-00-00 20:00");
        // // Set the appointment hour to 20h.
        // order.setAppointmentDate(appointmentDate);
        // testShop.addOrder(order);
        // assertFalse(order.retrieved());
        // // The shop closes at 19h, so the order shouldn't have been added to the
        // shop.
        // assertNull(testShop.getOrderToRetrieve(0));
        // appointmentDate.setHour(18);
        // // Changes the appointment hour to 18h
        // order.setAppointmentDate(appointmentDate);
        // testShop.addOrder(order);
        // testShop.getNextOrder();
        // // Now the order should have been added to the shop.
        // assertEquals(order, testShop.getOrderToRetrieve(0));
    }

    // TODO
    @Ignore
    @Test
    public void getAffluenceTest() {
        // Cookie cookie = Recipe.CHOCOCOLALALA.create();

        // Order order1 = new Order();
        // order1.addCookie(cookie);
        // SimpleDate appointmentDate1 = new SimpleDate("00-00-00 15:00");
        // order1.setAppointmentDate(appointmentDate1);

        // Order order2 = new Order();
        // order2.addCookie(cookie);
        // SimpleDate appointmentDate2 = new SimpleDate("00-00-00 15:00");
        // order2.setAppointmentDate(appointmentDate2);

        // Order order3 = new Order();
        // order3.addCookie(cookie);
        // SimpleDate appointmentDate3 = new SimpleDate("00-00-00 17:00");
        // order3.setAppointmentDate(appointmentDate3);

        // testShop.addOrder(order1);
        // testShop.addOrder(order2);
        // testShop.addOrder(order3);

        // testShop.getNextOrder();
        // testShop.getNextOrder();
        // testShop.getNextOrder();

        // testShop.retrieved(order1.getId());
        // testShop.retrieved(order3.getId());

        // assertEquals(1, testShop.getAffluence().get(15).intValue());
        // assertEquals(1, testShop.getAffluence().get(17).intValue());

        // testShop.retrieved(order2.getId());

        // assertEquals(2, testShop.getAffluence().get(15).intValue());
        // assertEquals(0, testShop.getAffluence().get(14).intValue());
    }

}
