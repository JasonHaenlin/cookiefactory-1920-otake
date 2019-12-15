package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.AppointmentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.CodeStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.PaymentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class PaymentStepdefs implements En {
    Order order;
    PaymentStep payment;
    CodeStep reduction;
    ProductStep basket;
    AppointmentStep appointment;
    OrderStepBuilder validator;
    HelperRecipe helper;
    Shop shop = new Shop("city", "name", new ParentCompany());

    public PaymentStepdefs() {
        Given("A complete order", () -> {
            helper = new HelperRecipe(new RecipeBook());
            basket = OrderStepBuilder.newOrder();
            Product p1 = helper.getSoooChocolate();
            Product p2 = helper.getChocolalala();
            helper.addToStorage(shop.getStorage(), 1000);
            basket.addProduct(p1, 15).addProduct(p2, 5);
        });

        When("The customer begin the validation process", () -> {
            appointment = basket.validateBasket();
            reduction = appointment.setAppointment(new SimpleDate("00-00-00 13:00"));
            payment = reduction.noCode().withoutAccount();
        });

        Then("The customer pay the due price", () -> {
            assertNull(order);
            order = payment.validatePayment().build(shop);
            assertNotNull(order);
            assertEquals(20, order.getQuantity());
        });
    }
}
