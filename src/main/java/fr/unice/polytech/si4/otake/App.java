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

import java.util.Calendar;
import java.util.Date;

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
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,17);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        firstorder.setAppointmentDate(cal.getTime());
        Shop firstshop = new Shop("Nice", 6, "Nice-Cookie");
        float result = firstshop.addOrder(firstorder);
        System.out.println("Result : " + result);
        //@formatter:on
    }
}
