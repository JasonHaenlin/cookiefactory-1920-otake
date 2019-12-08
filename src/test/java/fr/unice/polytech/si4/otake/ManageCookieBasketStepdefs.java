package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class ManageCookieBasketStepdefs implements En {

    Cookie c1;
    Cookie c2;
    Cookie c3;
    Order orderobj;
    ProductStep pstep;
    HelperRecipe helper;

    public ManageCookieBasketStepdefs() {

        Given("a basket containing cookies", () -> {
            helper = new HelperRecipe(new RecipeBook());
            c1 = helper.getSoooChocolate();
            c2 = helper.getDarkTemptation();
            c3 = helper.getChocolalala();
            pstep = OrderStepBuilder.newOrder();
            pstep.addProduct(c1).addProduct(c2).addProduct(c3);

        });
        When("Billy delete one cookie", () -> {
            assertEquals(1, pstep.getContent().get(c1));
            pstep.removeProduct(c1);
        });
        Then("The cookie is not in the basket anymore", () -> {
            assertFalse(pstep.getContent().containsKey(c1));
        });
        When("Billy add one cookie", () -> {
            pstep.addProduct(c1);
            assertEquals(1, pstep.getContent().get(c1));
            pstep.addProduct(c1);
        });
        Then("The cookie is added in the basket", () -> {
            assertEquals(2, pstep.getContent().get(c1));
        });
    }

}
