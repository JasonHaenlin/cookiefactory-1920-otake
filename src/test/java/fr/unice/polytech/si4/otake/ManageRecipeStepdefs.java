package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import fr.unice.polytech.si4.otake.cookiefactory.CookieFactory;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import io.cucumber.java8.En;

public class ManageRecipeStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    CookieFactory factory;

    public ManageRecipeStepdefs() {

        Given("a Cookie Factory with some recipes", () -> {
            cookieobj = Recipe.SOOCHOCOLATE.create();
            cookieobj2 = Recipe.DARKTEMPTATION.create();
            cookieobj3 = Recipe.CHOCOCOLALALA.create();
            factory = new CookieFactory();
            factory.addRecipe(cookieobj);
            assertFalse(factory.addRecipe(cookieobj));
            factory.addRecipe(cookieobj2);
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
