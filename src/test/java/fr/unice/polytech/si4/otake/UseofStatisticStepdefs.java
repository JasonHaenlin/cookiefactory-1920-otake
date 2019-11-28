package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import io.cucumber.java8.En;

public class UseofStatisticStepdefs implements En {

    Cookie c1;
    Cookie c2;
    Cookie c3;
    Cookie result;
    RecipeBook factory;
    Order o1;
    Order o2;
    Order o3;
    Map<Cookie, Double> stat;
    Shop s;

    public UseofStatisticStepdefs() {
        Given("a Cookie Factory with some recipes and waiting orders", () -> {
            s = new Shop("city", "name", null);
            c1 = Recipe.SOOCHOCOLATE.create();
            c2 = Recipe.DARKTEMPTATION.create();
            c3 = Recipe.CHOCOCOLALALA.create();
            factory = new RecipeBook();
            o1 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().validatePayment().build(s);
            o2 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().validatePayment().build(s);
            o3 = OrderStepBuilder.newOrder().addProduct(c3, 2).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().validatePayment().build(s);
            factory.addRecipe(c1);
            factory.addRecipe(c2);
            factory.addRecipe(c3);
            s.addOrder(o1);
            s.addOrder(o2);
            s.addOrder(o3);
            s.getNextOrder();
            s.getNextOrder();
            s.getNextOrder();
            o1.retrieved();
            o2.retrieved();
            o3.retrieved();
        });

        When("Billy get the Statistic", () -> {
            this.stat = this.factory.getStatistic();
        });
        Then("The lowest percentage but nevertheless higher than 0 corresponds to the least ordered cookie", () -> {
            Double perc = 100.;
            for (Map.Entry<Cookie, Double> entry : stat.entrySet()) {
                if (entry.getValue() < perc) {
                    perc = entry.getValue();
                }
            }
            for (Map.Entry<Cookie, Double> entry : stat.entrySet()) {
                if (entry.getValue() == perc) {
                    result = entry.getKey();
                }
            }

            assertTrue(result.equals(c3));
        });
    }

}
