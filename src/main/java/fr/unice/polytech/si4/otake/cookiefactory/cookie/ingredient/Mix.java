package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Mix implements Ingredient {
	// @formatter:off
	MIXED("Mixed", 0.50),
	TOPPED("Topped", 0.50);
	// @formatter:on

	private final String type;
	private final double price;

	Mix(String type, double price) {
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
