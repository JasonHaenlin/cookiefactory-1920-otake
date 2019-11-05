package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Topping {
	WHITECHOCOLATE("WhiteChocolate"), MILKCHOCOLATE("MilkChocolate"), MMS("MMs"), REESEBUTTERCUP("ReeseButtercup");

	private final String type;

	Topping(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
