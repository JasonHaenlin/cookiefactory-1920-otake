package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.discount.Discount;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.CodeStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import io.cucumber.java8.En;

/**
 * DiscountEventStepdefs
 */
public class DiscountEventStepdefs implements En {
    Order o;
    Discount d;
    double finalReduction;
    CodeStep cstep;

    public DiscountEventStepdefs() {
        Given("an order with {int} cookies and a Discount code {string} applied with similar cookies with a reduction of {double}",
                (Integer q, String code, Double r) -> {
                    this.cstep = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create(), q)
                            .validateBasket().setAppointment(new SimpleDate("00-00-00 15:00"));
                    this.d = new Discount(true, r, Discount.Trigger.code(code), Discount.Behaviour.products(q));
                });
        When("a customer enter a code {string} the discount is applied", (String code) -> {
            this.o = this.cstep.withCode(code).withoutAccount().validatePayment().build(new Shop("", "", null));
            this.finalReduction = this.d.applyIfEligible(this.o, null, null);
        });
        Then("the discount is on with a reduction of {double}", (Double r) -> {
            assertEquals(0.1, this.finalReduction);
            this.o.setPriceWithTaxes(10);
        });
        And("his passing from {double} to {double}", (Double p, Double r) -> {
            assertEquals(10, this.o.getPriceWithTaxes());
            this.o.applyDiscount(this.finalReduction);
            assertEquals(9, this.o.getPriceWithTaxes());
        });

    }
}
