package fr.unice.polytech.si4.otake.cookiefactory;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Storage;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

public class cookiefactoryTest {

    Cookie c1;
    Cookie c2;
    Cookie c3;
    RecipeBook factory;
    Order o1;
    Order o2;
    Order o3;
    Map<Cookie, Double> stat;
    ParentCompany pc = new ParentCompany();
    Storage storage;

    @Before
    public void factoryCreation() {
        Shop s = new Shop("city", "name", pc);
        factory = pc.getRecipes();
        HelperRecipe helper = new HelperRecipe(factory);
        storage = s.getStorage();
        storage.addStock(helper.chewy, 200);
        storage.addStock(helper.crunchy, 200);
        storage.addStock(helper.choco, 200);
        storage.addStock(helper.mixed, 200);
        storage.addStock(helper.topped, 200);
        storage.addStock(helper.milkChoco, 200);
        storage.addStock(helper.whiteChoco, 200);
        storage.addStock(helper.cinnamon, 200);
        storage.addStock(helper.vanilla, 200);
        c1 = helper.getSoooChocolate();
        c2 = helper.getDarkTemptation();
        c3 = helper.getChocolalala();
        o1 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment().build(s);
        o2 = OrderStepBuilder.newOrder().addProduct(c1, 2).addProduct(c2, 2).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment().build(s);
        o3 = OrderStepBuilder.newOrder().addProduct(c3, 2).validateBasket()
                .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount().validatePayment().build(s);
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
