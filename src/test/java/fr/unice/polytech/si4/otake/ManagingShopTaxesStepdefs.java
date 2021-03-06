package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class ManagingShopTaxesStepdefs implements En {

	Shop myShop;
	Order order;
	HelperRecipe helper = new HelperRecipe(new RecipeBook());
	Storage storage;

	public ManagingShopTaxesStepdefs() {

		Given("the shop {string} of {string} has taxes of {double}", (String name, String city, Double taxes) -> {
			myShop = new Shop("Nice", "Nice granny cookie", new ParentCompany()).withCustomTaxes(taxes);
			helper.addToStorage(myShop.getStorage(), 1000);
		});

		When("a customer makes an order of {int} of his favourite cookie", (Integer nbOfFavCookie) -> {
			order = OrderStepBuilder.newOrder().addProduct(helper.getDarkTemptation(), nbOfFavCookie).validateBasket()
					.setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment()
					.build(myShop);
		});

		Then("the price is calculated according to the shop taxes policy", () -> {
			assertEquals(myShop.getTaxes() * order.getPriceWithoutTaxes() + order.getPriceWithoutTaxes(),
					order.applyTaxes(myShop.getTaxes()), 0.001);
		});

		When("the store manager wants to change the store taxes to {double}", (Double newTaxes) -> {
			myShop.setTaxes(newTaxes);
		});

		And("a customer order {int} cookies", (Integer nbCookies) -> {
			order = OrderStepBuilder.newOrder().addProduct(helper.getDarkTemptation(), nbCookies).validateBasket()
					.setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment()
					.build(myShop);
		});

		Then("the new taxes applies to the cookies ordering", () -> {
			assertNotEquals(0.001, myShop.getTaxes());
			assertNotEquals(0.001 * order.getPriceWithoutTaxes() + order.getPriceWithoutTaxes(),
					order.applyTaxes(myShop.getTaxes()));
			assertEquals(myShop.getTaxes() * order.getPriceWithoutTaxes() + order.getPriceWithoutTaxes(),
					order.applyTaxes(myShop.getTaxes()));
		});

	}
}
