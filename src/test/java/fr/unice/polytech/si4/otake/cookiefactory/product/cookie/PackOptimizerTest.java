package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.product.PackOptimizer;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackSize;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackSize.PackType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PackOptimizerTest {

    PackOptimizer packOptimizer;
    final int smallSize = 2;
    final int mediumSize = 4;
    final int bigSize = 6;

    @Before
    public void packOptimizerCreation() {
        packOptimizer = new PackOptimizer();
<<<<<<< HEAD
        packOptimizer.addPackType(PackSize.SMALL, smallSize);
        packOptimizer.addPackType(PackSize.MEDIUM, mediumSize);
        packOptimizer.addPackType(PackSize.BIG, bigSize);
=======
        packOptimizer.addPackType(new PackSize(PackType.SMALL, 15), smallPrice);
        packOptimizer.addPackType(new PackSize(PackType.MEDIUM, 20), mediumPrice);
        packOptimizer.addPackType(PackType.BIG, bigSize, bigPrice);
>>>>>>> GH-47 Fixed tests
    }

    @Test
    public void optimizeProductsTest() {
        List<Product> products1 = new ArrayList<>();
        for (int i = 0; i < (bigSize + smallSize + 1); ++i) {
            products1.add(Recipe.SOOCHOCOLATE.create());
        }
        System.out.println("size = " + products1.size());
        products1 = packOptimizer.optimizeProducts(products1);
        assertEquals(3, products1.size());

        List<Product> products2 = new ArrayList<>();
<<<<<<< HEAD
        for(int i = 0; i < (bigSize*2 + mediumSize + 1); ++i){
=======
        for (int i = 0; i < (bigSize * 2 + mediumSize + 1); ++i) {
>>>>>>> GH-47 Fixed tests
            products2.add(Recipe.SOOCHOCOLATE.create());
        }
        products2 = packOptimizer.optimizeProducts(products2);
        assertEquals(4, products2.size());

        products2.add(Recipe.SOOCHOCOLATE.create());
        products2.add(Recipe.SOOCHOCOLATE.create());
        products2 = packOptimizer.optimizeProducts(products2);

        assertEquals(5, products2.size());
    }
}
