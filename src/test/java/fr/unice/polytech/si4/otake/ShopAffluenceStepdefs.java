package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class ShopAffluenceStepdefs implements En {

    Shop myShop;
    Map<Integer, Integer> statistics;
    HelperRecipe helper = new HelperRecipe(new RecipeBook());
    Storage storage;

    public ShopAffluenceStepdefs() {
        Given("A shop named {string} in {string}", (String name, String city) -> {
            myShop = new Shop(city, name, new ParentCompany());
            storage = myShop.getStorage();
            storage.addStock(helper.chewy, 1000);
            storage.addStock(helper.crunchy, 1000);
            storage.addStock(helper.choco, 1000);
            storage.addStock(helper.mixed, 1000);
            storage.addStock(helper.topped, 1000);
            storage.addStock(helper.milkChoco, 1000);
            storage.addStock(helper.whiteChoco, 1000);
            storage.addStock(helper.cinnamon, 1000);
            storage.addStock(helper.vanilla, 1000);
        });

        And("{int} order retrieved at {int}h and {int} orders retrieved at {int}h",
                (Integer nbOrderSlowHour, Integer slowHour, Integer nbOrderRushHour, Integer rushHour) -> {
                    this.addOrdersAtHour(nbOrderSlowHour, slowHour);
                    this.addOrdersAtHour(nbOrderRushHour, rushHour);
                });

        When("A Manager ask for statistics about his shop", () -> {
            statistics = myShop.getAffluence();
        });

        Then("He can see that there have been less orders at {int}h compared to {int}h",
                (Integer slowHour, Integer rushHour) -> {
                    assertTrue(statistics.get(slowHour) < statistics.get(rushHour));
                });
    }

    private void addOrdersAtHour(int nbOrder, int hour) {
        for (int i = 0; i < nbOrder; i++) {
            Order order = OrderStepBuilder.newOrder().addProduct(helper.getDarkTemptation()).validateBasket()
                    .setAppointment(new SimpleDate("24-12-2019 " + hour + ":00")).noCode().withoutAccount()
                    .validatePayment().build(myShop);

            myShop.addOrder(order);
            myShop.getNextOrder();
            myShop.retrieved(order.getId());
        }
    }
}
