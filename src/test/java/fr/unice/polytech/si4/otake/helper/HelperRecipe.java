package fr.unice.polytech.si4.otake.helper;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Ingredient;

/**
 * Recipe
 */
public class HelperRecipe {

    public final Ingredient whiteChoco;
    public final Ingredient milkChoco;
    public final Ingredient mms;
    public final Ingredient reeseButter;
    public final Ingredient mixed;
    public final Ingredient topped;
    public final Ingredient vanilla;
    public final Ingredient cinnamon;
    public final Ingredient chili;
    public final Ingredient plain;
    public final Ingredient choco;
    public final Ingredient peanutButter;
    public final Ingredient oatmeat;
    public final Ingredient crunchy;
    public final Ingredient chewy;

    public HelperRecipe(RecipeBook rb) {
        RecipeBook li = rb.withDefaultIngredient();
        // Topping
        whiteChoco = li.getIngredient("white chocolate");
        milkChoco = li.getIngredient("milk chocolate");
        mms = li.getIngredient("mms");
        reeseButter = li.getIngredient("reese buttercup");
        // Mixed
        mixed = li.getIngredient("mixed");
        topped = li.getIngredient("topped");
        // Flavour
        vanilla = li.getIngredient("vanilla");
        cinnamon = li.getIngredient("cinnamon");
        chili = li.getIngredient("chili");
        // Dough
        plain = li.getIngredient("plain");
        choco = li.getIngredient("chocolate");
        peanutButter = li.getIngredient("peanut butter");
        oatmeat = li.getIngredient("oatmeat");
        // Cooking
        crunchy = li.getIngredient("crunchy");
        chewy = li.getIngredient("chewy");
    }

    public Cookie getDarkTemptation() {
        return new Cookie("Dark Temptation",
                Arrays.asList(crunchy, choco, mixed, milkChoco, milkChoco, whiteChoco, cinnamon), false);
    }

    public Cookie getSoooChocolate() {
        return new Cookie("Soooo Chocolate", Arrays.asList(chewy, choco, topped, milkChoco, milkChoco, whiteChoco),
                false);
    }

    public Cookie getChocolalala() {
        return new Cookie("Chocolalala",
                Arrays.asList(crunchy, choco, mixed, whiteChoco, whiteChoco, whiteChoco, vanilla), false);
    }
}
