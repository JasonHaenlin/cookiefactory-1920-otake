package fr.unice.polytech.si4.otake.cookiefactory;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;

public class cookiefactoryTest {

    Cookie c1;
    Cookie c2;
    Cookie c3;
    RecipeBook factory;
    Order o1;
    Order o2;
    Order o3;
    Map<Cookie, Double> stat;

    @Before
    public void factoryCreation() {
        Shop s = new Shop("city", "name", null);
        c1 = Recipe.SOOCHOCOLATE.create();
        c2 = Recipe.DARKTEMPTATION.create();
        c3 = Recipe.CHOCOCOLALALA.create();
        factory = new RecipeBook();
        o1 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().WithoutAccount().validatePayment().build(s);
        o2 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().WithoutAccount().validatePayment().build(s);
        o3 = OrderStepBuilder.newOrder().addProduct(c3, 2).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().WithoutAccount().validatePayment().build(s);
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
    }

    @Test
    public void getStatisticTest() {
        Cookie result = null;
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
        assertTrue(c3.equals(result));
    }

}
