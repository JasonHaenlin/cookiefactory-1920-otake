package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;

/**
 * recipeTest
 */
public class recipeTest {

    @Test
    public void cookieCreationTest() {
        Cookie socho = Recipe.SOOCHOCOLATE.create();
        assertEquals(Mix.TOPPED, socho.getMixType());
        Cookie darkt = Recipe.DARKTEMPTATION.create();
        assertEquals(Dough.CHOCOLATE, darkt.getDoughType());
        Cookie choco = Recipe.CHOCOCOLALALA.create();
        assertEquals(Flavour.VANILLA, choco.getFlavourType());
    }
}
