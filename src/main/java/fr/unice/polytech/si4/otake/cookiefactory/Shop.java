package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;
import java.util.Objects;

public class Shop {

	private String city;
	private float taxes;
	private String name;
	private Order order;
	private Scheduler schedule;
	private static final float DEFAULT_TAXES = (float) 0.30;

	public Shop(String city, String name) {
		this(city, DEFAULT_TAXES, name);
	}

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
	public boolean addOrder(Order order, RegisteredCustomer registerCustomer) {
		order.setPriceWithoutTaxes(registerCustomer.addDiscountIfEligible(order.getPriceWithoutTaxes()));
		if (addOrder(order)) {
			registerCustomer.addCookiePoints(order.getQuantity());
			return true;
		}
		return false;
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
		order.setPriceWithTaxes(order.getPriceWithoutTaxes() * this.taxes + order.getPriceWithoutTaxes());
		return order.getPriceWithTaxes();
	}

	public String getLocation() {
		return city;
	}

	public Order getOrder() {
		return order;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Shop)) {
			return false;
		}
		Shop shop = (Shop) obj;
		return this.hashCode() == shop.hashCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.city, this.name);
	}

}
