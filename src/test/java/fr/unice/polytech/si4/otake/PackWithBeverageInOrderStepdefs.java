package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.BuildStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.Beverage;
import fr.unice.polytech.si4.otake.cookiefactory.product.Pack;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType;
import fr.unice.polytech.si4.otake.cookiefactory.product.PackType.TypeSize;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

/**
 * PackWithBeverageInOrderStepdefs
 */
public class PackWithBeverageInOrderStepdefs implements En {

    ParentCompany parent;
    HelperRecipe helper;
    Shop shop;
    BuildStep buildStep;
    Order o;
    int price1;
    int price2;
    int amount1;
    int amount2;

    public PackWithBeverageInOrderStepdefs() {
        Given("I am making my order", () -> {
            parent = new ParentCompany();
            helper = new HelperRecipe(parent.getRecipes());
            shop = new Shop("city", "name", parent);
            helper.addToStorage(shop.getStorage(), 100);
            parent.addShop(shop);
            shop.setTaxes(0);
        });

        Given("I am making my order with a pack of {int} cookies at {int} € and a beverage at {int} €",
                (Integer amount, Integer price1, Integer price2) -> {
                    this.price1 = price1;
                    this.price2 = price2;
                    parent.getExtra().addPack(new PackType(TypeSize.SMALL, amount, price1));
                    Pack p = parent.getExtra().createPackIfPossible(helper.getDarkTemptation(), amount,
                            new Beverage("milk chocolate", price2));
                    this.buildStep = OrderStepBuilder.newOrder().addProduct(p).validateBasket()
                            .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount()
                            .validatePayment();
                });

        When("I am validating my order", () -> {
            o = this.buildStep.build(shop);
        });

        Then("I should have paid for my pack only", () -> {
            assertEquals(price1 + (price2 / 2), o.getPriceWithTaxes());
        });

        Given("I am making my order with {int} cookies a beverage and {int} more cookies",
                (Integer amount1, Integer amount2) -> {
                    this.amount1 = amount1;
                    this.amount2 = amount2;
                    Beverage bev = new Beverage("Bubble tea", 2);
                    parent.getExtra().addPack(new PackType(TypeSize.SMALL, amount1, 15));
                    this.buildStep = OrderStepBuilder.newOrder().addProduct(helper.getChocolalala(), amount1)
                            .addProduct(helper.getDarkTemptation(), amount2).addProduct(bev).validateBasket()
                            .setAppointment(new SimpleDate("00-00-00 13:00")).noCode().withoutAccount()
                            .validatePayment();
                });

        Then("I should have paid for my pack and for 5 other cookies", () -> {
            assertEquals(16 + helper.getDarkTemptation().getPrice() * amount2, o.getPriceWithTaxes());
        });

    }
}
