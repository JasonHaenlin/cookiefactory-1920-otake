package fr.unice.polytech.si4.otake.cookiefactory.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.BadAppointmentRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.NoProductRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.order.exception.NotEnoughIngredientsRuntimeException;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

/**
 * OrderStepBuilderTest
 */
public class OrderStepBuilderTest {

    Shop s;
    Product p;
    Storage storage;
    HelperRecipe helper;

    @Before
    public void init() {
        s = new Shop("city", "name", new ParentCompany()).withSchedule(10, 19);
        storage = s.getStorage();

        RecipeBook rc = new RecipeBook();
        helper = new HelperRecipe(rc);

        p = new HelperRecipe(new ParentCompany().getRecipeBook()).getChocolalala();

    }

    @Test
    public void goodOrderTest() {
        // @formatter:off
        storage.addStock(helper.crunchy, 11);
        storage.addStock(helper.choco, 11);
        storage.addStock(helper.mixed, 11);
        storage.addStock(helper.whiteChoco, 33);
        storage.addStock(helper.vanilla, 11);
        Order o = OrderStepBuilder
                    .newOrder()
                    .addProduct(p)
                    .addProduct(p, 10)
                    .validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00"))
                    .withCode("CODE")
                    .withoutAccount()
                    .validatePayment()
                    .build(s);
        // @formatter:on
        assertNotNull(o);
    }

    @Test
    public void badOrdertest() {
        try {
            // @formatter:off
            storage.addStock(helper.crunchy, 11);
            storage.addStock(helper.choco, 11);
            storage.addStock(helper.mixed, 11);
            storage.addStock(helper.whiteChoco, 33);
            storage.addStock(helper.vanilla, 11);
            OrderStepBuilder
                        .newOrder()
                        .addProduct(p)
                        .addProduct(p, 10)
                        .validateBasket()
                        .setAppointment(new SimpleDate("00-00-00 09:00"))
                        .noCode()
                        .withoutAccount()
                        .validatePayment()
                        .build(s);
            fail("No BadAppointmentRuntimeException thrown");
        } catch (BadAppointmentRuntimeException e) {
            assertTrue(true);
        }
        try {
            // @formatter:off
            OrderStepBuilder
                        .newOrder()
                        .validateBasket()
                        .setAppointment(new SimpleDate("00-00-00 15:00"))
                        .noCode()
                        .withoutAccount()
                        .validatePayment()
                        .build(s);
            fail("No NoProductRuntimeException thrown");
        } catch (NoProductRuntimeException e) {
            assertTrue(true);
        }
    }
    @Test
    public void badProductQuantityOrdertest() {

        // @formatter:off
        storage.addStock(helper.crunchy, 11);
        storage.addStock(helper.choco, 11);
        storage.addStock(helper.mixed, 11);
        storage.addStock(helper.whiteChoco, 33);
        storage.addStock(helper.vanilla, 10);
        Order o = null;
        try {
            o = OrderStepBuilder
                    .newOrder()
                    .addProduct(p)
                    .addProduct(p, 10)
                    .validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00"))
                    .withCode("CODE")
                    .withoutAccount()
                    .validatePayment()
                    .build(s);
            fail();
        } catch (NotEnoughIngredientsRuntimeException e) {
            assertNull(o);
        }
        storage.addStock(helper.vanilla, 2);
        Order o2 = OrderStepBuilder
                .newOrder()
                .addProduct(p)
                .addProduct(p, 10)
                .validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00"))
                .withCode("CODE")
                .withoutAccount()
                .validatePayment()
                .build(s);
        assertNotNull(o2);
    }
}
