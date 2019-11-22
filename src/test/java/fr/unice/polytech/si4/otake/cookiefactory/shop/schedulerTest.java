package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class schedulerTest {

    Scheduler schedule;

    @Before
    public void schedulerCreation() {
        schedule = new Scheduler();
    }

    @Test
    public void setOpeningTest() {
        schedule.setOpeningHour(10);
        assertEquals(10, schedule.getOpeningHour());
    }

    @Test
    public void setClosingTest() {
        schedule.setClosingHour(17);
        assertEquals(17, schedule.getClosingHour());
    }

    @Test
    public void setScheduleTest() {
        schedule.setSchedule(10, 17);
        assertEquals(10, schedule.getOpeningHour());
        assertEquals(17, schedule.getClosingHour());
    }
}
