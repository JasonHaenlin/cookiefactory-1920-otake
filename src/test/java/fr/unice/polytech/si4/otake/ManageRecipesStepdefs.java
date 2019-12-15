package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class ManageRecipesStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie customCookie;
    RecipeBook recipes;
    HelperRecipe helper;

    public ManageRecipesStepdefs() {

        Before(() -> {
            recipes = new RecipeBook();
            helper = new HelperRecipe(recipes);
            cookieobj = helper.getSoooChocolate();
            cookieobj2 = helper.getDarkTemptation();
            cookieobj3 = helper.getChocolalala();
        });

        Given("I want to update the recipe book", () -> {
            recipes.addRecipe(cookieobj);
            recipes.addRecipe(cookieobj2);
            recipes.addRecipe(cookieobj3);
            customCookie = new Cookie("custom",
                    Arrays.asList(helper.chewy, helper.choco, helper.topped, helper.reeseButter), true);
            assertFalse(recipes.getCookies().isEmpty());
        });

        When("I delete a recipe", () -> {
            recipes.removeRecipe(cookieobj2);
        });

        Then("I can not get that recipe again", () -> {
            assertNull(recipes.getCookie(cookieobj2.getName()));
        });

        When("I add a new recipe", () -> {
            recipes.addRecipe(cookieobj2);
        });

        Then("the recipe book is updated correctly", () -> {
            assertEquals(cookieobj2, recipes.getCookie(cookieobj2.getName()));
            assertEquals(3, recipes.getCookies().size());
        });

        When("I want to add an already added recipe", () -> {
            recipes.addRecipe(cookieobj2);
        });

        Then("the recipe is not added", () -> {
            assertEquals(cookieobj2, recipes.getCookie(cookieobj2.getName()));
            assertEquals(3, recipes.getCookies().size());
        });

        When("I add a custom cookie", () -> {
            recipes.addRecipe(customCookie);
        });

        Then("the recipe book is updated", () -> {
            assertEquals(customCookie, recipes.getCookie(customCookie.getName()));
        });

        But("I can not retrieve it with the other cookies", () -> {
            assertEquals(3, recipes.getCookies().size());
        });
    }

}
