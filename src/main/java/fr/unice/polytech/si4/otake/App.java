package fr.unice.polytech.si4.otake;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;

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
        cal.set(Calendar.HOUR_OF_DAY,15);
        firstorder.setAppointmentDate(cal);
        Shop firstshop = new Shop("Nice", 6, "Nice-Cookie");
        firstshop.addOrder(firstorder);
        //@formatter:on
    }
}
