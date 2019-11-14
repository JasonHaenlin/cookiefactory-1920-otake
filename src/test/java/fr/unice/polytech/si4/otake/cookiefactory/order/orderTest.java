package fr.unice.polytech.si4.otake.cookiefactory.order;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;

public class orderTest {

    Order order;

    @Before
    public void orderCreation() {
        order = new Order();
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
        assertTrue("order2 should have a lower id than order1", order1.getId() < order2.getId());
    }
}
