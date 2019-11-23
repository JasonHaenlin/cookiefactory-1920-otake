package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.IngredientChecker;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Topping;

/**
 * ListTypeCheckerTest
 */
public class IngredientCheckerTest {

    IngredientChecker checker;

    @Before
    public void init() {
        List<Class<? extends Ingredient>> li = new ArrayList<>();
        li.add(Cooking.class);
        li.add(Flavour.class);
        li.add(Dough.class);
        this.checker = new IngredientChecker(li);
    }

    @Test
    public void ingredientVerifyTypeTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(Cooking.CRUNCHY);
        list.add(Flavour.CHILI);

        assertFalse(checker.verify(list));

        list.add(Dough.CHOCOLATE);

        assertTrue(checker.verify(list));
    }

    @Test
    public void ingredientVerifyTypeOversizeTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(Cooking.CRUNCHY);
        list.add(Flavour.CHILI);

        assertFalse(checker.verify(list));

        list.add(Dough.CHOCOLATE);

        assertTrue(checker.verify(list));

        list.add(Cooking.CHEWY);

        assertFalse(checker.verify(list));
    }

    @Test
    public void flavourQuantitytest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(Cooking.CRUNCHY);
        list.add(Dough.CHOCOLATE);

        assertFalse(checker.isQuantityExcessive(Flavour.class, list, 1));

        list.add(Flavour.CHILI);

        assertFalse(checker.isQuantityExcessive(Flavour.class, list, 1));

        list.add(Flavour.CHILI);

        assertTrue(checker.isQuantityExcessive(Flavour.class, list, 1));
    }

    @Test
    public void ingredientVerifyToppingTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(Cooking.CRUNCHY);
        list.add(Cooking.CRUNCHY);
        list.add(Flavour.CHILI);
        assertFalse("0 > 2 error", checker.isQuantityExcessive(Topping.class, list, 2));

        list.add(Topping.WHITECHOCOLATE);
        list.add(Topping.WHITECHOCOLATE);

        assertFalse("2 > 2 error", checker.isQuantityExcessive(Topping.class, list, 2));

        list.add(Topping.REESEBUTTERCUP);

        assertTrue("3 > 2 error", checker.isQuantityExcessive(Topping.class, list, 2));
    }

}
