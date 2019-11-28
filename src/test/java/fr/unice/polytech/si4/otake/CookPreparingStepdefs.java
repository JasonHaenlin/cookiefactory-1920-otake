package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import io.cucumber.java8.En;

/**
 * CookPreparingStepdefs
 */
public class CookPreparingStepdefs implements En {
    RecipeBook factory;
    Shop shop;
    Order o;
    Order o1;
    Order o2;

    public CookPreparingStepdefs() {
        Given("orders in a shop", () -> {
            shop = new Shop("city", "name", factory);
            o1 = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 14:00")).noCode().validatePayment().build(shop);
            o2 = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 12:00")).noCode().validatePayment().build(shop);
            shop.addOrder(o2);
            shop.addOrder(o1);
        });
        When("the cook want to prepare the first order", () -> {
            o = shop.getCurrentOrder();
        });
        Then("the cook can retrieve the first order in the queue", () -> {
            assertEquals(o2, o);
        });
        When("the cook finish the preparation", () -> {
            o = shop.getNextOrder();
        });
        Then("the cook can go to the next order", () -> {
            assertEquals(o1, o);
        });
        When("an order is retrieve by his id", () -> {
            assertTrue(shop.retrieved(o2.getId()));
        });
        Then("the order is archive in the queue", () -> {
            assertEquals(o1, shop.getCurrentOrder());
            assertTrue(o2.hasBeenRetrieved());
        });
    }
}
