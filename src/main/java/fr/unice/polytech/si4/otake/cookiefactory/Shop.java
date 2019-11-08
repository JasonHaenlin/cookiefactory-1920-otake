package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Date;

public class Shop {

	private String city;
	private float taxes;
	private String name;
	private Order theorder;
	private Scheduler schedule;

	/**
	 * 
	 * @param order
	 */

	public Shop(String city, float taxes, String name ){
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		schedule = new Scheduler(8, 20);
	}

	public Shop(String city, float taxes, String name, int opening_time, int closing_time){
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		schedule = new Scheduler(opening_time, closing_time);
	}

	public float addOrder(Order order) {
		if(checkAppointmentDate(order.getAppointmentDate())){
			this.theorder = order;
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 
	 * @param order
	 * @param registerCustomer
	 */
	public float addOrder(Order order, RegisteredCustomer registerCustomer) {
		// TODO - implement Shop.addOrder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 */
	public void getAppointments(Date date) {
		// TODO - implement Shop.getAppointments
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 */
	public boolean checkAppointmentDate(Date date) {
		if(date.getHours() > schedule.getOpening() && date.getHours() < schedule.getClosing()){
			return true;
		} else {
			return false;
		}
	}

	public float getTaxes() {
		return this.taxes;
	}

	/**
	 * 
	 * @param order
	 */
	private float applyTaxes(Order order) {
		// TODO - implement Shop.applyTaxes
		throw new UnsupportedOperationException();
	}

	public String getLocation() {
		// TODO - implement Shop.getLocation
		throw new UnsupportedOperationException();
	}

}