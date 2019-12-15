package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.time.LocalDateTime;
import java.util.Objects;

public class SimpleDate implements Comparable<SimpleDate> {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    /**
     * build a simple date on the current time
     */
    public SimpleDate() {
        LocalDateTime date = LocalDateTime.now();
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.hour = date.getHour();
        this.minute = date.getMinute();
    }

    /**
     * create a simple date of format "00-00-00 13:00"
     *
     * day-month-year hour:min
     *
     * <pre>
     *  {@code new SimpleDate("00-00-00 13:00");}
     * </pre>
     *
     *
     * @param date
     */
    public SimpleDate(String date) {
        String[] dates = date.split(" ");
        String[] infosDay = dates[0].split("-");
        String[] infosHour = dates[1].split(":");
        this.day = Integer.parseInt(infosDay[0]);
        this.month = Integer.parseInt(infosDay[1]);
        this.year = Integer.parseInt(infosDay[2]);
        this.hour = Integer.parseInt(infosHour[0]);
        this.minute = Integer.parseInt(infosHour[1]);
    }

    public SimpleDate(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public boolean before(SimpleDate date) {
        return this.compareTo(date) < 0;
    }

    public boolean after(SimpleDate date) {
        return this.compareTo(date) > 0;
    }

    @Override
    public int compareTo(SimpleDate date) {
        if (this.year < date.year)
            return -1;
        else if (this.year > date.year)
            return 1;

        if (this.month < date.month)
            return -1;
        else if (this.month > date.month)
            return 1;

        if (this.day < date.day)
            return -1;
        else if (this.day > date.day)
            return 1;

        if (this.hour < date.hour)
            return -1;
        else if (this.hour > date.hour)
            return 1;

        if (this.minute < date.minute)
            return -1;
        else if (this.minute > date.minute)
            return 1;

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimpleDate)) {
            return false;
        }
        SimpleDate date = (SimpleDate) obj;
        return this.hashCode() == date.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.day, this.hour, this.minute, this.month, this.year);
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }
}
