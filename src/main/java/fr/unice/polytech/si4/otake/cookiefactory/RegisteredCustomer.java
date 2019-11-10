package fr.unice.polytech.si4.otake.cookiefactory;

public class RegisteredCustomer {

	private boolean isSubscribed;
	private String id;
	private int unitsBought;
	private int discount;
	private int unitsOfCookiesBeforeDiscount;
	private static final int QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT = 30;

	public RegisteredCustomer(String id, boolean isSubscribed){
		this.id = id;
		this.isSubscribed = isSubscribed;
		unitsOfCookiesBeforeDiscount = QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT;
	}

	/**
	 * apply discount to price of order
	 * @param price of order with taxes
	 */
	public int addDiscount(int price) {
		unitsOfCookiesBeforeDiscount = QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT;
		return price-(price*discount);
	}

	/**
	 * add to this adherent to fidelity program the nb of cookie he purchased
	 * @param nbCookies purchased
	 */
	public void addCookiePoints(int nbCookies){
		unitsOfCookiesBeforeDiscount-=nbCookies;
		if(unitsOfCookiesBeforeDiscount<0)
			unitsOfCookiesBeforeDiscount=0;
	}

	public String getId() {
		return id;
	}

	/**
	 * Obtain quantity of cookies bought
	 * @return MAX 30
	 */
	public int getCookiePoints(){
		return QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT - unitsOfCookiesBeforeDiscount;
	}

	public void setSubscribed(boolean subscribed) {
		isSubscribed = subscribed;
	}

	public boolean getSubscribed() {
		return isSubscribed;
	}

}