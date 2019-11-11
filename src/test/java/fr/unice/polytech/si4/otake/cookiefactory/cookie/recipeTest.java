package fr.unice.polytech.si4.otake.cookiefactory.cookie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

/**
 * recipeTest
 */
public class recipeTest {

    @Test
    public void cookieCreationTest() {
        Cookie socho = Recipe.SOOCHOCOLATE.create();
        assertEquals("Soooo Chocolate", socho.getName());
        Cookie darkt = Recipe.DARKTEMPTATION.create();
        assertEquals("Dark Temptation", darkt.getName());
        Cookie choco = Recipe.CHOCOCOLALALA.create();
        assertEquals("Chococolalala", choco.getName());
    }
}
