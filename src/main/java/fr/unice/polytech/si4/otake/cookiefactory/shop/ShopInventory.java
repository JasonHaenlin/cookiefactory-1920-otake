package fr.unice.polytech.si4.otake.cookiefactory.shop;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.shop.exception.NotEnoughQuantityIngredientException;

/**
 * used to manage the shop's ingredients for making cookies
 */
public class ShopInventory {
    private Map<Ingredient, Integer> ingredients;

    ShopInventory() {
        ingredients = new HashMap<>();
    }

    ShopInventory(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * fill the shop's stock from this ingredient
     * 
     * @param ingredient to fill
     * @param quantity   to fill
     */
    void fillIngredient(Ingredient ingredient, int quantity) {
        if (ingredients.containsKey(ingredient)) {
            ingredients.put(ingredient, (ingredients.get(ingredient) + quantity));
        } else {
            ingredients.put(ingredient, quantity);
        }
    }

    /**
     * use an ingredient in the shop's inventory, use to make a cookie
     * 
     * @param ingredient needed for the cookie
     * @param quantity   needed for the cookie
     */
    void useIngredient(Ingredient ingredient, int quantity) {
        if (ingredients.containsKey(ingredient)) {
            if (ingredients.get(ingredient) >= quantity)
                ingredients.put(ingredient, (ingredients.get(ingredient) - quantity));
        }
        throw new NotEnoughQuantityIngredientException();
    }

    int quantityOf(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient))
            return ingredients.get(ingredient);
        return 0;
    }

    boolean contains(Ingredient ingredient) {
        return ingredients.containsKey(ingredient);
    }
}
