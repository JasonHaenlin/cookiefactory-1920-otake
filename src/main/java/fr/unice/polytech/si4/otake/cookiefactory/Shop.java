package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Date;

public class Shop {

	private String city;
	private float taxes;
	private String name;

	/**
	 * 
	 * @param order
	 */
	public float addOrder(Order order) {
		// TODO - implement Shop.addOrder
		throw new UnsupportedOperationException();
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
		// TODO - implement Shop.checkAppointmentDate
		throw new UnsupportedOperationException();
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