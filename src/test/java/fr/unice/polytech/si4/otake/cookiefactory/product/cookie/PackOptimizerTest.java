package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.product.PackOptimizer;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType.TypeSize;

public class PackOptimizerTest {

    PackOptimizer packOptimizer;
    final int smallSize = 2;
    final int mediumSize = 4;
    final int bigSize = 6;
    final double smallPrice = 5.0;
    final double mediumPrice = 10.0;
    final double bigPrice = 15.0;

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
        Cookie cookie1 = Recipe.SOOCHOCOLATE.create();
        products1.put(cookie1, (bigSize + smallSize + 1));
        products1 = packOptimizer.optimizeProducts(products1);
        assertEquals(3, products1.size());

        Map<Product, Integer> products2 = new HashMap<>();
        products2.put(cookie1, (bigSize * 2 + mediumSize + 1));
        products2 = packOptimizer.optimizeProducts(products2);
        assertEquals(4, products2.size());

        products2.put(cookie1, products2.get(cookie1)+2);
        products2 = packOptimizer.optimizeProducts(products2);
        assertEquals(5, products2.size());


        Cookie cookie2 = Recipe.DARKTEMPTATION.create();
        Map<Product, Integer> products3 = new HashMap<>();
        products3.put(cookie1, (smallSize+1));
        products3.put(cookie2, (smallSize+1));
        products3 = packOptimizer.optimizeProducts(products3);
        assertEquals(4, products3.size());

    }
}
