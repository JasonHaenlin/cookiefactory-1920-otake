package fr.unice.polytech.si4.otake.cookiefactory;

public class RegisteredCustomer {

	private boolean isSubscribed;
	private String id;
	private int unitsBought;
	private int discount;
	private int unitsOfCookiesBeforeDiscount;

	public RegisteredCustomer(String id, boolean isSubscribed){
		this.id = id;
		this.isSubscribed = isSubscribed;
	}

	public void subscribeToFideliTyProgram(){
		isSubscribed = true;
	}

	public void unsubscribeFromFidelityProgram(){
		isSubscribed = false;
	}

	/**
	 * 
	 * @param price
	 */
	public int addDiscount(int price) {
		// TODO - implement RegisteredCustomer.addDiscount
		throw new UnsupportedOperationException();
	}

	public String getId() {
		return id;
	}

	public void setSubscribed(boolean subscribed) {
		isSubscribed = subscribed;
	}

	public boolean getSubscribed() {
		return isSubscribed;
	}

}