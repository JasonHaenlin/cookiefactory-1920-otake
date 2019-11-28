package fr.unice.polytech.si4.otake.cookiefactory.order;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.order.exception.BadAppointmentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.NoProductRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

/**
 * OrderStepBuilderTest
 */
public class OrderStepBuilderTest {

    Shop s;
    Product p;

    @Before
    public void init() {
        s = new Shop("city", 5, "name", 10, 19, null);
        p = Recipe.CHOCOCOLALALA.create();
    }

    @Test
    public void goodOrderTest() {
        // @formatter:off
        Order o = OrderStepBuilder
                    .newOrder()
                    .addProduct(p)
                    .addProduct(p, 10)
                    .validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00"))
                    .withCode("CODE")
                    .validatePayment()
                    .build(s);
        // @formatter:on
        assertNotNull(o);
    }

    @Test
    public void badOrdertest() {
        try {
            // @formatter:off
            Order o = OrderStepBuilder
                        .newOrder()
                        .addProduct(p)
                        .addProduct(p, 10)
                        .validateBasket()
                        .setAppointment(new SimpleDate("00-00-00 09:00"))
                        .noCode()
                        .validatePayment()
                        .build(s);
            fail("No BadAppointmentRuntimeException thrown");
        } catch (BadAppointmentRuntimeException e) {
            assertTrue(true);
        }
        try {
            // @formatter:off
            Order o = OrderStepBuilder
                        .newOrder()
                        .validateBasket()
                        .setAppointment(new SimpleDate("00-00-00 15:00"))
                        .noCode()
                        .validatePayment()
                        .build(s);
            fail("No NoProductRuntimeException thrown");
        } catch (NoProductRuntimeException e) {
            assertTrue(true);
        }
    }
}
