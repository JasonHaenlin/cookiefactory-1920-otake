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

    Order o1;
    Order o2;
    Order o3;

    @Before
    public void init() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 15);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.HOUR_OF_DAY, 18);
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.HOUR_OF_DAY, 10);
        this.o1 = new Order(null, new SimpleDate("00-00-00 15:00"), null);
        this.o2 = new Order(null, new SimpleDate("00-00-00 18:00"), null);
        this.o3 = new Order(null, new SimpleDate("00-00-00 10:00"), null);
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
