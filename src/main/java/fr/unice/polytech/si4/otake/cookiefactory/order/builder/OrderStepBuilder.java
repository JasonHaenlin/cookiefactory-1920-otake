package fr.unice.polytech.si4.otake.cookiefactory.order.builder;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

/**
 * OrderStepBuilder
 */
public class OrderStepBuilder {

    private OrderStepBuilder() {
    }

    public static ProductStep newOrder() {
        return new OrderSteps();
    }

    /**
     * build the basket
     */
    public interface ProductStep {
        ProductStep addProduct(Product product);

        ProductStep addProduct(Product product, int quantity);

        ProductStep removeProduct(Product product);

        ProductStep removeProduct(Product product, int quantity);

        AppointmentStep validateBasket();
    }

    /**
     * set an appointment date
     */
    public interface AppointmentStep {
        CodeStep setAppointment(SimpleDate date);
    }

    /**
     * add a discount code like EVENT or CE_<COMPANY>
     */
    public interface CodeStep {
        PaymentStep withCode(String code);

        PaymentStep noCode();
    }

    /**
     * validate your payment
     */
    public interface PaymentStep {
        BuildStep validatePayment();
    }

    /**
     * check and build an order; verify the appointment date regarding a shop and
     * give you the order with the computed price with taxes
     */
    public interface BuildStep {
        Order build(Shop shop);
    }

}
