package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.product.PackOptimizer;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackSize;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PackOptimizerTest {

    PackOptimizer packOptimizer;
    final int smallSize = 2;
    final int mediumSize = 4;
    final int bigSize = 6;
    final double smallPrice = 5.0;
    final double mediumPrice = 10.0;
    final double bigPrice = 15.0;

    @Before
    public void packOptimizerCreation(){
        packOptimizer = new PackOptimizer();
        packOptimizer.addPackType(PackSize.SMALL, smallSize, smallPrice);
        packOptimizer.addPackType(PackSize.MEDIUM, mediumSize, mediumPrice);
        packOptimizer.addPackType(PackSize.BIG, bigSize, bigPrice);
    }

    @Test
    public void optimizeProductsTest(){
        List<Product> products1 = new ArrayList<>();
        for(int i = 0; i < (bigSize + smallSize + 1); ++i){
            products1.add(Recipe.SOOCHOCOLATE.create());
        }
        System.out.println("size = " + products1.size());
        products1 = packOptimizer.optimizeProducts(products1);
        assertEquals(3, products1.size());

        List<Product> products2 = new ArrayList<>();
        for(int i = 0; i < (bigSize*2 + mediumSize + 1); ++i){
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
