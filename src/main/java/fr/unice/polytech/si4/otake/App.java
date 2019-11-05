package fr.unice.polytech.si4.otake;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Flavour;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.ingredient.Topping;

/**
 * Hello world!
 *
 */
public class App {
    private static Logger logger = LogManager.getLogger(App.class);

    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        // @formatter:off
        logger.info(new App().getGreeting());

        Cookie firstcookie = Recipe.DARKTEMPTATION.build();
        Order firstorder = new Order();
        firstorder.addCookie(firstcookie);
        Shop firstshop = new Shop("Nice", 6, "Nice-Cookie");
        firstshop.addOrder(firstorder);
        //@formatter:on
    }
}
