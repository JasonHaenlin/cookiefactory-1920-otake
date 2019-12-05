package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import io.cucumber.java8.En;

public class ShopAffluenceStepdefs implements En {

    Shop myShop;
    Map<Integer, Integer> statistics;

    public ShopAffluenceStepdefs() {
        Given("A shop named {string} in {string}", (String name, String city) -> {
            myShop = new Shop(city, name, null);
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
            Order order = OrderStepBuilder.newOrder().addProduct(Recipe.DARKTEMPTATION.create()).validateBasket()
                    .setAppointment(new SimpleDate("24-12-2019 " + hour + ":00")).noCode().WithoutAccount()
                    .validatePayment().build(myShop);

            myShop.addOrder(order);
            myShop.getNextOrder();
            myShop.retrieved(order.getId());
        }
    }
}
