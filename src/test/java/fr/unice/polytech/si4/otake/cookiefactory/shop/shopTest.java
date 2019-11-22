package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactory;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;

public class shopTest {

    Shop testShop;
    CookieFactory factory;

    @Before
    public void shopCreation() {
        factory = new CookieFactory();
        testShop = new Shop("Antibes", 5, "Antibes-Cookie", 8, 19,factory);
    }

    @Test
    public void addOrderTest() {
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        Order order = new Order();
        order.addCookie(cookie);
        Calendar appointmentDate = Calendar.getInstance();
        appointmentDate.clear();
        appointmentDate.set(Calendar.HOUR_OF_DAY, 20);
        // Set the appointment hour to 20h.
        order.setAppointmentDate(appointmentDate);
        testShop.addOrder(order);
        assertFalse(order.retrieved());
        // The shop closes at 19h, so the order shouldn't have been added to the shop.
        assertNull(testShop.getOrderToRetrieve(0));
        appointmentDate.set(Calendar.HOUR_OF_DAY, 18);
        // Changes the appointment hour to 18h
        order.setAppointmentDate(appointmentDate);
        testShop.addOrder(order);
        testShop.getNextOrder();
        // Now the order should have been added to the shop.
        assertEquals(order, testShop.getOrderToRetrieve(0));
    }

    @Test
    public void getAffluenceTest(){
        Cookie cookie = Recipe.CHOCOCOLALALA.create();

        Order order1 = new Order();
        order1.addCookie(cookie);
        Calendar appointmentDate1 = Calendar.getInstance();
        appointmentDate1.clear();
        appointmentDate1.set(Calendar.HOUR_OF_DAY, 15);
        order1.setAppointmentDate(appointmentDate1);

        Order order2 = new Order();
        order2.addCookie(cookie);
        Calendar appointmentDate2 = Calendar.getInstance();
        appointmentDate2.clear();
        appointmentDate2.set(Calendar.HOUR_OF_DAY, 15);
        order2.setAppointmentDate(appointmentDate2);

        Order order3 = new Order();
        order3.addCookie(cookie);
        Calendar appointmentDate3 = Calendar.getInstance();
        appointmentDate3.clear();
        appointmentDate3.set(Calendar.HOUR_OF_DAY, 17);
        order3.setAppointmentDate(appointmentDate3);

        testShop.addOrder(order1);
        testShop.addOrder(order2);
        testShop.addOrder(order3);

        testShop.getNextOrder();
        testShop.getNextOrder();
        testShop.getNextOrder();

        testShop.retrieved(order1.getId());
        testShop.retrieved(order3.getId());

        assertEquals(1, testShop.getAffluence().get(15).intValue());
        assertEquals(1, testShop.getAffluence().get(17).intValue());

        testShop.retrieved(order2.getId());

        assertEquals(2, testShop.getAffluence().get(15).intValue());
        assertEquals(0, testShop.getAffluence().get(14).intValue());
    }

}
