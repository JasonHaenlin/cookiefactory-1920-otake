package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.exception.IngredientNotPresentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.exception.TooMuchToppingRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;

/**
 * cookieTest
 */
public class cookieTest {
    Cookie cookie;

    @Before
    public void cookieCreation() {
        this.cookie = new Cookie("name",
                Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED, Topping.REESEBUTTERCUP));
    }

    @Test
    public void noToppingTest() {
        try {
            new Cookie("name", Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED));
            assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Fail ! ");
        }
    }

    @Test
    public void toMuchToppingTest() {
        try {
            new Cookie("name", Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED, Topping.MMS, Topping.MMS,
                    Topping.MMS, Topping.MMS, Topping.MMS));
            Assert.fail("Fail ! ");
        } catch (TooMuchToppingRuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void cookieFailTest() {
        try {
            new Cookie(null, null);
            Assert.fail("Fail ! ");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void cookieBadCreationTest() {
        try {
            new Cookie("name", Arrays.asList(Cooking.CHEWY));
            Assert.fail("Fail ! ");
        } catch (IngredientNotPresentRuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void equalsCookiesTest() {
        assertTrue(this.cookie.equals(
                new Cookie("name", Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED, Topping.REESEBUTTERCUP))));
        assertFalse(this.cookie.equals(null));
        assertTrue(this.cookie.equals(this.cookie));
    }

    @Test
    public void cookiePriceTest() {
        Cookie cuteLittleCookie = new Cookie("coocute",
                Arrays.asList(Cooking.CHEWY, Dough.PEANUTBUTTER, Mix.MIXED, Topping.WHITECHOCOLATE));
        assertEquals(2.3, cuteLittleCookie.getPrice());
        cuteLittleCookie = new Cookie("coocute",
                Arrays.asList(Cooking.CHEWY, Dough.PEANUTBUTTER, Mix.MIXED, Topping.WHITECHOCOLATE, Flavour.CHILI));
        assertEquals(3.3, cuteLittleCookie.getPrice());
    }
}
