package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
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
 * DiscountWithSeniorityStepdefs
 */
public class DiscountWithSeniorityStepdefs implements En {

    CodeStep cstep;
    BuildStep valstep;
    HelperRecipe helper;
    Shop shop;
    ParentCompany pc;
    double fullPrice;
    int yearsold;

    public DiscountWithSeniorityStepdefs() {
        Before(() -> {
            pc = new ParentCompany();
            shop = new Shop("", "", pc);
            helper = new HelperRecipe(new RecipeBook());
            helper.addToStorage(shop.getStorage(), 1000);
        });

        Given("I want to make an order", () -> {
            this.cstep = OrderStepBuilder.newOrder().addProduct(helper.getSoooChocolate(), 5).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 15:00"));
            fullPrice = HelperBasic.increaseWithRatio(helper.getSoooChocolate().getPrice() * 5, shop.getTaxes());

        });

        When("I order with a {int} years account", (Integer yearsold) -> {
            this.yearsold = yearsold;
            RegisteredCustomer rg = spy(new RegisteredCustomer("1", true));
            SimpleDate date = new SimpleDate();
            date.setYear(date.getYear() - yearsold);
            when(rg.getRegistrationDate()).thenReturn(date);
            this.valstep = this.cstep.noCode().withAccount(rg).validatePayment();
        });

        Then("I should receive a {double} discount on the final price", (Double discount) -> {
            Discount d = new Discount(true, discount, Discount.Trigger.seniority(), Discount.Behaviour.enrolmentTime());
            this.pc.getDiscounts().add(d);
            Order o = this.valstep.build(shop);
            assertTrue(fullPrice > o.getPriceWithTaxes(), fullPrice + ">" + o.getPriceWithTaxes());
            assertEquals(HelperBasic.decreaseWithRatio(fullPrice, discount * yearsold), o.getPriceWithTaxes(), 0.001);
        });

    }

}
