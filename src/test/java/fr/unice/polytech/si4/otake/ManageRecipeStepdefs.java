package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactory;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;
import io.cucumber.java8.En;

public class ManageRecipeStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie customCookie;
    CookieFactory factory;

    public ManageRecipeStepdefs() {

        Given("a Cookie Factory with some recipes", () -> {
            cookieobj = Recipe.SOOCHOCOLATE.create();
            cookieobj2 = Recipe.DARKTEMPTATION.create();
            cookieobj3 = Recipe.CHOCOCOLALALA.create();
            customCookie = new Cookie("custom",
                    Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED, Topping.REESEBUTTERCUP), true);
            factory = new CookieFactory();
            factory.addRecipe(cookieobj);
            assertFalse(factory.addRecipe(cookieobj));
            factory.addRecipe(cookieobj2);
            factory.addRecipe(customCookie);
            assertEquals(2, factory.getCookies().size());
        });

        When("Billy delete one recipe", () -> {
            assertFalse(factory.addRecipe(cookieobj2));
            factory.removeRecipe(cookieobj2);
        });

        Then("The recipe is not in the recipe list of the cookie factory anymore", () -> {
            assertTrue(factory.addRecipe(cookieobj2));
        });

        When("Billy add one recipe", () -> {
            factory.addRecipe(cookieobj3);
        });

        Then("The recipe is added in the recipe list of the cookie factory", () -> {
            assertEquals(3, factory.getCookies().size());
        });
    }

}
