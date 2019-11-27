package fr.unice.polytech.si4.otake.cookiefactory;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;

public class cookiefactoryTest {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie result;
    RecipeBook factory;
    Order order;
    Order order2;
    Order order3;
    Map<Cookie, Double> stat;

    // TODO
    @Before
    public void factoryCreation() {
        // cookieobj = Recipe.SOOCHOCOLATE.create();
        // cookieobj2 = Recipe.DARKTEMPTATION.create();
        // cookieobj3 = Recipe.CHOCOCOLALALA.create();
        // factory = new RecipeBook();
        // order = new Order();
        // order2 = new Order();
        // order3 = new Order();
        // factory.addRecipe(cookieobj);
        // factory.addRecipe(cookieobj2);
        // factory.addRecipe(cookieobj3);
        // order.addCookie(cookieobj);
        // order.addCookie(cookieobj2);
        // order.addCookie(cookieobj);
        // order.addCookie(cookieobj2);
        // order2.addCookie(cookieobj);
        // order2.addCookie(cookieobj);
        // order2.addCookie(cookieobj2);
        // order2.addCookie(cookieobj2);
        // order3.addCookie(cookieobj3);
        // order3.addCookie(cookieobj3);
        // order.updateStatus(Status.WAITING);
        // order2.updateStatus(Status.WAITING);
        // order3.updateStatus(Status.WAITING);
    }

    @Ignore
    @Test
    public void getStatisticTest() {
        this.stat = this.factory.getStatistic();
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
        assertTrue(result.equals(cookieobj3));
    }

}
