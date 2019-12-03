
package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Topping;

public class StorageTest {
    Storage stock;

    @Before
    public void storageCreation() {
        stock = new Storage();
    }

    @Test
    public void removeFromStockOneCookieTest() {
        stock.addIngredient(Cooking.CHEWY, 1);
        stock.addIngredient(Dough.CHOCOLATE, 1);
        stock.addIngredient(Mix.TOPPED, 2);
        stock.addIngredient(Topping.MILKCHOCOLATE, 2);
        stock.addIngredient(Topping.WHITECHOCOLATE, 1);
        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        assertTrue(this.stock.removeFromStockIfEnough(cookie, false));
        assertTrue(this.stock.removeFromStockIfEnough(cookie, true));
        assertFalse(this.stock.removeFromStockIfEnough(cookie, true));

    }
    @Test
    public void removeFromStockListOfCookieTest() {
        stock.addIngredient(Cooking.CHEWY, 1);
        stock.addIngredient(Cooking.CRUNCHY, 2);
        stock.addIngredient(Dough.CHOCOLATE, 3);
        stock.addIngredient(Mix.MIXED, 2);
        stock.addIngredient(Mix.TOPPED, 1);
        stock.addIngredient(Topping.MILKCHOCOLATE, 6);
        stock.addIngredient(Topping.WHITECHOCOLATE, 3);
        stock.addIngredient(Flavour.CINNAMON, 1);
        stock.addIngredient(Flavour.VANILLA, 1);
        

        Cookie cookie = Recipe.SOOCHOCOLATE.create();
        Cookie cookie2 = Recipe.DARKTEMPTATION.create();
        Cookie cookie3 = Recipe.DARKTEMPTATION.create();

        List<Cookie> listCookie = new ArrayList<>();
        listCookie.add(cookie);
        listCookie.add(cookie2);
        listCookie.add(cookie3);


        assertEquals(1,this.stock.removeListFromStockIfEnough(listCookie).size());
        stock.addIngredient(Flavour.CINNAMON, 1);
        assertEquals(0,this.stock.removeListFromStockIfEnough(listCookie).size());


    }
}