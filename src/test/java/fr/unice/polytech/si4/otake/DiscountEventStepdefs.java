package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.discount.Discount;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import io.cucumber.java8.En;

/**
 * DiscountEventStepdefs
 */
public class DiscountEventStepdefs implements En {
    Order o;
    Discount d;
    double finalReduction;

    public DiscountEventStepdefs() {
        Given("an order with {int} cookies and a Discount code {string} applied with similar cookies with a reduction of {double}",
                (Integer q, String code, Double r) -> {
                    // TODO
                    // this.o = new Order();
                    // Cookie c = Recipe.SOOCHOCOLATE.create();
                    // for (int i = 0; i < q; i++) {
                    // this.o.addCookie(c);
                    // }
                    // this.d = new Discount(true, r, Discount.Trigger.code(code),
                    // Discount.Behaviour.products(q));
                });
        When("a customer enter a code {string} the discount is applied", (String code) -> {
            // TODO
            // this.o.setCode(code);
            // this.finalReduction = this.d.applyIfEligible(this.o, null, null);
        });
        Then("the discount is on with a reduction of {double}", (Double r) -> {
            // TODO
            // assertEquals(0.1, this.finalReduction);
            // this.o.setPriceWithTaxes(10);
        });
        And("his passing from {double} to {double}", (Double p, Double r) -> {
            // TODO
            // assertEquals(10, this.o.getPriceWithTaxes());
            // this.o.applyDiscount(this.finalReduction);
            // assertEquals(9, this.o.getPriceWithTaxes());
        });

    }
}
