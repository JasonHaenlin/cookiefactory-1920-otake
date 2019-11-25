package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import io.cucumber.java8.En;

public class GetCookiesStepdefs implements En {

    Cookie cookie;
    Cookie cookie2;
    Cookie cookie3;
    RecipeBook factory;
    HashMap<Cookie, Double> map;
    List<Cookie> cookiesget;
    Cookie cookieget;

    public GetCookiesStepdefs() {

        Given("a Cookie Factory with some cookies", () -> {
            this.cookie = Recipe.SOOCHOCOLATE.create();
            this.cookie2 = Recipe.DARKTEMPTATION.create();
            this.cookie3 = Recipe.CHOCOCOLALALA.create();
            this.map = new HashMap<Cookie, Double>();
            this.map.put(cookie, 0.);
            this.map.put(cookie2, 0.);
            this.map.put(cookie3, 0.);
            this.factory = new RecipeBook(map);
            assertEquals(3, factory.getCookies().size());
        });
        When("Billy get all Cookies from Cookies Factory", () -> {
            this.cookiesget = this.factory.getCookies();
        });
        Then("Billy has all Cookies", () -> {
            assertEquals(3, this.cookiesget.size());
        });
        When("Billy get a Cookie from Cookies Factory", () -> {
            this.cookieget = this.factory.getCookie("Dark Temptation");
        });
        Then("Billy has one cookie", () -> {
            assertTrue(this.cookieget.getName().equals("Dark Temptation"));
        });
    }

}
