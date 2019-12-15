package fr.unice.polytech.si4.otake.cookiefactory;

import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

public class RegisteredCustomer {

	private boolean isSubscribed;
	private String id;
	private final SimpleDate registrationDate;
	private int unitsOfPoints;

	public RegisteredCustomer(String id, boolean isSubscribed) {
		this.id = id;
		this.isSubscribed = isSubscribed;
		this.unitsOfPoints = 0;
		this.registrationDate = new SimpleDate();
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
	public SimpleDate getRegistrationDate() {
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
