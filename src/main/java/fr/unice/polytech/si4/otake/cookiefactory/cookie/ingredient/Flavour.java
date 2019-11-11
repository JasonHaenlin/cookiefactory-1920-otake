package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Flavour implements Ingredient {
	// @formatter:off
	VANILLA(0.60),
	CINNAMON(0.50),
	CHILI(1.00);
	// @formatter:on

	private final double price;

	Flavour(double price) {
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
