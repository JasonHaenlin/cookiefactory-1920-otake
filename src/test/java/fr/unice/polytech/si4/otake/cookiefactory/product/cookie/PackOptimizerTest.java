package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.Beverage;
import fr.unice.polytech.si4.otake.cookiefactory.product.Pack;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackOptimizer;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType.TypeSize;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

public class PackOptimizerTest {

    PackOptimizer packOptimizer;
    final int smallSize = 2;
    final int mediumSize = 4;
    final int bigSize = 6;
    final double smallPrice = 5.0;
    final double mediumPrice = 10.0;
    final double bigPrice = 15.0;
    HelperRecipe helper = new HelperRecipe(new RecipeBook());

    @Before
    public void packOptimizerCreation() {
        packOptimizer = new PackOptimizer();
        packOptimizer.addPackType(new PackType(TypeSize.SMALL, smallSize, smallPrice));
        packOptimizer.addPackType(new PackType(TypeSize.MEDIUM, mediumSize, mediumPrice));
        packOptimizer.addPackType(new PackType(TypeSize.BIG, bigSize, bigPrice));
    }

    @Test
    public void optimizeProductsTest() {
        Map<Product, Integer> products1 = new HashMap<>();
        Cookie cookie1 = helper.getSoooChocolate();
        products1.put(cookie1, (bigSize + smallSize + 1));
        products1 = packOptimizer.optimizeProducts(products1);
        assertEquals(3, products1.size());

        Map<Product, Integer> products2 = new HashMap<>();
        products2.put(cookie1, (bigSize * 2 + mediumSize + 1));
        Pack pack = new Pack(cookie1.getName(), new PackType(TypeSize.BIG, bigSize, bigPrice), cookie1);
        products2 = packOptimizer.optimizeProducts(products2);
        assertEquals(3, products2.size());
        assertEquals(2, (int) products2.get(pack));
        products2.put(cookie1, products2.get(cookie1) + 2);
        products2 = packOptimizer.optimizeProducts(products2);
        assertEquals(4, products2.size());

        Cookie cookie2 = helper.getDarkTemptation();
        Map<Product, Integer> products3 = new HashMap<>();
        products3.put(cookie1, (smallSize + 1));
        products3.put(cookie2, (smallSize + 1));
        products3 = packOptimizer.optimizeProducts(products3);
        assertEquals(4, products3.size());

    }

    @Test
    public void optimizeProductsWithBeverageTest() {
        Map<Product, Integer> products1 = new HashMap<>();
        Cookie cookie1 = helper.getSoooChocolate();
        Beverage bev1 = new Beverage("bubble tea", 10);
        products1.put(cookie1, (bigSize + smallSize + 1));
        products1.put(bev1, 1);
        assertEquals(3, packOptimizer.optimizeProducts(products1).size());
        products1.put(bev1, 5);
        products1 = packOptimizer.optimizeProducts(products1);
        assertEquals(4, products1.size());
        assertEquals(3, (int) products1.get(bev1));
    }
}
