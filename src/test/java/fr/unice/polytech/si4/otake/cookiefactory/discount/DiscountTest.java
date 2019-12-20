package fr.unice.polytech.si4.otake.cookiefactory.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.RegisteredCustomer;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

/**
 * DiscountTest
 */
public class DiscountTest {

	Discount d1;
	Order o;
	Shop s = new Shop("city", "name", new ParentCompany());
	HelperRecipe helper;
	ParentCompany pc;
	Cookie c;
	Cookie c2;
	Product p;
	Product p2;
	Storage storage;

	@Before
	public void init() {
		this.pc = new ParentCompany();
		this.helper = new HelperRecipe(pc.getRecipes());
		storage = s.getStorage();

		storage.addStock(helper.chewy, 1000);
		storage.addStock(helper.crunchy, 1000);
		storage.addStock(helper.choco, 1000);
		storage.addStock(helper.mixed, 1000);
		storage.addStock(helper.topped, 1000);
		storage.addStock(helper.milkChoco, 1000);
		storage.addStock(helper.whiteChoco, 1000);
		storage.addStock(helper.cinnamon, 1000);
		storage.addStock(helper.vanilla, 1000);

		c = helper.getChocolalala();
		c2 = helper.getDarkTemptation();
		p = helper.getChocolalala();
		p2 = helper.getDarkTemptation();
	}

	@Test
	public void discountCodeTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.basic());
		this.o = OrderStepBuilder.newOrder().addProduct(helper.getChocolalala()).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").withoutAccount().validatePayment()
				.build(s);
		this.o.setPriceWithTaxes(10);
		double red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(9, o.getPriceWithTaxes());
		this.o = OrderStepBuilder.newOrder().addProduct(helper.getChocolalala()).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("NOPE").withoutAccount().validatePayment()
				.build(s);
		this.o.setPriceWithTaxes(10);
		red = this.d1.applyIfEligible(o, null, null);
		this.o.applyDiscount(red);
		assertEquals(10, o.getPriceWithTaxes());
	}

	@Test
	public void discountHourTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.hour(), Discount.Behaviour.basic());
		this.o = OrderStepBuilder.newOrder().addProduct(helper.getChocolalala()).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 19:00")).noCode().withoutAccount().validatePayment().build(s);
		this.o.setPriceWithTaxes(10);
		double red = this.d1.applyIfEligible(o, null, s);
		this.o.applyDiscount(red);
		assertEquals(9, o.getPriceWithTaxes());
	}

	@Test
	public void discountSeniorityTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.seniority(), Discount.Behaviour.products(10));
		this.o = OrderStepBuilder.newOrder().addProduct(c, 5).addProduct(c2, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment().build(s);

		this.o.setPriceWithTaxes(10);

		RegisteredCustomer rc = new RegisteredCustomer("1", true);
		double red = this.d1.applyIfEligible(o, rc, null);
		this.o.applyDiscount(red);
		assertEquals(10, o.getPriceWithTaxes());
	}

	@Test
	public void discountProductsTest() {
		this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.products(100));
		this.o = OrderStepBuilder.newOrder().addProduct(c, 5).addProduct(c2, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").withoutAccount().validatePayment()
				.build(s);
		this.o.setPriceWithTaxes(10);
		double red = this.d1.applyIfEligible(o, null, new Shop("city", "name", new ParentCompany()));
		this.o.applyDiscount(red);
		assertEquals(10, o.getPriceWithTaxes());

		this.o = OrderStepBuilder.newOrder().addProduct(c, 55).addProduct(c2, 55).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").withoutAccount().validatePayment()
				.build(s);
		this.o.setPriceWithTaxes(10);
		red = this.d1.applyIfEligible(o, null, new Shop("city", "name", new ParentCompany()));
		this.o.applyDiscount(red);
		assertEquals(9, o.getPriceWithTaxes());
	}

	@Test
	public void discrountElligibleProductsTest() {
		RecipeBook rc = new RecipeBook();
		rc.addRecipe(c);
		this.d1 = new Discount(false, 0.1, Discount.Trigger.code("CODE"), Discount.Behaviour.elligibleCookies(rc));
		this.o = OrderStepBuilder.newOrder().addProduct(p, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").withoutAccount().validatePayment()
				.build(s);
		double price = (c.getPrice() * 5);
		this.o.setPriceWithTaxes(price);
		double red = this.d1.applyIfEligible(o, null, new Shop("city", "name", new ParentCompany()));
		this.o.applyDiscount(red);
		assertEquals(price - (price * 0.1), o.getPriceWithTaxes());

		this.o = OrderStepBuilder.newOrder().addProduct(p, 5).addProduct(p2, 5).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 13:00")).withCode("CODE").withoutAccount().validatePayment()
				.build(s);
		price = (c.getPrice() * 5) + (c2.getPrice() * 5);
		this.o.setPriceWithTaxes(price);
		red = this.d1.applyIfEligible(o, null, new Shop("city", "name", new ParentCompany()));
		this.o.applyDiscount(red);
		assertEquals(price - (price * (0.1 * c.getPrice() * 5) / price), o.getPriceWithTaxes());
	}
}
