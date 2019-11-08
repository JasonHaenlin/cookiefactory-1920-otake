package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Date;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class Order {

	private Date appointmentDate;
	private Date RetrievedDate;
	private String id;
	private Cookie thecookie;

	public Order() {
		this.id = "id";
	}

	/**
	 *
	 * @param cookie
	 */
	public int addCookie(Cookie cookie) {
		this.thecookie = cookie;
		return 1;
	}

	/**
	 *
	 * @param cookie
	 */
	public void removeCookie(Cookie cookie) {
		// TODO - implement Order.removeCookie
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param taxes
	 */
	public float getPriceWithTaxes(float taxes) {
		// TODO - implement Order.getPriceWithTaxes
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param date
	 */
	public void setAppointmentDate(Date date) {
		this.appointmentDate = date;
	}

	public Date getAppointmentDate() { return appointmentDate; }

	public float getPrice() {
		// TODO - implement Order.getPrice
		throw new UnsupportedOperationException();
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
	boolean hasBeenRetrieved(Date date) {
		// TODO - implement Order.hasBeenRetrieved
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the thecookie
	 */
	public Cookie getThecookie() {
		return thecookie;
	}

}
