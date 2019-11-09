package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;
import io.cucumber.java8.En;

public class CreateCookiesStepdefs implements En {

    Cookie cookie;

    public CreateCookiesStepdefs() {
        Given("a list of ingredients for a cookie named {string}", (String name) -> {
            this.cookie = new Cookie(name, 2.25, Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED)
                    .addTopping(Topping.REESEBUTTERCUP);
        });
        When("the cook want to add {string} topping", (String topping) -> {
            this.cookie.addTopping(Topping.valueOf(topping));
        });
        Then("the cookie is updated with {string}", (String topping) -> {
            Boolean test = false;
            for (Topping t : this.cookie.getToppings()) {
                if (Topping.valueOf(topping).equals(t)) {
                    test = true;
                }
            }
            assertTrue(test);
        });
        When("the cook add a {string} flavour in the cookie", (String flavour) -> {
            this.cookie.withFlavourType(Flavour.valueOf(flavour));
        });
        Then("the Cookie include the {string} flavour", (String flavour) -> {
            assertEquals(Flavour.valueOf(flavour), this.cookie.getFlavourType());
        });
    }

}