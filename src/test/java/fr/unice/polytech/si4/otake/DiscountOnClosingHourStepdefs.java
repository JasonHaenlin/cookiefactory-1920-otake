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
    Order o;
    Product p;
    Product pc;
    double fullprice;
    Shop s;
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

            p = helper.getSoooChocolate();
            parentC.getRecipes().addRecipe((Cookie) p);
            pc = new Cookie("customCookie", Arrays.asList(helper.chewy, helper.choco, helper.mixed), true);
            fullprice = HelperBasic.increaseWithRatio(p.getPrice() * 5, taxes);
            s = new Shop("city", "name", parentC).withSchedule(8, 18);

            helper.addToStorage(s.getStorage(), 1000);
        });

        Given("I order some cookies", () -> {
            apstep = OrderStepBuilder.newOrder().addProduct(p, 5).addProduct(pc).validateBasket();

        });
        When("I pass an order at {int} o'clock to a shop closing at {int} o'clock", (Integer hour, Integer closing) -> {
            s.withSchedule(10, closing);
            this.o = apstep.setAppointment(new SimpleDate("00-00-00 " + hour + ":00")).noCode().withoutAccount()
                    .validatePayment().build(s);
            s.addOrder(o);
        });
        Then("I get a {double} discount on my order only on the basics cookies", (Double disc) -> {
            assertEquals(HelperBasic.decreaseWithRatio(fullprice, disc)
                    + HelperBasic.increaseWithRatio(pc.getPrice(), taxes), o.getPriceWithTaxes());
        });

    }
}
