package fr.unice.polytech.si4.otake.cookiefactory.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
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
	Shop s = new Shop("city", "name", null);
	Cookie c = Recipe.CHOCOCOLALALA.create();
	Cookie c2 = Recipe.DARKTEMPTATION.create();
	Product p = Recipe.CHOCOCOLALALA.create();
	Product p2 = Recipe.DARKTEMPTATION.create();

	@Test
	public void disountCodeTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.basic());
		this.o = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").WithoutAccount().validatePayment()
				.build(new Shop("city", "name", null));
		this.o.setPriceWithTaxes(10);
		double red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(9, o.getPriceWithTaxes());
		this.o = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("NOPE").WithoutAccount().validatePayment()
				.build(new Shop("city", "name", null));
		this.o.setPriceWithTaxes(10);
		red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(10, o.getPriceWithTaxes());
	}

	@Test
	public void discountHourTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.hour(), Discount.Behaviour.basic());
		this.o = OrderStepBuilder.newOrder().addProduct(Recipe.SOOCHOCOLATE.create()).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 19:00")).noCode().WithoutAccount().validatePayment().build(s);
		this.o.setPriceWithTaxes(10);
		double red = this.d1.applyIfEligible(o, null, s);
		this.o.applyDiscount(red);
		assertEquals(9, o.getPriceWithTaxes());
	}

	@Test
	public void discountSeniorityTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.seniority(), Discount.Behaviour.products(10));
		this.o = OrderStepBuilder.newOrder().addProduct(c, 5).addProduct(c2, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).noCode().WithoutAccount().validatePayment().build(s);

		this.o.setPriceWithTaxes(10);

		RegisteredCustomer rc = new RegisteredCustomer("1", true);
		double red = this.d1.applyIfEligible(o, rc, null);
		this.o.applyDiscount(red);
		assertEquals(10, o.getPriceWithTaxes());
	}

	@Test
	public void discrountProductsTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.products(100));
		this.o = OrderStepBuilder.newOrder().addProduct(c, 5).addProduct(c2, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").WithoutAccount().validatePayment()
				.build(s);
		this.o.setPriceWithTaxes(10);
		double red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(10, o.getPriceWithTaxes());

		this.o = OrderStepBuilder.newOrder().addProduct(c, 55).addProduct(c2, 55).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").WithoutAccount().validatePayment()
				.build(s);
		this.o.setPriceWithTaxes(10);
		red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(9, o.getPriceWithTaxes());
	}

	@Test
	public void discrountElligibleProductsTest() {
		List<Cookie> cs = new ArrayList<>();
		cs.add(c);
		this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.elligibleCookies(cs));
		this.o = OrderStepBuilder.newOrder().addProduct(p, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").WithoutAccount().validatePayment()
				.build(s);
		double price = (c.getPrice() * 5);
		this.o.setPriceWithTaxes(price);
		double red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(price - (price * 0.1), o.getPriceWithTaxes());

		this.o = OrderStepBuilder.newOrder().addProduct(p, 5).addProduct(p2, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").WithoutAccount().validatePayment()
				.build(s);
		price = (c.getPrice() * 5) + (c2.getPrice() * 5);
		this.o.setPriceWithTaxes(price);
		red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(price - (price * (0.1 * c.getPrice() * 5) / price), o.getPriceWithTaxes());
	}
}
