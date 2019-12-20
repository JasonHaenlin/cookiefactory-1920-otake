package fr.unice.polytech.si4.otake.cookiefactory.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType.TypeSize;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

/**
 * ExtraProductTest
 */
public class ExtraProductTest {

    ExtraProducts extra;
    HelperRecipe helper;

    @Before
    public void init() {
        helper = new HelperRecipe(new RecipeBook());
        extra = new ExtraProducts();
        extra.addBeverage(new Beverage("bubble tea", 15));
        extra.addBeverage(new Beverage("yogurt", 10));
        extra.addPack(new PackType(TypeSize.SMALL, 15, 10));
        extra.addPack(new PackType(TypeSize.MEDIUM, 20, 10));
        extra.addPack(new PackType(TypeSize.BIG, 30, 10));
    }

    @Test
    public void checkExtraProductTest() {
        assertEquals(3, extra.getPackPossibleSize().size());
    }

    @Test
    public void makePackTest() {
        PackType ptype = extra.getPacks().get(0);
        Beverage bev = extra.getBeverages().get(0);
        Pack expected = new Pack(helper.getChocolalala().getName(), ptype, helper.getChocolalala());
        Pack actual1 = extra.createPackIfPossible(helper.getChocolalala(), ptype.getSize());
        assertEquals(expected, actual1);
        expected.withBeverage(bev);
        Pack actual2 = extra.createPackIfPossible(helper.getChocolalala(), ptype.getSize(), bev);
        assertEquals(expected, actual2);
        assertNull(extra.createPackIfPossible(helper.getDarkTemptation(), 16));
    }
}
