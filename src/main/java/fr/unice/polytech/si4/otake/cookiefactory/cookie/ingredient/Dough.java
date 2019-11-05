package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Dough {
	PLAIN("Plain"), CHOCOLATE("Chocolate"), PEANUTBUTTER("PeanutButter"), OATMEAL("Oatmeal");

	private final String type;

	Dough(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
