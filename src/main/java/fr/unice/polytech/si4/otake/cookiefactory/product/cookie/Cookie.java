package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.ProductType;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.exception.IngredientNotPresentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.exception.TooMuchIngredientRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.IngredientChecker;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Topping;

public class Cookie extends Product {

	private static final int MAX_TOPPINGS = 3;
	private final List<Ingredient> ingredients;
	private final IngredientChecker checker;
	private final Boolean isCustom;

	/**
	 * create a new Cookie with a name and the specific ingredients Need at least 1
	 * Cooking, 1 Dough and 1 Mix. You can add from 0 to 3 Toppings with a optional
	 * Flavour.
	 *
	 * @param name
	 * @param ingredients
	 */
	public Cookie(String name, List<Ingredient> ingredients, Boolean isCustom) {
		super(name, isCustom ? ProductType.CUSTOM_COOKIE : ProductType.ON_MENU_COOKIE);
		if (name == null) {
			throw new IllegalArgumentException(NAME_CAN_NOT_BE_NULL);
		}
		this.checker = new IngredientChecker(Arrays.asList(Cooking.class, Dough.class, Mix.class));
		if (!this.checker.verify(ingredients)) {
			throw new IngredientNotPresentRuntimeException();
		}
		if (this.checker.isQuantityExcessive(Topping.class, ingredients, Cookie.MAX_TOPPINGS)) {
			throw new TooMuchIngredientRuntimeException("Topping", Cookie.MAX_TOPPINGS);
		}
		if (this.checker.isQuantityExcessive(Flavour.class, ingredients, 1)) {
			throw new TooMuchIngredientRuntimeException("Flavour", 1);
		}
		this.isCustom = isCustom;
		this.ingredients = ingredients;
		this.price = computePrice();
	}

	@Override
	protected final double computePrice() {
		double m = 0;
		for (Ingredient i : ingredients) {
			m += i.getPrice();
		}
		if (this.isCustom) {
			m = m * 1.20;
		}
		return m;
	}

	public double getPrice() {
		return this.price;
	}

	public Boolean isCustom() {
		return this.isCustom;
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
		return Objects.hash(this.ingredients);
	}

}
