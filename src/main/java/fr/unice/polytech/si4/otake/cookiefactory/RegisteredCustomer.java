package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;

public class RegisteredCustomer {

	private boolean isSubscribed;
	private String id;
	private double discount;
	private final Calendar registrationDate;
	private int unitsOfPoints;
	public static final int POINT_BEFORE_DISCOUNT = 30;
	public static final double DEFAULT_DISCOUNT = 0.10;

	public RegisteredCustomer(String id, boolean isSubscribed) {
		this.id = id;
		this.isSubscribed = isSubscribed;
		this.discount = DEFAULT_DISCOUNT;
		this.unitsOfPoints = 0;
		this.registrationDate = Calendar.getInstance();
	}

	/**
	 * add a discount to the price if eligible
	 *
	 * @param price
	 * @return the new price or the old price if not eligible
	 */
	public double addDiscountIfEligible(double price) {
		double d = 0.;
		if (unitsOfPoints >= POINT_BEFORE_DISCOUNT) {
			d = this.discount;
			unitsOfPoints -= POINT_BEFORE_DISCOUNT;
		}
		return price - (price * d);
	}

	/**
	 * add to this adherent to fidelity program the nb of cookie he purchased
	 *
	 * @param points purchased
	 */
	public void addPoints(int points) {
		unitsOfPoints += points;
	}

	public void removePoints(int points) {
		unitsOfPoints -= points;
	}

	public String getId() {
		return id;
	}

	/**
	 * @return the registrationDate
	 */
	public Calendar getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Obtain quantity of cookies bought
	 *
	 * @return nb of points
	 */
	public int getCookiePoints() {
		return unitsOfPoints;
	}

	public void setSubscribed(boolean subscribed) {
		isSubscribed = subscribed;
	}

	public boolean getSubscribed() {
		return isSubscribed;
	}

}
