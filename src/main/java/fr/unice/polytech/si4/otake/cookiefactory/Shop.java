package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;

public class Shop {

	private String city;
	private float taxes;
	private String name;
	private Order theorder = null;
	private Scheduler schedule;

	/**
	 * 
	 * @param city, taxes, name
	 */

	public Shop(String city, float taxes, String name) {
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		schedule = new Scheduler(8, 20);
	}

	public Shop(String city, float taxes, String name, int opening_time, int closing_time) {
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		schedule = new Scheduler(opening_time, closing_time);
	}

	public float addOrder(Order order) {
		if (checkAppointmentDate(order.getAppointmentDate())) {
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
	public void getAppointments(Calendar date) {
		// TODO - implement Shop.getAppointments
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 */
	public boolean checkAppointmentDate(Calendar date) {
		if (date.after(schedule.getOpening()) && date.before(schedule.getClosing())) {
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

	public Order getTheorder() {
		return theorder;
	}

}