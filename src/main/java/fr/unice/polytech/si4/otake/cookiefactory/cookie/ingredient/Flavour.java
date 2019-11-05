package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Flavour {
	VANILLA("Vanilla"), CINNAMON("Cinnamon"), CHILI("Chili");

	private final String type;

	Flavour(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
