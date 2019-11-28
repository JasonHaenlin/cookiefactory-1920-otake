package fr.unice.polytech.si4.otake.cookiefactory.order;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

public class orderTest {

    Order order;
    Shop shop;
    SimpleDate date;
    RecipeBook factory;

    @Before
    public void orderCreation() {
        factory = new RecipeBook();
        shop = new Shop("Biot", "time", factory);
        date = new SimpleDate("00-00-00 13:00");
    }

    @Test
    public void addCookieTest() {
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        Map<Product, Integer> p = new HashMap<>();
        p.put(cookie, 1);
        order = new Order(p, null, null);
        assertEquals(1, order.getContent().get(cookie));
    }

    @Test
    public void setAppointmentDateTest() {
        SimpleDate appointmentDate1 = new SimpleDate("00-00-00 15:00");
        order = new Order(null, appointmentDate1, "");

        SimpleDate appointmentDate2 = new SimpleDate("00-00-00 13:00");
        assertTrue(appointmentDate2.before(order.getAppointmentDate()));

        appointmentDate2.setHour(17);
        assertTrue(appointmentDate2.after(order.getAppointmentDate()));

        appointmentDate1.setHour(15);
        assertEquals(appointmentDate1, order.getAppointmentDate());
    }

    @Test
    public void orderIdTest() {
        Order order1 = new Order(null, date, "");
        Order order2 = new Order(null, date, "");

        shop.addOrder(order1);
        shop.addOrder(order2);
        assertTrue("order2 should have a lower id than order1", order1.getId() < order2.getId());
    }

    @Test
    public void orderRetrievedTest() {
        Order o1 = new Order(new HashMap<>(), date, "");
        shop.addOrder(o1);
        assertNull(shop.getOrderToRetrieve(0), "order id 1 should be null");
        shop.getNextOrder();
        assertEquals(o1, shop.getOrderToRetrieve(0), "order id 1 should be o1");
        assertTrue("the o1 should be done", shop.retrieved(0));
        assertNull(shop.getOrderToRetrieve(0), "now the order to retrieve should be null");
    }
}
