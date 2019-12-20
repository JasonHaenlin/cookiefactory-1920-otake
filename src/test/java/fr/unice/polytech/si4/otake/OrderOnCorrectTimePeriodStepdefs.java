package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.AppointmentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.BuildStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.CodeStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.BadAppointmentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

/**
 * OrderOnCorrectTimePeriodStepdefs
 */
public class OrderOnCorrectTimePeriodStepdefs implements En {

    ParentCompany parent;
    HelperRecipe helper;
    Shop shop;
    AppointmentStep appStep;
    BuildStep buildStep;
    CodeStep codeStep;
    Order o;
    int id;

    public OrderOnCorrectTimePeriodStepdefs() {
        Before(() -> {
            parent = new ParentCompany();
            helper = new HelperRecipe(parent.getRecipes());
            shop = new Shop("city", "name", parent);
            helper.addToStorage(shop.getStorage(), 100);
            parent.addShop(shop);
        });

        Given("I am making an order", () -> {
            this.appStep = OrderStepBuilder.newOrder().addProduct(helper.getChocolalala()).validateBasket();
        });

        Given("I set an appointment date", () -> {
            this.codeStep = this.appStep.setAppointment(new SimpleDate("00-00-00 13:00"));
        });

        When("I create my order", () -> {
            this.o = this.codeStep.noCode().withoutAccount().validatePayment().build(shop);
        });

        Then("I can validate the order in the shop", () -> {
            this.shop.addOrder(this.o);
        });

        But("If the date is not correct", () -> {
            try {
                this.o = this.appStep.setAppointment(new SimpleDate("00-00-00 05:00")).noCode().withoutAccount()
                        .validatePayment().build(shop);
            } catch (BadAppointmentRuntimeException e) {
                this.o = null;
            }
        });

        Then("I will have to try again", () -> {
            assertNull(this.o);
        });

        Given("A ready order", () -> {
            shop = spy(shop);
            this.o = this.appStep.setAppointment(new SimpleDate("00-00-00 17:00")).noCode().withoutAccount()
                    .validatePayment().build(shop);
            this.shop.addOrder(this.o);
            shop.getNextOrder();
        });

        When("I want to retrieve it", () -> {
            id = this.o.getId();
        });

        Then("With the correct id, I can retrieve it", () -> {
            when(shop.verifyRetrieveDate(this.o)).thenReturn(true);
            assertTrue(shop.retrieved(id));
        });

        But("if I retrieve my order too soon, I'll not be able to get it", () -> {
            Shop spyShop = spy(shop);
            when(spyShop.verifyRetrieveDate(this.o)).thenReturn(false);
            this.o = this.appStep.setAppointment(new SimpleDate("00-00-00 17:00")).noCode().withoutAccount()
                    .validatePayment().build(spyShop);
            spyShop.addOrder(this.o);
            spyShop.getNextOrder();
            assertFalse(spyShop.retrieved(id));
        });
    }
}
