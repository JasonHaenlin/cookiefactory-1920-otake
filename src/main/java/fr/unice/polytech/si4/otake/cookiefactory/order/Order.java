package fr.unice.polytech.si4.otake.cookiefactory.order;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class Order {

	private static int orderId = 1;

	private final int id;
	private final Map<Cookie, Integer> orderContent;

	private Calendar appointmentDate;
	private float priceWithoutTaxes;
	private float priceWithTaxes;
	private Status status;

	/**
	 * create a new order when cookies can be add
	 */
	public Order() {
		this.id = Order.orderId++;
		this.orderContent = new HashMap<>();
	}

	/**
	 * add a cookie in the basked
	 *
	 * @param cookie
	 * @return the number of cookies for the given cookie added
	 */
	public int addCookie(Cookie cookie) {
		Integer t = this.orderContent.get(cookie);
		if (t == null) {
			t = 0;
		}
		this.orderContent.put(cookie, t + 1);
		buildPriceWithoutTaxes();
		return t + 1;
	}

	/**
	 * remove a cookie from the basket
	 *
	 * @param cookie
	 * @return true if the cookie has been deleted, false otherwise
	 */
	public boolean removeCookie(Cookie cookie) {
		Integer t = this.orderContent.get(cookie);
		if (t == null) {
			return false;
		}
		if (t > 1) {
			this.orderContent.put(cookie, t - 1);
		} else {
			this.orderContent.remove(cookie);
		}
		buildPriceWithoutTaxes();
		return true;
	}

	private void buildPriceWithoutTaxes() {
		priceWithoutTaxes = 0;
		for (Map.Entry<Cookie, Integer> entry : orderContent.entrySet()) {
			priceWithoutTaxes += entry.getKey().getPrice() * entry.getValue();
		}
	}

	private void updateCookiesSolds() {
		for (Map.Entry<Cookie, Integer> entry : orderContent.entrySet()) {
			entry.getKey().incrementUnit(entry.getValue());
		}

	}

	/**
	 * Update the status of the order
	 *
	 * @param status : READY, RETRIEVED, IN_PROGRESS, WAITING
	 */
	public void updateStatus(Status status) {
		if (status == Status.WAITING) {
			updateCookiesSolds();
		}
		this.status = status;
	}

	boolean hasBeenRetrieved() {
		return this.status == Status.RETRIEVED;
	}

	/**
	 * retrieve the content of the order
	 *
	 * @return a map of cookies with the quantities
	 */
	public Map<Cookie, Integer> getTheOrderContent() {
		return this.orderContent;
	}

	/**
	 * get the quantity of cookies in the basket
	 *
	 * @return the number of cookies
	 */
	public int getQuantity() {
		int quantity = 0;
		for (Map.Entry<Cookie, Integer> e : orderContent.entrySet()) {
			quantity += e.getValue();
		}
		return quantity;
	}

	public float getPriceWithTaxes() {
		return priceWithTaxes;
	}

	public void setPriceWithTaxes(float priceWithTaxes) {
		this.priceWithTaxes = priceWithTaxes;
	}

	public void setPriceWithoutTaxes(float priceWithoutTaxes) {
		this.priceWithoutTaxes = priceWithoutTaxes;
	}

	public float getPriceWithoutTaxes() {
		return priceWithoutTaxes;
	}

	public void setAppointmentDate(Calendar date) {
		this.appointmentDate = date;
	}

	public Calendar getAppointmentDate() {
		return appointmentDate;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}
}
