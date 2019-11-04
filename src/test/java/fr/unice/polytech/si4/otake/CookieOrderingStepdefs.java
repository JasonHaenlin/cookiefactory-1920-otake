package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import fr.unice.polytech.si4.otake.cookiefactory.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.Order;
import io.cucumber.java8.En;

public class CookieOrderingStepdefs implements En {

    Cookie cookieobj;
    Order orderobj;

    public CookieOrderingStepdefs() {

        Given("a Cookie {string} and an Order {string}", (String cookie, String order) -> {
             cookieobj = new Cookie(cookie, 3);
             orderobj = new Order(new Date(), new Date() , order);
        });

        When("adding the cookie in the order", () -> {
            orderobj.addCookie(cookieobj);
        });

        Then("Cookie is add in the order", () -> {
            assertEquals(cookieobj.getName(), orderobj.thecookie.getName());
        });
    }

}