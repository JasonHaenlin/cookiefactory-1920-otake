package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

public class RecipeBook {

	private Map<Cookie, Double> allCookies;

	/**
	 * new RecipeBook to manage the Cookies recipes
	 */
	public RecipeBook() {
		this(new HashMap<Cookie, Double>());
	}

	/**
	 * new RecipeBook to manage the Cookies recipes
	 */
	public RecipeBook(Map<Cookie, Double> maps) {
		this.allCookies = maps;
	}

	/**
	 * get a cookie by his name
	 *
	 * @param name
	 * @return the cookie if it exist, null otherwise
	 */
	public Cookie getCookie(String name) {
		for (Map.Entry<Cookie, Double> entry : allCookies.entrySet()) {
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
		List<Cookie> cookiesreturn = new ArrayList<>(allCookies.size());
		for (Map.Entry<Cookie, Double> entry : allCookies.entrySet()) {
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
		return this.allCookies.putIfAbsent(recipe, 0.0) == null;
	}

	/**
	 * remove a cookie recipe from the book
	 *
	 * @param recipe
	 * @return true if the recipe has been removed, false otherwise
	 */
	public boolean removeRecipe(Cookie recipe) {
		return this.allCookies.remove(recipe) != null;
	}

	public Map<Cookie, Double> getStatistic() {
		int soldSum = 0;
		for (Map.Entry<Cookie, Double> entry : allCookies.entrySet()) {
			soldSum += entry.getKey().getUnits();
		}
		for (Map.Entry<Cookie, Double> entry : allCookies.entrySet()) {
			if (soldSum > 0) {
				Double statistic = ((entry.getKey().getUnits() / soldSum) * 100.);
				allCookies.put(entry.getKey(), statistic);
			} else {
				Double statistic = 0.;
				allCookies.put(entry.getKey(), statistic);
			}
		}
		return allCookies;
	}
}
