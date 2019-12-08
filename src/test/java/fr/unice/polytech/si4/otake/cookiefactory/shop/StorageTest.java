
package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

public class StorageTest {
    Storage stock;
    HelperRecipe helper;

    @Before
    public void storageCreation() {
        RecipeBook rc = new RecipeBook();
        stock = new Storage(rc);
        helper = new HelperRecipe(rc);
    }

    @Test
    public void removeFromStockOneCookieTest() {
        stock.addStock(helper.chewy, 1);
        stock.addStock(helper.choco, 1);
        stock.addStock(helper.topped, 2);
        stock.addStock(helper.milkChoco, 2);
        stock.addStock(helper.whiteChoco, 1);
        Cookie cookie = helper.getSoooChocolate();
        assertTrue(this.stock.removeFromStockIfEnough(cookie, false));
        assertTrue(this.stock.removeFromStockIfEnough(cookie, true));
        assertFalse(this.stock.removeFromStockIfEnough(cookie, true));

    }

    @Test
    public void removeFromStockListOfCookieTest() {
        stock.addStock(helper.chewy, 1);
        stock.addStock(helper.crunchy, 2);
        stock.addStock(helper.choco, 3);
        stock.addStock(helper.mixed, 2);
        stock.addStock(helper.topped, 1);
        stock.addStock(helper.milkChoco, 6);
        stock.addStock(helper.whiteChoco, 3);
        stock.addStock(helper.cinnamon, 1);
        stock.addStock(helper.vanilla, 1);

        Cookie cookie = helper.getSoooChocolate();
        Cookie cookie2 = helper.getDarkTemptation();
        Cookie cookie3 = helper.getDarkTemptation();

        List<Cookie> listCookie = new ArrayList<>();
        listCookie.add(cookie);
        listCookie.add(cookie2);
        listCookie.add(cookie3);

        assertEquals(1, this.stock.removeListFromStockIfEnough(listCookie).size());
        stock.addStock(helper.cinnamon, 1);
        assertEquals(0, this.stock.removeListFromStockIfEnough(listCookie).size());

    }
}
