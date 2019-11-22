package fr.unice.polytech.si4.otake.cookiefactory.shop;

public class SimpleDate {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public SimpleDate(String date){
        String[] dates = date.split(" ");
        String[] infos_day = dates[0].split("-");
        String[] infos_hour = dates[1].split(":");
        this.day = Integer.parseInt(infos_day[0]);
        this.month = Integer.parseInt(infos_day[1]);
        this.year = Integer.parseInt(infos_day[2]);
        this.hour = Integer.parseInt(infos_hour[0]);
        this.minute = Integer.parseInt(infos_hour[1]);
    }

    public SimpleDate(int day, int month, int year, int hour, int minute){
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public boolean equals(SimpleDate date){
        if(this == date) return true;
        if(date == null) return false;

        return this.compareTo(date) == 0;
    }

    public boolean before(SimpleDate date){
        return this.compareTo(date) == -1;
    }

    public boolean after(SimpleDate date){
        return this.compareTo(date) == 1;
    }

    public int compareTo(SimpleDate date){
        if(this.year < date.year) return -1;
        else if(this.year > date.year) return 1;

        if(this.month < date.month) return -1;
        else if(this.month > date.month) return 1;

        if(this.day < date.day) return -1;
        else if(this.day > date.day) return 1;

        if(this.hour < date.hour) return -1;
        else if(this.hour > date.hour) return 1;

        if(this.minute < date.minute) return -1;
        else if(this.minute > date.minute) return 1;

        return 0;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public int getYear(){
        return this.year;
    }

    public int getMonth(){
        return this.month;
    }

    public int getDay(){
        return this.day;
    }

    public int getHour(){
        return this.hour;
    }

    public int getMinute(){
        return this.minute;
    }
}
