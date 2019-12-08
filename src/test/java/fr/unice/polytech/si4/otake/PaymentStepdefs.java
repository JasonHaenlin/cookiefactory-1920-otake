package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.PaymentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.CodeStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.AppointmentStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentStepdefs implements En {
    Order order;
    PaymentStep payment;
    CodeStep reduction;
    ProductStep basket;
    AppointmentStep appointment;
    OrderStepBuilder validator;
    HelperRecipe helper;
    Shop shop = new Shop("city", "name", null);

    public PaymentStepdefs() {
        Given("A complete order", () -> {
            helper = new HelperRecipe(new RecipeBook());
            basket = OrderStepBuilder.newOrder();
            Product p1 = helper.getSoooChocolate();
            Product p2 = helper.getChocolalala();
            basket.addProduct(p1, 15).addProduct(p2, 5);
        });

        When("The customer begin the validation process", () -> {
            appointment = basket.validateBasket();
            // TODO choose available shop
            // TODO choose appointment - done
            reduction = appointment.setAppointment(new SimpleDate("00-00-00 13:00"));
            // TODO build pack if possible
            // TODO apply reduction - done
            payment = reduction.noCode().WithoutAccount();
        });

        Then("The customer pay the due price", () -> {
            assertNull(order);
            order = payment.validatePayment().build(shop);
            assertNotNull(order);
            assertEquals(20, order.getQuantity());
        });
    }
}
