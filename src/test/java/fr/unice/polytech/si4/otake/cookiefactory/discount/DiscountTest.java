package fr.unice.polytech.si4.otake.cookiefactory.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

/**
 * DiscountTest
 */
public class DiscountTest {

    Discount d1;
    Order o;

    @Test
    public void disountCodeTest() {
        this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.basic());
        this.o = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").validatePayment()
                .build(new Shop("city", "name", null));
        this.o.setPriceWithTaxes(10);
        double red = this.d1.applyIfEligible(o, null, null);
        this.o.applyDiscount(red);
        assertEquals(9, o.getPriceWithTaxes());
        this.o = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).withCode("NOPE").validatePayment()
                .build(new Shop("city", "name", null));
        this.o.setPriceWithTaxes(10);
        red = this.d1.applyIfEligible(o, null, null);
        this.o.applyDiscount(red);
        assertEquals(10, o.getPriceWithTaxes());
    }

    @Test
    public void discountHourTest() {
        this.d1 = new Discount(false, 0.1, Discount.Trigger.hour(), Discount.Behaviour.basic());
        Shop s = new Shop("city", 0, "name", 8, 20, null);
        this.o = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 19:00")).noCode().validatePayment().build(s);
        this.o.setPriceWithTaxes(10);
        double red = this.d1.applyIfEligible(o, null, s);
        this.o.applyDiscount(red);
        assertEquals(9, o.getPriceWithTaxes());
    }

    @Test
    public void discountSeniorityTest() {
        this.d1 = new Discount(false, 0.1, Discount.Trigger.seniority(), Discount.Behaviour.products(10));
        Shop s = new Shop("city", 0, "name", 8, 20, null);
        Cookie c = Recipe.CHOCOCOLALALA.create();
        this.o = OrderStepBuilder.newOrder().addProduct(c, 10).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().validatePayment().build(s);
        this.o.setPriceWithTaxes(10);
        RegisteredCustomer rc = new RegisteredCustomer("1", true);
        double red = this.d1.applyIfEligible(o, rc, null);
        this.o.applyDiscount(red);
        assertEquals(10, o.getPriceWithTaxes());
    }
}
