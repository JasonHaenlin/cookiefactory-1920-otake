package fr.unice.polytech.si4.otake.cookiefactory.shop;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Ingredient;

/**
 * StorageObserver
 */
public interface StorageObserver {
    void addIngredient(Ingredient ingredient);

    void removeIngredient(Ingredient ingredient);
}
