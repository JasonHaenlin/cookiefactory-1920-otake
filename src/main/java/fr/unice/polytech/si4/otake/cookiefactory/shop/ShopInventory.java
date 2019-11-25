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
        Integer inStock = ingredients.get(ingredient);
        if (inStock == null) {
            inStock = 0;
        }
        ingredients.put(ingredient, inStock + quantity);
    }

    /**
     * use an ingredient in the shop's inventory, use to make a cookie
     *
     * @param ingredient needed for the cookie
     * @param quantity   needed for the cookie
     */
    void useIngredient(Ingredient ingredient, int quantity) {
        Integer inStock = ingredients.get(ingredient);
        if (inStock != null && inStock >= quantity) {
            ingredients.put(ingredient, (ingredients.get(ingredient) - quantity));
        }
        throw new NotEnoughQuantityIngredientException();
    }

    int quantityOf(Ingredient ingredient) {
        Integer inStock = ingredients.get(ingredient);
        return inStock != null ? inStock : 0;
    }
}
