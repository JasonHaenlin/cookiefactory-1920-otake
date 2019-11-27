package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import io.cucumber.java8.En;

public class ManageCookieBasketStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Order orderobj;

    public ManageCookieBasketStepdefs() {

        // TODO
        Given("a basket containing cookies", () -> {
            // cookieobj = Recipe.SOOCHOCOLATE.create();
            // cookieobj2 = Recipe.DARKTEMPTATION.create();
            // cookieobj3 = Recipe.CHOCOCOLALALA.create();
            // orderobj = new Order();
            // orderobj.addCookie(cookieobj);
            // orderobj.addCookie(cookieobj2);
            // orderobj.addCookie(cookieobj3);
        });
        // TODO
        When("Billy delete one cookie", () -> {
            // assertEquals(1, orderobj.getTheOrderContent().get(cookieobj));
            // orderobj.removeCookie(cookieobj);
        });
        // TODO
        Then("The cookie is not in the basket anymore", () -> {
            // assertFalse(orderobj.getTheOrderContent().containsKey(cookieobj));
        });
        // TODO
        When("Billy add one cookie", () -> {
            // orderobj.addCookie(cookieobj);
            // assertEquals(1, orderobj.getTheOrderContent().get(cookieobj));
            // orderobj.addCookie(cookieobj);
        });
        // TODO
        Then("The cookie is added in the basket", () -> {
            // assertEquals(2, orderobj.getTheOrderContent().get(cookieobj));
        });
    }

}
