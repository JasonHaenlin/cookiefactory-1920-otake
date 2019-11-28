package fr.unice.polytech.si4.otake.cookiefactory.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

/**
 * DiscountQueueTest
 */
public class DiscountQueueTest {

    Order o;
    DiscountQueue dq;
    Discount d1;
    Discount d2;
    Discount d3;

    @Before
    public void init() {
        this.dq = new DiscountQueue();
        this.o = OrderStepBuilder.newOrder().addProduct(Recipe.CHOCOCOLALALA.create()).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().validatePayment()
                .build(new Shop("", "name", null));
        this.o.setPriceWithTaxes(10);
    }

    @Test
    public void discountQueueCompareTest() {
        this.d1 = new Discount(true, 4.5, null, null);
        this.d2 = new Discount(false, 4.5, null, null);
        this.d3 = new Discount(true, 4.5, null, null);

        assertEquals(0, d1.compareTo(d3));
        assertEquals(1, d2.compareTo(d1));
        assertEquals(1, d2.compareTo(d3));
        assertEquals(-1, d3.compareTo(d2));
    }

    @Test
    public void discountQueueOrderExclusiveTest() {
        this.d1 = new Discount(true, 0.1, new DiscountTrigger() {
            @Override
            public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        }, new DiscountBehaviour() {
            @Override
            public boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        });
        this.d2 = new Discount(false, 4.5, null, null);
        this.d3 = new Discount(false, 4.5, null, null);
        this.dq.add(d2);
        this.dq.add(d1);
        this.dq.add(d3);
        this.dq.applyDiscounts(o, null, null);
        assertEquals(9, o.getPriceWithTaxes());
    }

    @Test
    public void discountQueueNotExclusiveTest() {
        this.d1 = new Discount(true, 0.1, new DiscountTrigger() {
            @Override
            public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return false;
            }
        }, new DiscountBehaviour() {
            @Override
            public boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        });
        this.d2 = new Discount(false, 0.1, new DiscountTrigger() {
            @Override
            public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        }, new DiscountBehaviour() {
            @Override
            public boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        });
        this.d3 = new Discount(false, 0.1, new DiscountTrigger() {
            @Override
            public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        }, new DiscountBehaviour() {
            @Override
            public boolean apply(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        });
        this.dq.add(d2);
        this.dq.add(d1);
        this.dq.add(d3);
        this.dq.applyDiscounts(o, null, null);
        assertEquals(8, o.getPriceWithTaxes());
    }
}
