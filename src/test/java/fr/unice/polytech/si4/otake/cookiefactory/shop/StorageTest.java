
package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Topping;

public class StorageTest {
    Storage stock;

    @Before
    public void storageCreation() {
        stock = new Storage();
        stock.addIngredient(Cooking.CHEWY, 1);
        stock.addIngredient(Dough.CHOCOLATE, 1);
        stock.addIngredient(Mix.TOPPED, 2);
        stock.addIngredient(Topping.MILKCHOCOLATE, 2);
        stock.addIngredient(Topping.WHITECHOCOLATE, 1);
    }

    @Test
    public void addOrderTest() {
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        assertTrue(this.stock.removeFromStockIfEnough(cookie));
        assertFalse(this.stock.removeFromStockIfEnough(cookie));

    }
}