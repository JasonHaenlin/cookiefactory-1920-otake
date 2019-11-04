package fr.unice.polytech.si4.otake;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.unice.polytech.si4.otake.cookiefactory.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.Shop;

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
        logger.info(new App().getGreeting());
        Cookie firstcookie = new Cookie("First", 3);
        Order firstorder = new Order(new Date(),new Date(),"6666666");
        firstorder.addCookie(firstcookie);
        Shop firstshop = new Shop("Nice",6,"Nice-Cookie");
        firstshop.addOrder(firstorder);
        
    }
}
