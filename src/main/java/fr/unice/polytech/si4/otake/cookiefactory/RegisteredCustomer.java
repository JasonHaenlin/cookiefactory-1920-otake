package fr.unice.polytech.si4.otake.cookiefactory;

public class RegisteredCustomer {

	private boolean isSubscribed;
	private String id;
	private int unitsBought;
	private float discount;
	private int unitsOfCookiesBeforeDiscount;
	public static final int QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT = 30;
	public static final float DEFAULT_DISCOUNT = (float)0.10;

	public RegisteredCustomer(String id, boolean isSubscribed){
		this.id = id;
		this.isSubscribed = isSubscribed;
		discount = DEFAULT_DISCOUNT;
		unitsOfCookiesBeforeDiscount = QUANTITY_OF_COOKIES_NEEDED_TO_OBTAIN_DISCOUNT;
	}

	/**
	 * apply discount to price of order
	 * @param price of order with taxes
	 */
	public float addDiscount(float price) {
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
<<<<<<< HEAD
	 * Obtain quantity of cookies bought
	 * @return MAX 30
=======
	 *
	 * @param price
>>>>>>> GH-20 add a static field id to increment the id on each new order
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
