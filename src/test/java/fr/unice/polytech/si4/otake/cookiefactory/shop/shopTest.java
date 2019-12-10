package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

public class shopTest {

	Shop testShop;
	HelperRecipe helper = new HelperRecipe(new RecipeBook());

	@Before
	public void shopCreation() {
		testShop = new Shop("Antibes", "Antibes-Cookie", new ParentCompany());
	}

	@Test
	public void getAffluenceTest() {
		Cookie cookie = helper.getChocolalala();

		Order order1 = OrderStepBuilder.newOrder().addProduct(cookie).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 15:00")).noCode().withoutAccount().validatePayment()
				.build(testShop);

		Order order2 = OrderStepBuilder.newOrder().addProduct(cookie).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 15:00")).noCode().withoutAccount().validatePayment()
				.build(testShop);

		Order order3 = OrderStepBuilder.newOrder().addProduct(cookie).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 17:00")).noCode().withoutAccount().validatePayment()
				.build(testShop);

		testShop.addOrder(order1);
		testShop.addOrder(order2);
		testShop.addOrder(order3);

		testShop.getNextOrder();
		testShop.getNextOrder();
		testShop.getNextOrder();

		testShop.retrieved(order1.getId());
		testShop.retrieved(order3.getId());

		assertEquals(1, testShop.getAffluence().get(15).intValue());
		assertEquals(1, testShop.getAffluence().get(17).intValue());

		testShop.retrieved(order2.getId());

		assertEquals(2, testShop.getAffluence().get(15).intValue());
		assertEquals(0, testShop.getAffluence().get(14).intValue());
	}

}
