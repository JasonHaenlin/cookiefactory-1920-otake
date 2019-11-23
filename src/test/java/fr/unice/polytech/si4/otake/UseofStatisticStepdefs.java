package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.Status;
import io.cucumber.java8.En;

public class UseofStatisticStepdefs implements En {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie result;
    RecipeBook factory;
    Order order;
    Order order2;
    Order order3;
    Map<Cookie, Float> stat;

    public UseofStatisticStepdefs() {

        Given("a Cookie Factory with some recipes and waiting orders", () -> {
            cookieobj = Recipe.SOOCHOCOLATE.create();
            cookieobj2 = Recipe.DARKTEMPTATION.create();
            cookieobj3 = Recipe.CHOCOCOLALALA.create();
            factory = new RecipeBook();
            order = new Order();
            order2 = new Order();
            order3 = new Order();
            factory.addRecipe(cookieobj);
            factory.addRecipe(cookieobj2);
            factory.addRecipe(cookieobj3);
            order.addCookie(cookieobj);
            order.addCookie(cookieobj2);
            order.addCookie(cookieobj);
            order.addCookie(cookieobj2);
            order2.addCookie(cookieobj);
            order2.addCookie(cookieobj);
            order2.addCookie(cookieobj2);
            order2.addCookie(cookieobj2);
            order3.addCookie(cookieobj3);
            order3.addCookie(cookieobj3);
            order.updateStatus(Status.WAITING);
            order2.updateStatus(Status.WAITING);
            order3.updateStatus(Status.WAITING);

        });

        When("Billy get the Statistic", () -> {
            this.stat = this.factory.getStatistic();
        });

        Then("The lowest percentage but nevertheless higher than 0 corresponds to the least ordered cookie", () -> {
            Float perc = (float) 100;
            for (Map.Entry<Cookie, Float> entry : stat.entrySet()) {
                if (entry.getValue() < perc) {
                    perc = entry.getValue();
                }
            }
            for (Map.Entry<Cookie, Float> entry : stat.entrySet()) {
                if (entry.getValue() == perc) {
                    result = entry.getKey();
                }
            }

            assertTrue(result.equals(cookieobj3));

        });
    }

}
