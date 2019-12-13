package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
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
    ParentCompany parentC;
    HelperRecipe helper;
    Storage storage;

    public DiscountOrderStepdefs() {
        Given("a customer that order at the end of the day", () -> {
            taxes = 0.3;
            parentC = new ParentCompany();
            helper = new HelperRecipe(parentC.getRecipeBook());

            p = helper.getSoooChocolate();
            parentC.getRecipeBook().addRecipe((Cookie) p);
            pc = new Cookie("customCookie", Arrays.asList(helper.chewy, helper.choco, helper.mixed), true);
            fullprice = (p.getPrice() * 5) + ((p.getPrice() * 5) * taxes);
            s = new Shop("city", "name", parentC).withSchedule(8, 18);

            storage = s.getStorage();
            storage.addStock(helper.chewy, 1000);
            storage.addStock(helper.crunchy, 1000);
            storage.addStock(helper.choco, 1000);
            storage.addStock(helper.mixed, 1000);
            storage.addStock(helper.topped, 1000);
            storage.addStock(helper.milkChoco, 1000);
            storage.addStock(helper.whiteChoco, 1000);
            storage.addStock(helper.cinnamon, 1000);
            storage.addStock(helper.vanilla, 1000);
        });
        When("a customer create an order with cookies at {int} o'clock", (Integer day) -> {
            o = OrderStepBuilder.newOrder().addProduct(p, 5).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 " + day + ":00")).noCode().withoutAccount()
                    .validatePayment().build(s);
            s.addOrder(o);

        });
        Then("if the closing hour is almost time, a discount of {double} is apply on basics cookies", (Double disc) -> {
            assertEquals(fullprice - (fullprice * disc), o.getPriceWithTaxes());
        });

    }
}
