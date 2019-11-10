package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class Order {

	private static int ORDERID = 1;

	private final int id;
	private Calendar appointmentDate;
	private Calendar RetrievedDate;
	private Map<Cookie, Integer> orderContent;
	private float priceWithoutTaxes;
	private float priceWithTaxes;

	public Order() {
		this.id = Order.ORDERID++;
		this.orderContent = new HashMap<>();
	}

	/**
	 *
	 * @param cookie
	 */
	public int addCookie(Cookie cookie) {
		if (this.orderContent.containsKey(cookie)) {
			int t = this.orderContent.get(cookie);
			this.orderContent.put(cookie, t + 1);
			buildPriceWithoutTaxes();
			return 1;
		} else {
			this.orderContent.put(cookie, 1);
		}
		buildPriceWithoutTaxes();
		return 0;
	}

	/**
	 *
	 * @param cookie
	 */
	public boolean removeCookie(Cookie cookie) {
		if (this.orderContent.containsKey(cookie)) {
			int t = this.orderContent.get(cookie);
			if (t > 1) {
				this.orderContent.put(cookie, t - 1);
				buildPriceWithoutTaxes();
				return true;
			} else {
				this.orderContent.remove(cookie);
			}
			buildPriceWithoutTaxes();
			return true;
		} else {
			buildPriceWithoutTaxes();
			return false;
		}
	}

	public float getPriceWithTaxes() {
		return priceWithTaxes;
	}

	void setPriceWithTaxes(float priceWithTaxes) {
		this.priceWithTaxes = priceWithTaxes;
	}

	public void setPriceWithoutTaxes(float priceWithoutTaxes) {
		this.priceWithoutTaxes = priceWithoutTaxes;
	}

	public float getPriceWithoutTaxes() {
		return priceWithoutTaxes;
	}

	/**
	 *
	 * @param date
	 */
	public void setAppointmentDate(Calendar date) {
		this.appointmentDate = date;
	}

	public Calendar getAppointmentDate() {
		return appointmentDate;
	}

	public float getPrice() {
		return priceWithoutTaxes;
	}

	/**
	 * Must call when items in cart changes
	 */
	private void buildPriceWithoutTaxes() {
		priceWithoutTaxes = 0;
		for (Map.Entry<Cookie, Integer> entry : orderContent.entrySet()) {
			priceWithoutTaxes += entry.getKey().getPrice() * entry.getValue();
		}
	}

	/**
	 *
	 * @param status
	 */
	boolean updateStatus(Status status) {
		// TODO - implement Order.updateStatus
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param date
	 */
	boolean hasBeenRetrieved(Calendar date) {
		// TODO - implement Order.hasBeenRetrieved
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the thecookie
	 */
	public Map<Cookie, Integer> getTheOrderContent() {
		return this.orderContent;
	}

	public int getQuantity() {
		int quantity = 0;
		for (Map.Entry<Cookie, Integer> e : orderContent.entrySet()) {
			quantity += e.getValue();
		}
		return quantity;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
