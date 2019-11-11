package fr.unice.polytech.si4.otake.cookiefactory;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;

public class cookiefactoryTest {

    Cookie cookieobj;
    Cookie cookieobj2;
    Cookie cookieobj3;
    Cookie result;
    CookieFactory factory;
    Order order;
    Order order2;
    Order order3;
    Map<Cookie, Float> stat;

    @Before
    public void factoryCreation() {
        cookieobj = Recipe.SOOCHOCOLATE.create();
        cookieobj2 = Recipe.DARKTEMPTATION.create();
        cookieobj3 = Recipe.CHOCOCOLALALA.create();
        factory = new CookieFactory();
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
    }

    @Test
    public void getStatisticTest() {
        this.stat = this.factory.getStatistic();
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
        System.out.println(stat.get(cookieobj));
        System.out.println(stat.get(cookieobj2));
        System.out.println(stat.get(cookieobj3));
        assertTrue(result.equals(cookieobj3));
    }

}
