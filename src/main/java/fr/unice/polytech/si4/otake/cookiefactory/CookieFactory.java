package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class CookieFactory {

	private Map<Cookie, Float> allCookies;

	public CookieFactory() {
		this(new HashMap<Cookie, Float>());
	}

	public CookieFactory(HashMap<Cookie, Float> maps) {
		this.allCookies = maps;
	}

	public Cookie getCookie(String name) {
		for (Map.Entry<Cookie, Float> entry : allCookies.entrySet()) {
			Cookie cookie = entry.getKey();
			if (cookie.getName() == name) {
				return cookie;
			}
		}
		return null;
	}

	public List<Cookie> getCookies() {
		List<Cookie> cookiesreturn = new ArrayList<>();
		for (Map.Entry<Cookie, Float> entry : allCookies.entrySet()) {
			cookiesreturn.add(entry.getKey());
		}
		return cookiesreturn;
	}

	/**
	 *
	 * @param recipe
	 */
	public boolean addRecipe(Cookie recipe) {
		if (this.allCookies.containsKey(recipe)) {
			return false;
		} else {
			this.allCookies.put(recipe, (float) 0.0);
		}
		return true;

	}

	/**
	 *
	 * @param recipe
	 */
	public boolean removeRecipe(Cookie recipe) {
		if (this.allCookies.containsKey(recipe)) {
			this.allCookies.remove(recipe);
			return true;
		} else {
			return false;
		}
	}

	public Map<Cookie, Float> getStatistic() {
		int soldSum = 0;
		for (Map.Entry<Cookie, Float> entry : allCookies.entrySet()) {
			soldSum += entry.getKey().getUnitsSold();
		}
		for (Map.Entry<Cookie, Float> entry : allCookies.entrySet()) {
			if (soldSum > 0) {
				Float statistic = (float) ((entry.getKey().getUnitsSold() / (float) soldSum) * 100);
				allCookies.put(entry.getKey(), statistic);
			} else {
				Float statistic = (float) 0;
				allCookies.put(entry.getKey(), statistic);
			}

		}
		return allCookies;
	}

}
