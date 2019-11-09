package fr.unice.polytech.si4.otake.cookiefactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.Scheduler;

public class schedulerTest {

    Scheduler schedule;

    @Before
    public void schedulerCreation() {
        schedule = new Scheduler();
    }

    @Test
    public void setOpeningTest() {
        schedule.setOpening(10);
        assertEquals(10, schedule.getOpening().get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void setClosingTest() {
        schedule.setClosing(17);
        assertEquals(17, schedule.getClosing().get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void setScheduleTest() {
        schedule.setSchedule(10, 17);
        assertEquals(10, schedule.getOpening().get(Calendar.HOUR_OF_DAY));
        assertEquals(17, schedule.getClosing().get(Calendar.HOUR_OF_DAY));
    }
}
