package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;
import java.util.Date;

public class Scheduler {

	private Calendar opening;
	private Calendar closing;


	public Scheduler(){
		this.opening = Calendar.getInstance();
		this.closing = Calendar.getInstance();
		this.opening.clear();
		this.closing.clear();
		this.opening.set(Calendar.HOUR_OF_DAY, 8);
		this.closing.set(Calendar.HOUR_OF_DAY, 20);
	}

	Scheduler(int opening_, int closing_){
		this.opening = Calendar.getInstance();
		this.closing = Calendar.getInstance();
		this.opening.clear();
		this.closing.clear();
		this.opening.set(Calendar.HOUR_OF_DAY, opening_);
		this.closing.set(Calendar.HOUR_OF_DAY, closing_);
	}

	public Calendar getOpening(){
		return opening;
	}

	public Calendar getClosing(){
		return closing;
	}

	public void setOpening(int opening){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, opening);
		this.opening = cal;
	}

	public void setClosing(int closing){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, closing);
		this.closing = cal;
	}

	public void setSchedule(int opening, int closing){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, opening);
		this.opening = cal;
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.HOUR_OF_DAY, closing);
		this.closing = cal2;
	}

}