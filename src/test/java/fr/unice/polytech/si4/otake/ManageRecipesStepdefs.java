package fr.unice.polytech.si4.otake;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.IngredientType;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java.sl.In;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.*;

public class ManageRecipesStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie customCookie;
    RecipeBook recipes;
    HelperRecipe helper;
    ParentCompany company;
    Ingredient ingredient;

    public ManageRecipesStepdefs() {

        Before(() -> {
            recipes = new RecipeBook();
            helper = new HelperRecipe(recipes);
            cookieobj = helper.getSoooChocolate();
            cookieobj2 = helper.getDarkTemptation();
            cookieobj3 = helper.getChocolalala();
        });


        Given("an Ingredient called {string} of type {string}",(String name, String type)->{
            company = new ParentCompany();
            ingredient = new Ingredient(name, 1.0, toIngredient(type));
        });

        And("the ingredient {string} doesn't already exist in company's recipe book",(String s)->{
            assertNull(company.getRecipes().getIngredient(s));
        });

        When("the company add the ingredient to his ingredients",()->{
            company.getRecipes().addIngredient(ingredient);
        });

        Then("a new ingredient {string} is added",(String name)->{
            assertNotNull(company.getRecipes().getIngredient(name));
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

    IngredientType toIngredient(String s){
        switch (s){
            case "cooking":
                return IngredientType.COOKING;
            case "dough":
                return IngredientType.DOUGH;
            case "flavour":
                return IngredientType.FLAVOUR;
            case "mix":
                return IngredientType.MIX;
            case "topping":
                return IngredientType.TOPPING;
        }
        return IngredientType.FLAVOUR;
    }

}
