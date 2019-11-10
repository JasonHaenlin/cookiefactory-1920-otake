package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class CookieFactory {

	private Map<Cookie, Integer> allCookies;

	public CookieFactory() {
		this(new HashMap<Cookie, Integer>());
	}

	public CookieFactory(HashMap<Cookie, Integer> maps) {
		this.allCookies = maps;
	}

	public Cookie getCookie(String name) {
		for (Map.Entry<Cookie, Integer> entry : allCookies.entrySet()) {
			Cookie cookie = entry.getKey();
			if (cookie.getName() == name) {
				return cookie;
			}
		}
		return null;
	}

	public List<Cookie> getCookies() {
		List<Cookie> cookiesreturn = new ArrayList<>();
		for (Map.Entry<Cookie, Integer> entry : allCookies.entrySet()) {
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
			this.allCookies.put(recipe, 0);
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

}
