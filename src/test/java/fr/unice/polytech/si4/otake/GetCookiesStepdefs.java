package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactory;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import io.cucumber.java8.En;

public class GetCookiesStepdefs implements En {

    Cookie cookie;
    Cookie cookie2;
    Cookie cookie3;
    CookieFactory factory;
    List<Cookie> list;
    List<Cookie> cookiesget;
    Cookie cookieget;

    public GetCookiesStepdefs() {

        Given("a Cookie Factory with some recipes", () -> {
            this.cookie = Recipe.SOOCHOCOLATE.build();
            this.cookie2 = Recipe.DARKTEMPTATION.build();
            this.cookie3 = Recipe.CHOCOCOLALALA.build();
            this.list = new ArrayList<Cookie>();
            this.list.add(cookie);
            this.list.add(cookie2);
            this.list.add(cookie3);
            this.factory = new CookieFactory(list);
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
