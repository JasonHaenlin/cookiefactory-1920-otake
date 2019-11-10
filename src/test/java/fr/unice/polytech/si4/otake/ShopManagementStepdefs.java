package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import io.cucumber.java8.En;

public class ShopManagementStepdefs implements En {

    Shop myShop;
    Order order;

    public ShopManagementStepdefs() {

        Given("the shop {string} of {string} has taxes of {float}", (String name, String city, Float taxes) -> {
            myShop = new Shop("Nice", taxes, "Nice granny cookie");
        });

        When("a customer makes an order of {int} of his favourite cookie", (Integer nbOfFavCookie) -> {
            order = new Order();
            for (int i = 0; i < nbOfFavCookie; i++)
                order.addCookie(Recipe.DARKTEMPTATION.create());
        });

        Then("the price is calculated according to the shop taxes policy", () -> {
            assertEquals(myShop.getTaxes() * order.getPrice() + order.getPrice(), myShop.applyTaxes(order));
        });

        When("the store manager wants to change the store taxes to {float}", (Float newTaxes) -> {
            myShop.setTaxes(newTaxes);
        });

        And("a customer order {int} cookies", (Integer nbCookies) -> {
            order = new Order();
            for (int i = 0; i < nbCookies; i++)
                order.addCookie(Recipe.DARKTEMPTATION.create());
        });

        Then("the new taxes applies to the cookies ordering", () -> {
            assertNotEquals((float) 0.001, myShop.getTaxes());
            assertNotEquals(0.001 * order.getPrice() + order.getPrice(), myShop.applyTaxes(order));
            assertEquals(myShop.getTaxes() * order.getPrice() + order.getPrice(), myShop.applyTaxes(order));
        });

    }
}
