package fr.unice.polytech.si4.otake.cookiefactory.order;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.BadAppointmentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.NoProductRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.NotEnoughIngredientsRuntimeException;
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

        Map<Product, Integer> getContent();
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
        AccountStep withCode(String code);

        AccountStep noCode();
    }

    public interface AccountStep {
        PaymentStep withAccount(RegisteredCustomer rg);

        PaymentStep withoutAccount();
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

    /**
     * orderSteps
     */
    private static class OrderSteps
            implements ProductStep, AppointmentStep, CodeStep, PaymentStep, AccountStep, BuildStep {

        private final Map<Product, Integer> content;
        private SimpleDate appointmentDate;
        private String code;
        private RegisteredCustomer rg;

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
        public Map<Product, Integer> getContent() {
            return content;
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
        public AccountStep withCode(String code) {
            this.code = code;
            return this;
        }

        @Override
        public AccountStep noCode() {
            this.code = "";
            return this;
        }

        @Override
        public PaymentStep withAccount(RegisteredCustomer rg) {
            this.rg = rg;
            return this;
        }

        @Override
        public PaymentStep withoutAccount() {
            this.rg = null;
            return this;
        }

        @Override
        public BuildStep validatePayment() {
            return this;
        }

        @Override
        public Order build(Shop shop) {
            if (!shop.checkAppointmentDate(this.appointmentDate)) {
                throw new BadAppointmentRuntimeException();
            }
            if (this.content.isEmpty()) {
                throw new NoProductRuntimeException();
            }
            Order o = new Order(this.content, this.appointmentDate, this.code, this.rg);
            o.applyTaxes(shop.getTaxes());

            if (!shop.isStorageEnough(o.toCookieList())) {
                throw new NotEnoughIngredientsRuntimeException();
            }
            shop.getDiscounts().applyDiscounts(o, rg, shop);
            return o;
        }

    }

}
