package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class Order {

	private Calendar appointmentDate;
	private Calendar RetrievedDate;
	private String id;
	private Map<Cookie,Integer> orderContent;

	public Order() {
		this.id = "id";
		this.orderContent = new HashMap<Cookie,Integer>();
	}

	/**
	 *
	 * @param cookie
	 */
	public int addCookie(Cookie cookie) {

		if(this.orderContent.containsKey(cookie)){
			int t = this.orderContent.get(cookie);
			this.orderContent.put(cookie, t+1);
			return 1;
		} else {
			this.orderContent.put(cookie, 1);
		}
		return 0;
	}

	/**
	 *
	 * @param cookie
	 */
	public int removeCookie(Cookie cookie) {

		if(this.orderContent.containsKey(cookie)){
			int t = this.orderContent.get(cookie);
			if (t>1) {
				this.orderContent.put(cookie, t-1);
				return 1;
			}else {
				this.orderContent.remove(cookie);
			}
			return 1;
		} else {
			return 0;
		}
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
	public void setAppointmentDate(Calendar date) {
		this.appointmentDate = date;
	}

	public Calendar getAppointmentDate() { return appointmentDate; }

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
	boolean hasBeenRetrieved(Calendar date) {
		// TODO - implement Order.hasBeenRetrieved
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the thecookie
	 */
	public Map<Cookie,Integer> getTheOrderContent() {
		return this.orderContent;
	}

}
