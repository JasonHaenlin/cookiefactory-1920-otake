package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.Calendar;

public class RegisteredCustomer {

	private boolean isSubscribed;
	private String id;
	private float discount;
	private final Calendar registrationDate;
	private int unitsOfCookiesBeforeDiscount;
	public static final int QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT = 30;
	public static final float DEFAULT_DISCOUNT = (float) 0.10;

	public RegisteredCustomer(String id, boolean isSubscribed) {
		this.id = id;
		this.isSubscribed = isSubscribed;
		this.discount = DEFAULT_DISCOUNT;
		this.unitsOfCookiesBeforeDiscount = QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT;
		this.registrationDate = Calendar.getInstance();
	}

	/**
	 * add a discount to the price if eligible
	 *
	 * @param price
	 * @return the new price or the old price if not eligible
	 */
	public float addDiscountIfEligible(float price) {
		float d = (float) 0.;
		if (unitsOfCookiesBeforeDiscount <= 0) {
			d = this.discount;
			unitsOfCookiesBeforeDiscount = QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT;
		}
		return price - (price * d);
	}

	/**
	 * add to this adherent to fidelity program the nb of cookie he purchased
	 *
	 * @param nbCookies purchased
	 */
	public void addCookiePoints(int nbCookies) {
		unitsOfCookiesBeforeDiscount -= nbCookies;
		if (unitsOfCookiesBeforeDiscount < 0)
			unitsOfCookiesBeforeDiscount = 0;
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
	 * @return MAX 30
	 */
	public int getCookiePoints() {
		return QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT - unitsOfCookiesBeforeDiscount;
	}

	public void setSubscribed(boolean subscribed) {
		isSubscribed = subscribed;
	}

	public boolean getSubscribed() {
		return isSubscribed;
	}

}
