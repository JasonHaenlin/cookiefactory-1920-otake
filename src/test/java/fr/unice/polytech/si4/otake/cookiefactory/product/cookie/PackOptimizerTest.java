package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackOptimizer;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType.TypeSize;
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
        List<Product> products1 = new ArrayList<>();
        for (int i = 0; i < (bigSize + smallSize + 1); ++i) {
            products1.add(helper.getSoooChocolate());
        }
        products1 = packOptimizer.optimizeProducts(products1);
        assertEquals(3, products1.size());

        List<Product> products2 = new ArrayList<>();
        for (int i = 0; i < (bigSize * 2 + mediumSize + 1); ++i) {
            products2.add(helper.getSoooChocolate());
        }
        products2 = packOptimizer.optimizeProducts(products2);
        assertEquals(4, products2.size());

        products2.add(helper.getSoooChocolate());
        products2.add(helper.getSoooChocolate());
        products2 = packOptimizer.optimizeProducts(products2);

        assertEquals(5, products2.size());
    }
}
