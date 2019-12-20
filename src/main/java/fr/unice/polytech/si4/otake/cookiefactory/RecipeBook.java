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
import fr.unice.polytech.si4.otake.cookiefactory.shop.IngredientObservable;
import fr.unice.polytech.si4.otake.cookiefactory.shop.StorageObserver;

public class RecipeBook implements IngredientObservable {

	private final Map<Cookie, Double> cookies;
	private final Set<Ingredient> ingredients;
	private final Set<StorageObserver> obs;

	/**
	 * new RecipeBook to manage the Cookies recipes
	 *
	 * names propositions : Dark Temptation, Soooo Chocolate, Chocolalala, etc
	 */
	public RecipeBook() {
		this.cookies = new HashMap<>();
		this.ingredients = new HashSet<>();
		this.obs = new HashSet<>();
	}

	public RecipeBook withDefaultIngredient() {
		this.ingredients.add(new Ingredient("White Chocolate", 0.80, IngredientType.TOPPING));
		this.ingredients.add(new Ingredient("Milk Chocolate", 0.90, IngredientType.TOPPING));
		this.ingredients.add(new Ingredient("Mms", 1.00, IngredientType.TOPPING));
		this.ingredients.add(new Ingredient("Reese Buttercup", 1.10, IngredientType.TOPPING));
		this.ingredients.add(new Ingredient("Mixed", 0.50, IngredientType.MIX));
		this.ingredients.add(new Ingredient("topped", 0.50, IngredientType.MIX));
		this.ingredients.add(new Ingredient("Vanilla", 0.60, IngredientType.FLAVOUR));
		this.ingredients.add(new Ingredient("Cinnamon", 0.50, IngredientType.FLAVOUR));
		this.ingredients.add(new Ingredient("Chili", 1.00, IngredientType.FLAVOUR));
		this.ingredients.add(new Ingredient("Plain", 0.10, IngredientType.DOUGH));
		this.ingredients.add(new Ingredient("Chocolate", 0.20, IngredientType.DOUGH));
		this.ingredients.add(new Ingredient("Peanut Butter", 0.40, IngredientType.DOUGH));
		this.ingredients.add(new Ingredient("Oatmeal", 0.60, IngredientType.DOUGH));
		this.ingredients.add(new Ingredient("Crunchy", 0.50, IngredientType.COOKING));
		this.ingredients.add(new Ingredient("Chewy", 0.60, IngredientType.COOKING));
		return this;
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
			addIngredient(i);
		}
	}

	public boolean addIngredient(Ingredient i) {
		if (this.ingredients.add(i)) {
			dispatchAddEvent(i);
			return true;
		}
		return false;
	}

	public boolean removeIngredient(Ingredient i) {
		if (this.ingredients.remove(i)) {
			dispatchRemoveEvent(i);
			return true;
		}
		return false;
	}

	/**
	 * @return a specific ingredient
	 */
	public Ingredient getIngredient(String name) {
		name = name.toLowerCase();
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

	public void dispatchAddEvent(Ingredient i) {
		this.obs.forEach(o -> o.addIngredient(i));
	}

	public void dispatchRemoveEvent(Ingredient i) {
		this.obs.forEach(o -> o.removeIngredient(i));
	}

	@Override
	public void addObserver(StorageObserver obs) {
		this.obs.add(obs);
	}

	@Override
	public void removeObserver(StorageObserver obs) {
		this.obs.remove(obs);
	}

	@Override
	public void askForUpdates(StorageObserver obs) {
		this.ingredients.forEach(i -> obs.addIngredient(i));
	}

}
