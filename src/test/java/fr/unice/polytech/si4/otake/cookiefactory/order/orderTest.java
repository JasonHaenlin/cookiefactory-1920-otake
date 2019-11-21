package fr.unice.polytech.si4.otake.cookiefactory.order;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactory;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

public class orderTest {

    Order order;
    Shop shop;
    Calendar cal;
    CookieFactory factory;

    @Before
    public void orderCreation() {
        order = new Order();
        factory = new CookieFactory();
        shop = new Shop("Biot", "time",factory);
        cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.HOUR_OF_DAY, 13);
    }

    @Test
    public void addCookieTest() {
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        order.addCookie(cookie);
        assertEquals(1, order.getTheOrderContent().get(cookie));
    }

    @Test
    public void setAppointmentDateTest() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.HOUR_OF_DAY, 15);
        order.setAppointmentDate(cal);

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();
        cal2.set(Calendar.HOUR_OF_DAY, 13);
        assertTrue(cal2.before(order.getAppointmentDate()));

        cal2.set(Calendar.HOUR_OF_DAY, 17);
        assertTrue(cal2.after(order.getAppointmentDate()));

        cal2.set(Calendar.HOUR_OF_DAY, 15);
        assertEquals(cal2, order.getAppointmentDate());

    }

    @Test
    public void orderIdTest() {
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setAppointmentDate(cal);
        order2.setAppointmentDate(cal);
        shop.addOrder(order1);
        shop.addOrder(order2);
        assertTrue("order2 should have a lower id than order1", order1.getId() < order2.getId());
    }

    @Test
    public void orderRetrievedTest() {
        Order o1 = new Order();
        o1.setAppointmentDate(cal);
        shop.addOrder(o1);
        assertNull(shop.getOrderToRetrieve(0), "order id 1 should be null");
        shop.getNextOrder();
        assertEquals(o1, shop.getOrderToRetrieve(0), "order id 1 should be o1");
        assertTrue("the o1 should be done", shop.retrieved(0));
        assertNull(shop.getOrderToRetrieve(0), "now the order to retrieve should be null");
    }
}
