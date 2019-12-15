package fr.unice.polytech.si4.otake.cookiefactory.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

public class shopTest {

	Shop spyShop;
	HelperRecipe helper = new HelperRecipe(new RecipeBook());
	Storage storage;

	@Before
	public void shopCreation() {
		spyShop = spy(new Shop("Antibes", "Antibes-Cookie", new ParentCompany()));
		storage = spyShop.getStorage();
		storage.addStock(helper.chewy, 200);
		storage.addStock(helper.crunchy, 200);
		storage.addStock(helper.choco, 200);
		storage.addStock(helper.mixed, 200);
		storage.addStock(helper.topped, 200);
		storage.addStock(helper.milkChoco, 200);
		storage.addStock(helper.whiteChoco, 200);
		storage.addStock(helper.cinnamon, 200);
		storage.addStock(helper.vanilla, 200);
	}

	@Test
	public void getAffluenceTest() {
		Cookie cookie = helper.getChocolalala();

		Order order1 = OrderStepBuilder.newOrder().addProduct(cookie).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 15:00")).noCode().withoutAccount().validatePayment()
				.build(spyShop);

		Order order2 = OrderStepBuilder.newOrder().addProduct(cookie).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 15:00")).noCode().withoutAccount().validatePayment()
				.build(spyShop);

		Order order3 = OrderStepBuilder.newOrder().addProduct(cookie).validateBasket()
				.setAppointment(new SimpleDate("00-00-00 17:00")).noCode().withoutAccount().validatePayment()
				.build(spyShop);

		when(spyShop.verifyRetrieveDate(order1)).thenReturn(true);
		when(spyShop.verifyRetrieveDate(order2)).thenReturn(true);
		when(spyShop.verifyRetrieveDate(order3)).thenReturn(true);

		spyShop.addOrder(order1);
		spyShop.addOrder(order2);
		spyShop.addOrder(order3);

		spyShop.getNextOrder();
		spyShop.getNextOrder();
		spyShop.getNextOrder();

		spyShop.retrieved(order1.getId());
		spyShop.retrieved(order3.getId());

		assertEquals(1, spyShop.getAffluence().get(15).intValue());
		assertEquals(1, spyShop.getAffluence().get(17).intValue());

		spyShop.retrieved(order2.getId());

		assertEquals(2, spyShop.getAffluence().get(15).intValue());
		assertEquals(0, spyShop.getAffluence().get(14).intValue());
	}

}
