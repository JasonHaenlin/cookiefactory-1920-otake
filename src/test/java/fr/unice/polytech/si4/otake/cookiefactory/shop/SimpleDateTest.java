package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * SimpleDateTest
 */
public class SimpleDateTest {

    @Test
    public void creationDate1Test() {
        SimpleDate date = new SimpleDate("05-12-19 13:00");
        assertEquals(5, date.getDay());
        assertEquals(12, date.getMonth());
        assertEquals(19, date.getYear());
        assertEquals(13, date.getHour());
        assertEquals(0, date.getMinute());
    }

    @Test
    public void creationDate2Test() {
        SimpleDate date = new SimpleDate(4, 3, 15, 2, 6);
        assertEquals(4, date.getDay());
        assertEquals(3, date.getMonth());
        assertEquals(15, date.getYear());
        assertEquals(2, date.getHour());
        assertEquals(6, date.getMinute());
    }

    @Test
    public void compateDateTest() {
        SimpleDate date1 = new SimpleDate("01-01-01 01:00");
        SimpleDate date2 = new SimpleDate("01-01-01 01:00");

        assertEquals(date1, date2);
        assertEquals(0, date1.compareTo(date2));
        date1.setDay(2);
        assertEquals(1, date1.compareTo(date2));
        date2.setMonth(5);
        assertEquals(-1, date1.compareTo(date2));
        date1.setMonth(5);
        date2.setDay(2);
        date1.setHour(5);
        assertEquals(1, date1.compareTo(date2));
        date2.setHour(5);
        date2.setMinute(59);
        assertEquals(-1, date1.compareTo(date2));

    }
}
