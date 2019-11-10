package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Flavour implements Ingredient {
	// @formatter:off
	VANILLA("Vanilla", 0.60),
	CINNAMON("Cinnamon", 0.50),
	CHILI("Chili", 1.00);
	// @formatter:on

	private final String type;
	private final double price;

	Flavour(String type, double price) {
		this.type = type;
		this.price = price;
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
}
