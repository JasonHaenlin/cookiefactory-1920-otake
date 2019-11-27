package fr.unice.polytech.si4.otake.cookiefactory.order.builder;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.builder.OrderStepBuilder.AppointmentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.builder.OrderStepBuilder.BuildStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.builder.OrderStepBuilder.CodeStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.builder.OrderStepBuilder.PaymentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.builder.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

/**
 * orderSteps
 */
public class OrderSteps implements ProductStep, AppointmentStep, CodeStep, PaymentStep, BuildStep {

    private final Map<Product, Integer> content;
    private SimpleDate appointmentDate;
    private String code;

    OrderSteps() {
        this.content = new HashMap<>();
    }

    @Override
    public ProductStep addProduct(Product product) {
        return add(product, 1);
    }

    @Override
    public ProductStep addProduct(Product product, int quantity) {
        return add(product, quantity);
    }

    private ProductStep add(Product product, int quantity) {
        Integer t = this.content.get(product);
        if (t == null) {
            t = 0;
        }
        this.content.put(product, t + quantity);
        return this;
    }

    @Override
    public ProductStep removeProduct(Product product) {
        return remove(product, 1);
    }

    @Override
    public ProductStep removeProduct(Product product, int quantity) {
        return remove(product, quantity);
    }

    private ProductStep remove(Product product, int quantity) {
        Integer t = this.content.get(product);
        if (t == null) {
            return this;
        }
        if (t > 1) {
            this.content.put(product, t - quantity);
        } else {
            this.content.remove(product);
        }
        return this;
    }

    @Override
    public AppointmentStep validateBasket() {
        return this;
    }

    @Override
    public CodeStep setAppointment(SimpleDate date) {
        this.appointmentDate = date;
        return this;
    }

    @Override
    public PaymentStep withCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public PaymentStep noCode() {
        this.code = "";
        return this;
    }

    @Override
    public BuildStep validatePayment() {
        return this;
    }

    @Override
    public Order build(Shop shop) {
        if (!shop.checkAppointmentDate(this.appointmentDate)) {
            return null;
        }
        return new Order(this.content, this.appointmentDate, this.code);
    }

}
