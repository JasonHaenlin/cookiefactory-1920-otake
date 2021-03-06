package fr.unice.polytech.si4.otake.cookiefactory.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

/**
 * DiscountQueueTest
 */
public class DiscountQueueTest {

    Order o;
    DiscountQueue dq;
    Shop shop = new Shop("", "name", new ParentCompany());
    Storage storage = shop.getStorage();
    Discount d1;
    Discount d2;
    Discount d3;
    HelperRecipe helper;

    @Before
    public void init() {
        this.dq = new DiscountQueue();
        this.helper = new HelperRecipe(new RecipeBook());
        storage.addStock(helper.chewy, 200);
        storage.addStock(helper.crunchy, 200);
        storage.addStock(helper.choco, 200);
        storage.addStock(helper.mixed, 200);
        storage.addStock(helper.topped, 200);
        storage.addStock(helper.milkChoco, 200);
        storage.addStock(helper.whiteChoco, 200);
        storage.addStock(helper.cinnamon, 200);
        storage.addStock(helper.vanilla, 200);
        this.o = OrderStepBuilder.newOrder().addProduct(helper.getChocolalala()).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment()
                .build(shop);
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
            public double apply(Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) {
                return reduction;
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
            public double apply(Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) {
                return reduction;
            }
        });
        this.d2 = new Discount(false, 0.1, new DiscountTrigger() {
            @Override
            public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        }, new DiscountBehaviour() {
            @Override
            public double apply(Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) {
                return reduction;
            }
        });
        this.d3 = new Discount(false, 0.1, new DiscountTrigger() {
            @Override
            public boolean check(Order order, RegisteredCustomer registeredCustomer, Shop shop) {
                return true;
            }
        }, new DiscountBehaviour() {
            @Override
            public double apply(Order order, RegisteredCustomer registeredCustomer, Shop shop, double reduction) {
                return reduction;
            }
        });
        this.dq.add(d2);
        this.dq.add(d1);
        this.dq.add(d3);
        this.dq.applyDiscounts(o, null, null);
        assertEquals(8, o.getPriceWithTaxes());
    }
}
