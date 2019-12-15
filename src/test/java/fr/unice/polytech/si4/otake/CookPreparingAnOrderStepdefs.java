package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

/**
 * CookPreparingStepdefs
 */
public class CookPreparingAnOrderStepdefs implements En {
    Shop shop;
    Order o, o1, o2;
    HelperRecipe helper;
    Storage storage;
    Cookie cookie;

    public CookPreparingAnOrderStepdefs() {

        Before(() -> {
            helper = new HelperRecipe(new RecipeBook());
            shop = new Shop("city", "name", new ParentCompany());
            helper.addToStorage(shop.getStorage(), 1000);
            o1 = OrderStepBuilder.newOrder().addProduct(helper.getSoooChocolate()).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 14:00")).noCode().withoutAccount().validatePayment()
                    .build(shop);
            o2 = OrderStepBuilder.newOrder().addProduct(helper.getSoooChocolate()).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 12:00")).noCode().withoutAccount().validatePayment()
                    .build(shop);
            shop.addOrder(o2);
            shop.addOrder(o1);
        });

        Given("I retrieve a waiting order", () -> {
            o = shop.getCurrentOrder();
        });
        When("I want to make a cookie", () -> {
            cookie = o.toCookieList().get(0);
        });
        Then("I can see the ingredients", () -> {
            assertFalse(cookie.getIngredients().isEmpty());
        });
        When("I finished the first one", () -> {
            o = shop.getNextOrder();
        });
        Then("I can retrieve the next waiting order", () -> {
            assertEquals(o1, o);
        });
    }
}
