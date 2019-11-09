package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class orderTest {

    Order order;

    @Before
    public void orderCreation(){
        order = new Order();
    }

    @Test
    public void addCookieTest(){
        Cookie cookie = Recipe.SOOCHOCOLATE.build();
        order.addCookie(cookie);
        assertEquals(cookie, order.getThecookie());
    }

    @Test
    public void setAppointmentDateTest(){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.HOUR_OF_DAY,15);
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
}
