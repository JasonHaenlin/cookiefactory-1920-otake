package fr.unice.polytech.si4.otake.cookie.cookieFactory;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
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
        this.cookie = new Cookie("name", 2.25, Cooking.CHEWY, Dough.CHOCOLATE, Mix.TOPPED);
    }

    @Test
    public void addFlavourTest() {
        assertNull(this.cookie.getFlavourType());
        this.cookie.withFlavourType(Flavour.VANILLA);
        assertNotNull(this.cookie.getFlavourType());
    }

    @Test
    public void addToppingTest() {
        assertEquals(0, this.cookie.getToppings().size());
        this.cookie.addTopping(Topping.MMS);
        assertEquals(1, this.cookie.getToppings().size());
        this.cookie.addTopping(Topping.MMS);
        this.cookie.addTopping(Topping.MMS);
        this.cookie.addTopping(Topping.MMS);
        assertEquals(3, this.cookie.getToppings().size());
    }

    @Test
    public void cookieFailTest() {
        try {
            new Cookie(null, -5, null, null, null);
            Assert.fail("Fail ! ");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void cookieCookTest() {
        Cookie coocute = new Cookie("coocute", 59.59, Cooking.CHEWY, Dough.PEANUTBUTTER, Mix.MIXED).cook();
        assertEquals(1, coocute.getToppings().size());
        assertEquals(Mix.MIXED, coocute.getMixType());
        assertEquals(Cooking.CHEWY, coocute.getCookingType());
        assertEquals(Dough.PEANUTBUTTER, coocute.getDoughType());
    }
}
