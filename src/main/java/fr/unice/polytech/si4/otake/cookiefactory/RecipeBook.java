package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.IngredientType;

public class RecipeBook {

	private final Map<Cookie, Double> cookies;
	private final Set<Ingredient> ingredients;

	/**
	 * new RecipeBook to manage the Cookies recipes
	 */
	public RecipeBook() {
		this.cookies = new HashMap<>();
		this.ingredients = new HashSet<>();
	}

	/**
	 * get a cookie by his name
	 *
	 * @param name
	 * @return the cookie if it exist, null otherwise
	 */
	public Cookie getCookie(String name) {
		for (Map.Entry<Cookie, Double> entry : cookies.entrySet()) {
			Cookie cookie = entry.getKey();
			if (name != null && name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * get all the cookies in the book
	 *
	 * @return a list of cookies
	 */
	public List<Cookie> getCookies() {
		List<Cookie> cookiesreturn = new ArrayList<>(cookies.size());
		for (Map.Entry<Cookie, Double> entry : cookies.entrySet()) {
			if (!entry.getKey().isCustom()) {
				cookiesreturn.add(entry.getKey());
			}
		}
		return cookiesreturn;
	}

	/**
	 * add a new cookie recipe in the book
	 *
	 * @param recipe
	 * @return false if the cookie already exist, true otherwise
	 */
	public boolean addRecipe(Cookie recipe) {
		return this.cookies.putIfAbsent(recipe, 0.0) == null;
	}

	/**
	 * remove a cookie recipe from the book
	 *
	 * @param recipe
	 * @return true if the recipe has been removed, false otherwise
	 */
	public boolean removeRecipe(Cookie recipe) {
		return this.cookies.remove(recipe) != null;
	}

	public Map<Cookie, Double> getStatistic() {
		int soldSum = 0;
		for (Map.Entry<Cookie, Double> entry : cookies.entrySet()) {
			soldSum += entry.getKey().getUnits();
		}
		for (Map.Entry<Cookie, Double> entry : cookies.entrySet()) {
			if (soldSum > 0) {
				Double statistic = ((entry.getKey().getUnits() / (double) soldSum) * 100.);
				cookies.put(entry.getKey(), statistic);
			} else {
				Double statistic = 0.;
				cookies.put(entry.getKey(), statistic);
			}
		}
		return cookies;
	}

	/**
	 * @return the ingredients
	 */
	public List<Ingredient> getIngredients() {
		return new ArrayList<>(this.ingredients);
	}

	public void addIngredients(List<Ingredient> ingredients) {
		for (Ingredient i : ingredients) {
			this.ingredients.add(i);
		}
	}

	public boolean addIngredient(Ingredient ingredient) {
		return this.ingredients.add(ingredient);
	}

	/**
	 * @return a specific ingredient
	 */
	public Ingredient getIngredient(String name) {
		for (Ingredient i : ingredients) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * @return the ingredients with a specific type
	 */
	public List<Ingredient> getIngredientsByType(IngredientType type) {
		return ingredients.stream().filter((i) -> i.getType() == type).collect(Collectors.toList());
	}
}
