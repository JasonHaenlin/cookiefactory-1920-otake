package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Dough implements Ingredient {
	// @formatter:off
	PLAIN("Plain",0.10),
	CHOCOLATE("Chocolate",0.20),
	PEANUTBUTTER("PeanutButter",0.40),
	OATMEAL("Oatmeal",0.60);
	// @formatter:on

	private final String type;
	private final double price;

	Dough(String type, double price) {
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
