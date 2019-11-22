package fr.unice.polytech.si4.otake.cookiefactory.order;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

public class orderTest {

    Order order;
    Shop shop;
    SimpleDate date;
    RecipeBook factory;

    @Before
    public void orderCreation() {
        order = new Order();
        factory = new RecipeBook();
        shop = new Shop("Biot", "time",factory);
        date = new SimpleDate("00-00-00 13:00");
    }

    @Test
    public void addCookieTest() {
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        order.addCookie(cookie);
        assertEquals(1, order.getTheOrderContent().get(cookie));
    }

    @Test
    public void setAppointmentDateTest() {
        SimpleDate appointmentDate1 = new SimpleDate("00-00-00 15:00");
        order.setAppointmentDate(appointmentDate1);

        SimpleDate appointmentDate2 = new SimpleDate("00-00-00 13:00");
        assertTrue(appointmentDate2.before(order.getAppointmentDate()));

        appointmentDate2.setHour(17);
        assertTrue(appointmentDate2.after(order.getAppointmentDate()));

        appointmentDate1.setHour(15);
        assertEquals(appointmentDate1, order.getAppointmentDate());
    }

    @Test
    public void orderIdTest() {
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setAppointmentDate(date);
        order2.setAppointmentDate(date);
        shop.addOrder(order1);
        shop.addOrder(order2);
        assertTrue("order2 should have a lower id than order1", order1.getId() < order2.getId());
    }

    @Test
    public void orderRetrievedTest() {
        Order o1 = new Order();
        o1.setAppointmentDate(date);
        shop.addOrder(o1);
        assertNull(shop.getOrderToRetrieve(0), "order id 1 should be null");
        shop.getNextOrder();
        assertEquals(o1, shop.getOrderToRetrieve(0), "order id 1 should be o1");
        assertTrue("the o1 should be done", shop.retrieved(0));
        assertNull(shop.getOrderToRetrieve(0), "now the order to retrieve should be null");
    }
}
