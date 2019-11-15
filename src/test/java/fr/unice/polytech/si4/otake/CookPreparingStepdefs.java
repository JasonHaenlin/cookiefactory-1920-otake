package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.Status;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import io.cucumber.java8.En;

/**
 * CookPreparingStepdefs
 */
public class CookPreparingStepdefs implements En {

    Shop shop;
    Order o;
    Order o1;
    Order o2;

    public CookPreparingStepdefs() {
        Given("orders in a shop", () -> {
            shop = new Shop("city", "name");
            o1 = new Order();
            o2 = new Order();
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.HOUR_OF_DAY, 14);
            Calendar cal2 = Calendar.getInstance();
            cal2.clear();
            cal2.set(Calendar.HOUR_OF_DAY, 12);
            o1.setAppointmentDate(cal);
            o2.setAppointmentDate(cal2);
            shop.addOrder(o1);
            shop.addOrder(o2);
        });
        When("the cook want to prepare the first order", () -> {
            o = shop.getCurrentOrder();
        });
        Then("the cook can retrieve the first order in the queue", () -> {
            assertEquals(o2, o);
        });
        When("the cook finish the preparation", () -> {
            o = shop.getNextOrder();
        });
        Then("the cook can go to the next order", () -> {
            assertEquals(o1, o);
        });
        When("an order is retrieve by his id", () -> {
            assertTrue(shop.retrieved(o2.getId()));
        });
        Then("the order is archive in the queue", () -> {
            assertEquals(o1, shop.getCurrentOrder());
            assertTrue(o2.getStatus() == Status.RETRIEVED);
        });
    }
}
