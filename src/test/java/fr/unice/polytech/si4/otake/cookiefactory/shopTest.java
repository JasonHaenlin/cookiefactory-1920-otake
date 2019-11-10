package fr.unice.polytech.si4.otake.cookiefactory;

import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;

public class shopTest {

    Shop testShop;

    @Before
    public void shopCreation() {
        testShop = new Shop("Antibes", 5, "Antibes-Cookie", 8, 19);
    }

    @Test
    public void addOrderTest() {
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        Order order = new Order();
        order.addCookie(cookie);
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.set(Calendar.HOUR_OF_DAY, 20);
        // Set the appointment hour to 20h.
        order.setAppointmentDate(appointmentDate);
        testShop.addOrder(order);
        // The shop closes at 19h, so the order shouldn't have been added to the shop.
        assertNull(testShop.getOrder());
        appointmentDate.set(Calendar.HOUR_OF_DAY, 18);
        // Changes the appointment hour to 18h
        order.setAppointmentDate(appointmentDate);
        testShop.addOrder(order);
        // Now the order should have been added to the shop.
        System.out.println(testShop.getOrder());
        // assertEquals(order, testShop.getOrder());
    }

}
