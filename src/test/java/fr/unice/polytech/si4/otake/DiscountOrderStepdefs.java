package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Cooking;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Dough;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.ingredient.Mix;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import io.cucumber.java8.En;

/**
 * DiscountOrderStepdefs
 */
public class DiscountOrderStepdefs implements En {
    Order o;
    Product p;
    Product pc;
    double fullprice;
    Shop s;
    double taxes;

    public DiscountOrderStepdefs() {
        Given("a customer that order at the end of the day", () -> {
            taxes = 0.3;
            p = Recipe.SOOCHOCOLATE.create();
            pc = new Cookie("customCookie", Arrays.asList(Cooking.CHEWY, Dough.CHOCOLATE, Mix.MIXED), true);
            fullprice = p.getPrice() * 5 + ((p.getPrice() * 5) * taxes);
            s = new Shop("city", "name", null).withSchedule(8, 18);
        });
        When("a customer create an order with cookies at {int} o'clock", (Integer day) -> {
            o = OrderStepBuilder.newOrder().addProduct(p, 5).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 " + day + ":00")).noCode().WithoutAccount()
                    .validatePayment().build(s);
            s.addOrder(o);

        });
        Then("if the closing hour is almost time, a discount of {double} is apply on basics cookies", (Double disc) -> {
            assertEquals(fullprice - (fullprice * disc), o.getPriceWithTaxes());
        });

    }
}
