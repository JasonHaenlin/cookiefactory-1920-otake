package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.AppointmentStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperBasic;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

/**
 * DiscountOrderStepdefs
 */
public class DiscountOnClosingHourStepdefs implements En {
    Order order;
    Product basicCookie;
    Product cookieCustom;
    double basicCookiesPrice;
    Shop shop;
    double taxes;
    ParentCompany parentC;
    HelperRecipe helper;
    Storage storage;
    AppointmentStep apstep;

    public DiscountOnClosingHourStepdefs() {
        Before(() -> {
            taxes = 0.3;
            parentC = new ParentCompany().withDefaultDiscount();
            helper = new HelperRecipe(parentC.getRecipes());

            basicCookie = helper.getSoooChocolate();
            parentC.getRecipes().addRecipe((Cookie) basicCookie);
            cookieCustom = new Cookie("customCookie", Arrays.asList(helper.chewy, helper.choco, helper.mixed), true);
            basicCookiesPrice = basicCookie.applyTaxes(taxes) * 5;
            shop = new Shop("city", "name", parentC).withSchedule(8, 18);

            helper.addToStorage(shop.getStorage(), 1000);
        });

        Given("I order some cookies", () -> {
            apstep = OrderStepBuilder.newOrder().addProduct(basicCookie, 5).addProduct(cookieCustom).validateBasket();

        });
        When("I pass an order at {int} o'clock to a shop closing at {int} o'clock", (Integer hour, Integer closing) -> {
            shop.withSchedule(10, closing);
            this.order = apstep.setAppointment(new SimpleDate("00-00-00 " + hour + ":00")).noCode().withoutAccount()
                    .validatePayment().build(shop);
            shop.addOrder(order);
        });
        Then("I get a {double} discount on my order only on the basics cookies", (Double disc) -> {
            assertEquals(HelperBasic.decreaseWithRatio(basicCookiesPrice, disc) + cookieCustom.applyTaxes(taxes),
                    order.getPriceWithTaxes(), 0.001);
        });

    }
}
