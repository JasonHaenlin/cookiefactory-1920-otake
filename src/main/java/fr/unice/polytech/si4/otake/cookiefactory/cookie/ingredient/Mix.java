package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Mix implements Ingredient {
	// @formatter:off
	MIXED(0.50),
	TOPPED(0.50);
	// @formatter:on

	private final double price;

	Mix(double price) {
		this.price = price;
	}

	@Override
	public Ingredient getValue() {
		return this;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
}
