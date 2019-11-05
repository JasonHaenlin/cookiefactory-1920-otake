package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import io.cucumber.java8.En;

public class CookieOrderingStepdefs implements En {

    Cookie cookieobj;
    Order orderobj;

    public CookieOrderingStepdefs() {

        Given("a Cookie {string} and an Order", (String cookie) -> {
            cookieobj = Recipe.SOOCHOCOLATE.build();
            orderobj = new Order();
        });

        When("adding the cookie in the order", () -> {
            orderobj.addCookie(cookieobj);
        });

        Then("Cookie is add in the order", () -> {
            assertEquals(cookieobj.getName(), orderobj.getThecookie().getName());
        });
    }

}
