package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import io.cucumber.java8.En;

public class CookieOrderingStepdefs implements En {

    Cookie cookieobj;
    Order orderobj;

    public CookieOrderingStepdefs() {

        Given("a Cookie {string} and an Order", (String cookie) -> {
            cookieobj = Recipe.SOOCHOCOLATE.create();
            orderobj = new Order();
        });

        When("adding the cookie in the order", () -> {
            orderobj.addCookie(cookieobj);
        });

        Then("Cookie is add in the order", () -> {
            assertEquals(1, orderobj.getTheOrderContent().get(cookieobj));
        });
    }

}
