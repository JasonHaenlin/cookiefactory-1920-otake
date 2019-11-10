package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Topping implements Ingredient {
	// @formatter:off
	WHITECHOCOLATE("WhiteChocolate", 0.80),
	MILKCHOCOLATE("MilkChocolate", 0.90),
	MMS("MMs",1.00),
	REESEBUTTERCUP("ReeseButtercup", 1.10);
	// @formatter:on

	private final String type;
	private final double price;

	Topping(String type, double price) {
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
