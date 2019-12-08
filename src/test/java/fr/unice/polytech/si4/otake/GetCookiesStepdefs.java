package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class GetCookiesStepdefs implements En {

    Cookie cookie;
    Cookie cookie2;
    Cookie cookie3;
    RecipeBook recipeBook;
    HashMap<Cookie, Double> map;
    List<Cookie> cookiesget;
    Cookie cookieget;

    public GetCookiesStepdefs() {

        Given("a Cookie Factory with some cookies", () -> {
            this.recipeBook = new RecipeBook();
            HelperRecipe helper = new HelperRecipe(this.recipeBook);
            this.cookie = helper.getSoooChocolate();
            this.cookie2 = helper.getDarkTemptation();
            this.cookie3 = helper.getChocolalala();
            this.map = new HashMap<Cookie, Double>();
            this.map.put(cookie, 0.);
            this.map.put(cookie2, 0.);
            this.map.put(cookie3, 0.);
            recipeBook.add(cookie);
            recipeBook.add(cookie2);
            recipeBook.add(cookie3);
            assertEquals(3, recipeBook.getCookies().size());
        });
        When("Billy get all Cookies from Cookies Factory", () -> {
            this.cookiesget = this.recipeBook.getCookies();
        });
        Then("Billy has all Cookies", () -> {
            assertEquals(3, this.cookiesget.size());
        });
        When("Billy get a Cookie from Cookies Factory", () -> {
            this.cookieget = this.recipeBook.getCookie("Dark Temptation");
        });
        Then("Billy has one cookie", () -> {
            assertTrue(this.cookieget.getName().equals("Dark Temptation"));
        });
    }

}
