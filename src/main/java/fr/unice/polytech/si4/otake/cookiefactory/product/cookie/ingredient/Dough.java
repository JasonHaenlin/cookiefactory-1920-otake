package fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient;

public enum Dough implements Ingredient {
	// @formatter:off
	PLAIN(0.10),
	CHOCOLATE(0.20),
	PEANUTBUTTER(0.40),
	OATMEAL(0.60);
	// @formatter:on

	private final double price;

	Dough(double price) {
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
