package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class CookieOrderingStepdefs implements En {

    Cookie c;
    Order orderobj;
    ProductStep pstep;

    public CookieOrderingStepdefs() {

        Given("a Cookie {string} and an Order", (String cookie) -> {
            c = new HelperRecipe(new RecipeBook()).getDarkTemptation();
            pstep = OrderStepBuilder.newOrder();
        });

        When("adding the cookie in the order", () -> {
            pstep.addProduct(c);
        });

        Then("Cookie is add in the order", () -> {
            assertEquals(1, pstep.getContent().get(c));
        });
    }

}
