package fr.unice.polytech.si4.otake.cookiefactory.order;

import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.Before;

/**
 * OrderQueueTest
 */
public class OrderQueueTest {

    Order o1 = new Order();
    Order o2 = new Order();
    Order o3 = new Order();

    @Before
    public void init() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 15);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 18);
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.HOUR_OF_DAY, 10);

        SimpleDate date1 = new SimpleDate("00-00-00 15:00");
        SimpleDate date2 = new SimpleDate("00-00-00 18:00");
        SimpleDate date3 = new SimpleDate("00-00-00 10:00");
        o1.setAppointmentDate(date1);
        o2.setAppointmentDate(date2);
        o3.setAppointmentDate(date3);
    }

    @Test
    public void orderQueueOrderTest() {
        OrderQueue od = new OrderQueue();
        assertTrue(od.add(o1));
        assertEquals(o1, od.peek());
        od.add(o2);
        assertEquals(o1, od.peek());
        od.add(o3);
        assertEquals(o3, od.peek());
        assertEquals(o1, od.next());
        assertEquals(o1, od.peek());
        assertEquals(o3, od.toRetrieve().get(0));
        assertTrue(od.archive(o2));
        assertEquals(o2, od.getArchive().pollFirst());
        assertTrue(od.archive(o3));
        assertEquals(o1, od.peek());
        assertFalse(od.add(null));
        assertFalse(od.archive(null));
    }
}
