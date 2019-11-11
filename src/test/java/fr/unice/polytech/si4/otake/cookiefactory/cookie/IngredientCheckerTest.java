package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Ingredient;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.IngredientChecker;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;

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
        list.add(Cooking.CRUNCHY);
        list.add(Flavour.CHILI);

        assertFalse(checker.verify(list));

        list.add(Dough.CHOCOLATE);

        assertTrue(checker.verify(list));
    }

    @Test
    public void ingredientVerifyToppingTest() {
        List<Ingredient> list = new ArrayList<>();
        list.add(Cooking.CRUNCHY);
        list.add(Cooking.CRUNCHY);
        list.add(Flavour.CHILI);
        assertFalse("0 > 2 error", checker.isQuantityAbused(Topping.class, list, 2));

        list.add(Topping.WHITECHOCOLATE);
        list.add(Topping.WHITECHOCOLATE);

        assertFalse("2 > 2 error", checker.isQuantityAbused(Topping.class, list, 2));

        list.add(Topping.REESEBUTTERCUP);

        assertTrue("3 > 2 error", checker.isQuantityAbused(Topping.class, list, 2));
    }

}
