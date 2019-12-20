package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder;
import fr.unice.polytech.si4.otake.cookiefactory.order.OrderStepBuilder.ProductStep;
import fr.unice.polytech.si4.otake.cookiefactory.product.Product;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.shop.exception.NoShopHasEnoughIngredient;
import fr.unice.polytech.si4.otake.cookiefactory.shop.exception.NoShopOpenedForTheProduct;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.*;

public class ShopAvailabiltiesStepdefs implements En {

    ParentCompany company;
    Cookie cookie;
    ProductStep basket;
    HelperRecipe hr;

    public ShopAvailabiltiesStepdefs() {

        Given("shops containing products and ingredients",()->{
            company = new ParentCompany();
            company.getRecipes().withDefaultIngredient();
            hr = new HelperRecipe(company.getRecipes());
            company.getRecipes().addRecipe(hr.getChocolalala());
            company.getRecipes().addRecipe(hr.getDarkTemptation());
            company.getRecipes().addRecipe(hr.getSoooChocolate());

            Shop shop1 = new Shop("Valbonne", "cookie valbonne", company).withSchedule(10,
                    19);
            Shop shop2 = new Shop("Antibes","cookie antibes",company).withSchedule(9, 20);
            Shop shop3 = new Shop("Biot","cookie biot", company).withSchedule(9, 19);
            Shop shop4 = new Shop("Nice","nice cookie", company).withSchedule(8, 22);

            addIngredientsForChocolalala(shop1,0);
            addIngredientsForDarkTemptation(shop2, 2);
            addIngredientForSoChocolate(shop3,2);

            //SHOP4 can only provide 1 of the cookie type
            addIngredientsForChocolalala(shop4, 0);
            addIngredientsForDarkTemptation(shop4, 1);
            addIngredientForSoChocolate(shop4, 1);

            company.addShop(shop1);
            company.addShop(shop2);
            company.addShop(shop3);
            company.addShop(shop4);
        });

        When("the customer chose a cookie {string}", (String e)->{
            cookie = company.getRecipes().getCookie(e);
        });

        And("the product is not available in any shop", ()-> {
            assertFalse(
                    company.couldAShopSatisfyThisCookie(cookie)
            );
        });

        And("hour is {int} and the only shop satisfying the product is closed",(Integer h)->{
            assertFalse(company.isThereAnOpenShopThatCouldMakeThisCookie(h,cookie));
        });

        Then("the customer can't add {string} for sold-out reason",(String e) ->{
            basket = OrderStepBuilder.newOrder();
            assertThrows(NoShopHasEnoughIngredient.class, () -> {
                basket.checkAvailabilityFromShops(company,12,cookie);
            });
        });

        Then("the customer can't add {string} at {int} for hour reason",(String e, Integer i) ->{
            basket = OrderStepBuilder.newOrder();
            assertThrows(NoShopOpenedForTheProduct.class, () -> {
                basket.checkAvailabilityFromShops(company,i,cookie);
            });
        });
    }

    public void addIngredientsForChocolalala(Shop s, int qtt){
        s.getStorage().addStock(hr.crunchy,qtt);
        s.getStorage().addStock(hr.choco, qtt);
        s.getStorage().addStock(hr.mixed, qtt);
        s.getStorage().addStock(hr.whiteChoco, qtt);
        s.getStorage().addStock(hr.whiteChoco, qtt);
        s.getStorage().addStock(hr.vanilla, qtt);
    }

    public void addIngredientsForDarkTemptation(Shop s, int qtt){
        s.getStorage().addStock(hr.crunchy, qtt);
        s.getStorage().addStock(hr.choco, qtt);
        s.getStorage().addStock(hr.mixed, qtt);
        s.getStorage().addStock(hr.milkChoco, qtt);
        s.getStorage().addStock(hr.milkChoco, qtt);
        s.getStorage().addStock(hr.whiteChoco, qtt);
        s.getStorage().addStock(hr.cinnamon, qtt);
    }

    public void addIngredientForSoChocolate(Shop s, int qtt){
        s.getStorage().addStock(hr.chewy,qtt);
        s.getStorage().addStock(hr.choco,qtt);
        s.getStorage().addStock(hr.topped,qtt);
        s.getStorage().addStock(hr.milkChoco,qtt);
        s.getStorage().addStock(hr.milkChoco,qtt);
        s.getStorage().addStock(hr.whiteChoco,qtt);
    }
}
