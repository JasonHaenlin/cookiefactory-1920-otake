package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

/**
 * ListTypeCheckerTest
 */
public class IngredientCheckerTest {

    IngredientChecker checker;
    HelperRecipe helper = new HelperRecipe(new RecipeBook());

    @Before
    public void init() {
        List<IngredientType> li = new ArrayList<>();
        li.add(IngredientType.COOKING);
        li.add(IngredientType.FLAVOUR);
        li.add(IngredientType.DOUGH);
        this.checker = new IngredientChecker(li);
    }

    @Test
    public void ingredientVerifyTypeTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(helper.crunchy);
        list.add(helper.chili);

        assertFalse(checker.verify(list));

        list.add(helper.choco);

        assertTrue(checker.verify(list));
    }

    @Test
    public void ingredientVerifyTypeOversizeTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(helper.crunchy);
        list.add(helper.chili);

        assertFalse(checker.verify(list));

        list.add(helper.choco);

        assertTrue(checker.verify(list));

        list.add(helper.chewy);

        assertFalse(checker.verify(list));
    }

    @Test
    public void flavourQuantitytest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(helper.crunchy);
        list.add(helper.choco);

        assertFalse(checker.isQuantityExcessive(IngredientType.FLAVOUR, list, 1));

        list.add(helper.chili);

        assertFalse(checker.isQuantityExcessive(IngredientType.FLAVOUR, list, 1));

        list.add(helper.chili);

        assertTrue(checker.isQuantityExcessive(IngredientType.FLAVOUR, list, 1));
    }

    @Test
    public void ingredientVerifyToppingTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(helper.crunchy);
        list.add(helper.crunchy);
        list.add(helper.chili);
        assertFalse("0 > 2 error", checker.isQuantityExcessive(IngredientType.TOPPING, list, 2));

        list.add(helper.whiteChoco);
        list.add(helper.whiteChoco);

        assertFalse("2 > 2 error", checker.isQuantityExcessive(IngredientType.TOPPING, list, 2));

        list.add(helper.reeseButter);

        assertTrue("3 > 2 error", checker.isQuantityExcessive(IngredientType.TOPPING, list, 2));
    }

}
