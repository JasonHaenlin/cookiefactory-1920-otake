package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;

public class Scheduler {

	private final Calendar closing;
	private final Calendar opening;

	Scheduler() {
		this(8, 20);
	}

	Scheduler(int opening, int closing) {
		this.opening = Calendar.getInstance();
		this.closing = Calendar.getInstance();
		this.opening.clear();
		this.closing.clear();
		this.opening.set(Calendar.HOUR_OF_DAY, opening);
		this.closing.set(Calendar.HOUR_OF_DAY, closing);
	}

	public Calendar getOpening() {
		return opening;
	}

	public Calendar getClosing() {
		return closing;
	}

	void setSchedule(int opening, int closing) {
		setClosing(closing);
		setOpening(opening);
	}

	void setOpening(int opening) {
		this.opening.set(Calendar.HOUR_OF_DAY, opening);
	}

	void setClosing(int closing) {
		this.closing.set(Calendar.HOUR_OF_DAY, closing);
	}

}
