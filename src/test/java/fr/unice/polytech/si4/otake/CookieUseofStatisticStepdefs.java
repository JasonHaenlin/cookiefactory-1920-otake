package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class CookieUseofStatisticStepdefs implements En {

    Cookie c1, c2, c3;
    Order o1, o2, o3;
    Cookie result;
    RecipeBook factory;
    Map<Cookie, Double> stat;
    Double perc;
    Shop s;

    public CookieUseofStatisticStepdefs() {

        Before(() -> {
            s = new Shop("city", "name", new ParentCompany());
            factory = new RecipeBook();
            HelperRecipe helper = new HelperRecipe(factory);
            helper.addToStorage(s.getStorage(), 1000);
            c1 = helper.getSoooChocolate();
            c2 = helper.getDarkTemptation();
            c3 = helper.getChocolalala();
            o1 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment()
                    .build(s);
            o2 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment()
                    .build(s);
            o3 = OrderStepBuilder.newOrder().addProduct(c3, 2).validateBasket()
                    .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment()
                    .build(s);
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

        Given("I get the statistics from the factory", () -> {
            this.stat = this.factory.getStatistic();
        });

        When("I see the lowest percentage", () -> {
            perc = 100.;
            for (Map.Entry<Cookie, Double> entry : stat.entrySet()) {
                if (entry.getValue() < perc) {
                    perc = entry.getValue();
                }
            }
        });

        Then("It should be the least ordered", () -> {
            for (Map.Entry<Cookie, Double> entry : stat.entrySet()) {
                if (entry.getValue() == perc) {
                    result = entry.getKey();
                }
            }
            assertEquals(c3, result);
        });
    }

}
