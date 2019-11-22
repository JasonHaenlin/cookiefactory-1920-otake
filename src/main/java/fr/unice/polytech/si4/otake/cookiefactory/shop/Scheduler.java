package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.Calendar;

public class Scheduler {

	private final SimpleDate closing;
	private final SimpleDate opening;

	Scheduler() {
		this(8, 20);
	}

	Scheduler(int opening, int closing) {
		this.opening = new SimpleDate("00-00-00 " + opening + ":00");
		this.closing = new SimpleDate("00-00-00 " + closing + ":00");
	}

	/*public SimpleDate getOpening(){
		return opening;
	}

	public SimpleDate getClosing(){
		return closing;
	}*/

	public int getOpeningHour() {
		return opening.getHour();
	}

	public int getClosingHour() {
		return closing.getHour();
	}

	void setSchedule(int opening, int closing) {
		setClosingHour(closing);
		setOpeningHour(opening);
	}

	void setOpeningHour(int opening) {
		this.opening.setHour(opening);
	}

	void setClosingHour(int closing) {
		this.closing.setHour(closing);
	}

}
