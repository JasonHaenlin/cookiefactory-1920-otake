package fr.unice.polytech.si4.otake.cookiefactory.product.cookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.exception.IngredientNotPresentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.exception.TooMuchIngredientRuntimeException;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

/**
 * cookieTest
 */
public class cookieTest {
    Cookie cookie;
    HelperRecipe helper = new HelperRecipe(new RecipeBook());

    @Before
    public void cookieCreation() {
        this.cookie = new Cookie("name", Arrays.asList(helper.chewy, helper.choco, helper.topped, helper.reeseButter),
                false);
    }

    @Test
    public void noToppingTest() {
        try {
            new Cookie("name", Arrays.asList(helper.chewy, helper.choco, helper.topped), false);
            assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Fail ! ");
        }
    }

    @Test
    public void toMuchToppingTest() {
        try {
            new Cookie("name", Arrays.asList(helper.chewy, helper.choco, helper.topped, helper.mms, helper.mms,
                    helper.mms, helper.mms, helper.mms), false);
            Assert.fail("Fail ! ");
        } catch (TooMuchIngredientRuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void cookieFailTest() {
        try {
            new Cookie(null, null, false);
            Assert.fail("Fail ! ");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void cookieBadCreationTest() {
        try {
            new Cookie("name", Arrays.asList(helper.chewy), false);
            Assert.fail("Fail ! ");
        } catch (IngredientNotPresentRuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void equalsCookiesTest() {
        assertTrue(this.cookie.equals(new Cookie("name",
                Arrays.asList(helper.chewy, helper.choco, helper.topped, helper.reeseButter), false)));
        assertFalse(this.cookie.equals(null));
        assertTrue(this.cookie.equals(this.cookie));
    }

    @Test
    public void cookiePriceTest() {
        Cookie cuteLittleCookie = new Cookie("coocute",
                Arrays.asList(helper.chewy, helper.peanutButter, helper.mixed, helper.whiteChoco), false);
        assertEquals(2.3, cuteLittleCookie.applyTaxes(0));
        Cookie customCuteLittleCookie = new Cookie("coocute",
                Arrays.asList(helper.chewy, helper.peanutButter, helper.mixed, helper.whiteChoco), true);
        assertEquals(2.76, customCuteLittleCookie.applyTaxes(0));
        cuteLittleCookie = new Cookie("coocute",
                Arrays.asList(helper.chewy, helper.peanutButter, helper.mixed, helper.whiteChoco, helper.chili), false);
        assertEquals(3.3, cuteLittleCookie.applyTaxes(0));
    }
}
