package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;

public class Cookie {

	private final int maxToppings = 3;

	private final double price;
	private final String name;
	private final Cooking cookingType;
	private final Dough doughType;
	private final Mix mixType;
	private final List<Topping> toppings = new ArrayList<>();
	private Flavour flavourType;

	public Cookie(String name, double price, Cooking cookingType, Dough doughType, Mix mixType) {
		if (name == null || price < 0) {
			throw new IllegalArgumentException("name can not be null and price can not be less than 1");
		}
		this.name = name;
		this.price = price;
		this.cookingType = cookingType;
		this.doughType = doughType;
		this.mixType = mixType;
	}

	public Cookie withFlavourType(Flavour flavourType) {
		this.flavourType = flavourType;
		return this;
	}

	public Cookie addTopping(Topping topping) {
		if (this.toppings.size() < this.maxToppings) {
			this.toppings.add(topping);
		}
		return this;
	}

	public Cookie cook() {
		if (this.toppings.size() < 1) {
			// default toppings
			this.addTopping(Topping.MilkChocolate);
		}
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

}
