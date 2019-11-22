package fr.unice.polytech.si4.otake.cookiefactory.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

/**
 * DiscountTest
 */
public class DiscountTest {

    Discount d1;
    Order o;

    @Before
    public void init() {
        this.o = new Order();
        this.o.setPriceWithTaxes(10);
    }

    @Test
    public void disountCodeTest() {
        this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.basic());
        this.o.setCode("CODE");
        float red = (float) this.d1.applyIfEligible(o, null, null);
        this.o.applyDiscount(red);
        assertEquals((float) 9, o.getPriceWithTaxes());
        this.o.setCode("NOPE");
        red = (float) this.d1.applyIfEligible(o, null, null);
        this.o.applyDiscount(red);
        assertEquals((float) 9, o.getPriceWithTaxes());
    }

    @Test
    public void discountHourTest() {
        this.d1 = new Discount(false, 0.1, Discount.Trigger.hour(), Discount.Behaviour.basic());
        Shop s = new Shop("city", 0, "name", 8, 20, null);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 19);
        this.o.setAppointmentDate(c);
        float red = (float) this.d1.applyIfEligible(o, null, s);
        this.o.applyDiscount(red);
        assertEquals((float) 9, o.getPriceWithTaxes());
    }

    @Test
    public void discountSeniorityTest() {
        this.d1 = new Discount(false, 0.1, Discount.Trigger.seniority(), Discount.Behaviour.products(10));
        Cookie c = Recipe.CHOCOCOLALALA.create();
        for (int i = 0; i < 10; i++) {
            this.o.addCookie(c);
        }
        RegisteredCustomer rc = new RegisteredCustomer("1", true);
        float red = (float) this.d1.applyIfEligible(o, rc, null);
        this.o.applyDiscount(red);
        assertEquals((float) 10, o.getPriceWithTaxes());
    }
}
