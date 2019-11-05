package fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient;

public enum Cooking {
	CRUNCHY("Crunchy"), CHEWY("Chewy");

	private final String type;

	Cooking(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
