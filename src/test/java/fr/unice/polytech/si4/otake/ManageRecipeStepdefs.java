package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class ManageRecipeStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie customCookie;
    RecipeBook factory;
    HelperRecipe helper;

    public ManageRecipeStepdefs() {

        Given("a Cookie Factory with some recipes", () -> {
            helper = new HelperRecipe(new RecipeBook());
            cookieobj = helper.getSoooChocolate();
            cookieobj2 = helper.getDarkTemptation();
            cookieobj3 = helper.getChocolalala();
            customCookie = new Cookie("custom",
                    Arrays.asList(helper.chewy, helper.choco, helper.topped, helper.reeseButter), true);
            factory = new RecipeBook();
            factory.add(cookieobj);
            assertFalse(factory.add(cookieobj));
            factory.add(cookieobj2);
            factory.add(customCookie);
            assertEquals(2, factory.getCookies().size());
        });

        When("Billy delete one recipe", () -> {
            assertFalse(factory.add(cookieobj2));
            factory.remove(cookieobj2);
        });

        Then("The recipe is not in the recipe list of the cookie factory anymore", () -> {
            assertTrue(factory.add(cookieobj2));
        });

        When("Billy add one recipe", () -> {
            factory.add(cookieobj3);
        });

        Then("The recipe is added in the recipe list of the cookie factory", () -> {
            assertEquals(3, factory.getCookies().size());
        });
    }

}
