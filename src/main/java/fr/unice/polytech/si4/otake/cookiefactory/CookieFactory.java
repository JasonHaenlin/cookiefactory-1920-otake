package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;

public class CookieFactory {

	private List<Cookie> allCookies;

	public CookieFactory() {
		this(new ArrayList<Cookie>());
	}

	public CookieFactory(List<Cookie> list) {
		this.allCookies = list;
	}

	public Cookie getCookie(String type) {
		// TODO - implement CookieFactory.getCookie
		throw new UnsupportedOperationException();
	}

	public Cookie[] getCookies() {
		// TODO - implement CookieFactory.getCookies
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param recipe
	 */
	boolean addRecipe(Cookie recipe) {
		// TODO - implement CookieFactory.addRecipe
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param recipe
	 */
	boolean removeRecipe(Cookie recipe) {
		// TODO - implement CookieFactory.removeRecipe
		throw new UnsupportedOperationException();
	}

}
