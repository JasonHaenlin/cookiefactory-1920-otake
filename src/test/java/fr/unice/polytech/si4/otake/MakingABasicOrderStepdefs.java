package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.order.Order;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.AppointmentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.BuildStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.PaymentStep;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.SimpleDate;
import fr.unice.polytech.si4.otake.helper.HelperBasic;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

public class MakingABasicOrderStepdefs implements En {

    Order orderobj;
    HelperRecipe helper;
    ParentCompany parent;
    Shop selectedShop;
    RecipeBook recipes;
    Shop shop;
    double taxes;

    ProductStep prodStep;
    AppointmentStep appStep;
    PaymentStep payStep;
    BuildStep buildStep;
    double fullPrice;

    public MakingABasicOrderStepdefs() {

        Before(() -> {
            parent = new ParentCompany().withDefaultDiscount();
        });

        Given("I want to make an order without any account", () -> {
            recipes = parent.getRecipeBook();
            helper = new HelperRecipe(recipes);
            parent.addShop("Nice", "NiceCookie");
            parent.addShop("valbonne", "ValbonneCookie");
            parent.getShops().forEach(s -> {
                s.setTaxes(taxes);
                helper.addToStorage(s.getStorage(), 50);
            });
        });

        When("I retrieve the list of recipes", () -> {
            recipes.addRecipe(helper.getChocolalala());
            recipes.addRecipe(helper.getDarkTemptation());
            recipes.addRecipe(helper.getSoooChocolate());
        });

        Then("I can see them", () -> {
            assertFalse(recipes.getCookies().isEmpty());
        });

        And("add {int} named {string} cookies", (Integer amount, String name) -> {
            Cookie cookie = recipes.getCookie(name);
            prodStep = OrderStepBuilder.newOrder().addProduct(cookie, amount);
            fullPrice = HelperBasic.increaseWithRatio(cookie.getPrice() * amount, taxes);
        });

        When("I validate my order", () -> {
            appStep = prodStep.validateBasket();
        });

        Then("I can retrieve the list of shop", () -> {
            List<Shop> shops = parent.getShops();
            assertFalse(shops.isEmpty());
        });

        And("set an appointment for a shop in {string}", (String location) -> {
            shop = parent.getShopByTerms(location, "").get(0);
            shop.getSchedule();
            payStep = appStep.setAppointment(new SimpleDate(15, 10, 2019, shop.getSchedule().getClosingHour() - 2, 0))
                    .noCode().withoutAccount();
        });

        When("validate my paiement", () -> {
            buildStep = payStep.validatePayment();
        });

        Then("the order is correctly build without any discounts", () -> {
            Order o = buildStep.build(shop);
            assertNotNull(o);
            assertEquals(fullPrice, o.getPriceWithTaxes());
        });

    }

}
