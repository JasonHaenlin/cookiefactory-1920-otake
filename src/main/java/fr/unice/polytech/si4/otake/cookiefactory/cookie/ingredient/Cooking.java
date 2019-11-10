package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Cooking implements Ingredient {
	// @formatter:off
	CRUNCHY("Crunchy", 0.50),
	CHEWY("Chewy", 0.60);
	// @formatter:on

	private final String type;
	private final double price;

	Cooking(String type, double price) {
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
