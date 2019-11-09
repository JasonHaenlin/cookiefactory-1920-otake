package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;

public class Scheduler {

	private final Calendar closing;
	private final Calendar opening;

	Scheduler() {
		this(8, 20);
	}

	Scheduler(int opening_, int closing_) {
		this.opening = Calendar.getInstance();
		this.closing = Calendar.getInstance();
		this.opening.clear();
		this.closing.clear();
		this.opening.set(Calendar.HOUR_OF_DAY, opening_);
		this.closing.set(Calendar.HOUR_OF_DAY, closing_);
	}

	public Calendar getOpening() {
		return opening;
	}

	public Calendar getClosing() {
		return closing;
	}

	void setOpening(int opening) {
		this.opening.set(Calendar.HOUR_OF_DAY, opening);
	}

	void setClosing(int closing) {
		this.closing.set(Calendar.HOUR_OF_DAY, closing);
	}

	void setSchedule(int opening, int closing) {
		setClosing(closing);
		setOpening(opening);
	}

}
