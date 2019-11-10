package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.exception.NoToppingRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.exception.TooMuchToppingRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;

public class Cookie {

	private static final int MAX_TOPPINGS = 3;

	private final String name;
	private final Cooking cookingType;
	private final Dough doughType;
	private final Mix mixType;
	private final List<Topping> toppings;
	private Flavour flavourType;
	private double price;

	public Cookie(String name, Cooking cookingType, Dough doughType, Mix mixType, Topping... toppings) {
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null and");
		}
		if (toppings.length < 1) {
			throw new NoToppingRuntimeException();
		}
		if (toppings.length > Cookie.MAX_TOPPINGS) {
			throw new TooMuchToppingRuntimeException(Cookie.MAX_TOPPINGS);
		}
		this.name = name;
		this.cookingType = cookingType;
		this.doughType = doughType;
		this.mixType = mixType;
		this.toppings = Arrays.asList(toppings);
		computePrice();
	}

	private void computePrice() {
		this.price = this.cookingType.getPrice() + this.doughType.getPrice() + this.mixType.getPrice();
		for (Topping t : toppings) {
			this.price += t.getPrice();
		}
	}

	public Cookie withFlavourType(Flavour flavourType) {
		if (flavourType == null) {
			this.price -= this.flavourType.getPrice();
			this.flavourType = null;
			return this;
		}
		this.flavourType = flavourType;
		this.price += this.flavourType.getPrice();
		return this;
	}

	public String getName() {
		return this.name;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * @return the cookingType
	 */
	public Cooking getCookingType() {
		return cookingType;
	}

	/**
	 * @return the doughType
	 */
	public Dough getDoughType() {
		return doughType;
	}

	/**
	 * @return the flavourType
	 */
	public Flavour getFlavourType() {
		return flavourType;
	}

	/**
	 * @return the mixType
	 */
	public Mix getMixType() {
		return mixType;
	}

	public List<Topping> getToppings() {
		return new ArrayList<>(this.toppings);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Cookie)) {
			return false;
		}

		Cookie cookie = (Cookie) obj;
		return this.hashCode() == cookie.hashCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.cookingType, this.doughType, this.mixType, this.toppings, this.flavourType);
	}

}
