package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Cooking implements Ingredient {
	// @formatter:off
	CRUNCHY(0.50),
	CHEWY(0.60);
	// @formatter:on
	private final double price;

	Cooking(double price) {
		this.price = price;
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public Ingredient getValue() {
		return this;
	}

}
