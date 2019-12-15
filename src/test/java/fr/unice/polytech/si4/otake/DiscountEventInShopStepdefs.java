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
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperBasic;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

/**
 * DiscountEventStepdefs
 */
public class DiscountEventInShopStepdefs implements En {

    Order o;
    Discount d;
    double finalReduction;
    CodeStep cstep;
    BuildStep valstep;
    HelperRecipe helper;
    Storage storage;
    Shop shop;
    ParentCompany pc;
    double fullPrice;

    public DiscountEventInShopStepdefs() {
        Before(() -> {
            pc = new ParentCompany();
            shop = new Shop("", "", pc);
            storage = shop.getStorage();
            helper = new HelperRecipe(new RecipeBook());
            helper.addToStorage(shop.getStorage(), 1000);
        });

        Given("I order {int} cookies", (Integer q) -> {
            this.cstep = OrderStepBuilder.newOrder().addProduct(helper.getSoooChocolate(), q).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 15:00"));
            fullPrice = HelperBasic.increaseWithRatio(helper.getSoooChocolate().getPrice() * q, shop.getTaxes());

        });

        When("I enter the discount code {string}", (String code) -> {
            this.valstep = this.cstep.withCode(code).withoutAccount().validatePayment();
        });

        Then("a discount of {double} is applied on my order with the {string} code on {int} cookies",
                (Double r, String code, Integer q) -> {
                    this.d = new Discount(true, r, Discount.Trigger.code(code), Discount.Behaviour.products(q));
                    this.pc.getDiscounts()
                            .add(new Discount(true, r, Discount.Trigger.code(code), Discount.Behaviour.products(q)));
                    this.o = this.valstep.build(shop);
                    assertEquals("EVENT", this.o.getCode());
                });
        And("I have to pay {double} less euro than before the discount", (Double reduction) -> {
            assertTrue(this.o.getPriceWithTaxes() < fullPrice);
            assertEquals(HelperBasic.decreaseWithRatio(fullPrice, reduction), this.o.getPriceWithTaxes(), 0.001);
        });

    }

}
