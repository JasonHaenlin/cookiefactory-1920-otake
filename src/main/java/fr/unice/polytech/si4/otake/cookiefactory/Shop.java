package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;

public class Shop {

	private String city;
	private float taxes;
	private String name;
	private Order order;
	private Scheduler schedule;

	/**
	 *
	 * @param city, taxes, name
	 */

	public Shop(String city, float taxes, String name) {
		this(city, taxes, name, 8, 20);
	}

	public Shop(String city, float taxes, String name, int openingTime, int closingTime) {
		this.city = city;
		this.taxes = taxes;
		this.name = name;
		schedule = new Scheduler(openingTime, closingTime);
	}

	public boolean addOrder(Order order) {
		if (checkAppointmentDate(order.getAppointmentDate())) {
			this.order = order;
			return true;
		}
		return false;

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
	 * @return the schedule
	 */
	public Scheduler getSchedule() {
		return schedule;
	}

	/**
	 *
	 * @param date
	 */
	public boolean checkAppointmentDate(Calendar date) {
		return date.after(schedule.getOpening()) && date.before(schedule.getClosing());
	}

	public float getTaxes() {
		return this.taxes;
	}

	public void setTaxes(float taxes) {
		this.taxes = taxes;
	}

	/**
	 *
	 * @param order
	 */
	public float applyTaxes(Order order) {
		order.setPriceWithTaxes(order.getPrice() * taxes + order.getPrice());
		return order.getPriceWithTaxes();
	}

	public String getLocation() {
		// TODO - implement Shop.getLocation
		throw new UnsupportedOperationException();
	}

	public Order getOrder() {
		return order;
	}

}
