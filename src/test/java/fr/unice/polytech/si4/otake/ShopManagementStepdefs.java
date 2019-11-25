package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import io.cucumber.java8.En;

public class ShopManagementStepdefs implements En {

    Shop myShop;
    RecipeBook factory;
    Order order;

    public ShopManagementStepdefs() {

        Given("the shop {string} of {string} has taxes of {double}", (String name, String city, Double taxes) -> {
            myShop = new Shop("Nice", taxes, "Nice granny cookie", factory);
        });

        When("a customer makes an order of {int} of his favourite cookie", (Integer nbOfFavCookie) -> {
            order = new Order();
            for (int i = 0; i < nbOfFavCookie; i++)
                order.addCookie(Recipe.DARKTEMPTATION.create());
        });

        Then("the price is calculated according to the shop taxes policy", () -> {
            assertEquals(myShop.getTaxes() * order.getPriceWithoutTaxes() + order.getPriceWithoutTaxes(),
                    myShop.applyTaxes(order));
        });

        When("the store manager wants to change the store taxes to {double}", (Double newTaxes) -> {
            myShop.setTaxes(newTaxes);
        });

        And("a customer order {int} cookies", (Integer nbCookies) -> {
            order = new Order();
            for (int i = 0; i < nbCookies; i++)
                order.addCookie(Recipe.DARKTEMPTATION.create());
        });

        Then("the new taxes applies to the cookies ordering", () -> {
            assertNotEquals(0.001, myShop.getTaxes());
            assertNotEquals(0.001 * order.getPriceWithoutTaxes() + order.getPriceWithoutTaxes(),
                    myShop.applyTaxes(order));
            assertEquals(myShop.getTaxes() * order.getPriceWithoutTaxes() + order.getPriceWithoutTaxes(),
                    myShop.applyTaxes(order));
        });

    }
}
