package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Mix {
	MIXED("Mixed"), TOPPED("Topped");

	private final String type;

	Mix(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
