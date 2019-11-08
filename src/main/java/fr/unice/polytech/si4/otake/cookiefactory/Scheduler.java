package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Date;

public class Scheduler {

	private int opening;
	private int closing;

	Scheduler(int opening_, int closing_){
		this.opening = opening_;
		this.closing = closing_;
	}

	public int getOpening(){
		return opening;
	}

	public int getClosing(){
		return closing;
	}

	public void setOpening(int opening){
		this.opening = opening;
	}

	public void setClosing(int closing){
		this.closing = closing;
	}

	public void setSchedule(int opening, int closing){
		this.opening = opening;
		this.closing = closing;
	}

}