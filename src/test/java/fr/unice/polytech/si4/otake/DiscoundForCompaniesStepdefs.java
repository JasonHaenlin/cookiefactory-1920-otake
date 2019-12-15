package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.discount.Discount;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.BuildStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.CodeStep;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.helper.HelperBasic;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

/**
 * DiscoundForCompaniesStepdefs
 */
public class DiscoundForCompaniesStepdefs implements En {

    CodeStep cstep;
    BuildStep valstep;
    HelperRecipe helper;
    Shop shop;
    ParentCompany pc;
    double fullPrice;
    String code;

    public DiscoundForCompaniesStepdefs() {
        Before(() -> {
            pc = new ParentCompany();
            shop = new Shop("", "", pc);
            helper = new HelperRecipe(new RecipeBook());
            helper.addToStorage(shop.getStorage(), 1000);
        });

        Given("I am creating an order", () -> {
            this.cstep = OrderStepBuilder.newOrder().addProduct(helper.getSoooChocolate(), 5).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 15:00"));
            fullPrice = HelperBasic.increaseWithRatio(helper.getSoooChocolate().getPrice() * 5, shop.getTaxes());

        });

        When("I add the code {string}", (String code) -> {
            this.code = code;
            this.valstep = this.cstep.withCode(code).withoutAccount().validatePayment();
        });

        Then("A discount will be added on the final price", () -> {
            Discount d = new Discount(true, 0.1, Discount.Trigger.code(code), Discount.Behaviour.basic());
            this.pc.getDiscounts().add(d);
            Order o = this.valstep.build(shop);
            assertTrue(fullPrice > o.getPriceWithTaxes());
            assertEquals(HelperBasic.decreaseWithRatio(fullPrice, 0.1), o.getPriceWithTaxes());
        });

    }

}
