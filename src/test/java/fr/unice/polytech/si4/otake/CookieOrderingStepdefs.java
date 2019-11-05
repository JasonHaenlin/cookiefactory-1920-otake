package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;
import fr.unice.polytech.si4.otake.cookiefactory.Order;
import io.cucumber.java8.En;

public class CookieOrderingStepdefs implements En {

    Cookie cookieobj;
    Order orderobj;

    public CookieOrderingStepdefs() {

        Given("a Cookie {string} and an Order", (String cookie) -> {
            cookieobj = new Cookie(cookie, 2.25, Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED)
                    .withFlavourType(Flavour.CHILI).addTopping(Topping.MILKCHOCOLATE).addTopping(Topping.REESEBUTTERCUP)
                    .cook();
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
