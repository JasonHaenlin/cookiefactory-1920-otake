package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Topping implements Ingredient {
	// @formatter:off
	WHITECHOCOLATE(0.80),
	MILKCHOCOLATE(0.90),
	MMS(1.00),
	REESEBUTTERCUP(1.10);
	// @formatter:on

	private final double price;

	Topping(double price) {
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
