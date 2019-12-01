package fr.unice.polytech.si4.otake;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.RecipeBook;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Recipe;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import io.cucumber.java8.En;

import java.util.Set;

public class ShopAvailabiltiesStepdefs implements En {

    ParentCompany company;

    public ShopAvailabiltiesStepdefs(){

            Given("",()->{});
            When("",()->{});
            And("",()->{});
            Then("",()->{});

            Given("shops containing products and ingredients",()->{
                company = new ParentCompany();
                RecipeBook s1 = new RecipeBook();
                RecipeBook s2 = new RecipeBook();
                RecipeBook s3 = new RecipeBook();
                RecipeBook s4 = new RecipeBook();

                s1.addRecipe(Recipe.DARKTEMPTATION.create());
                s2.addRecipe(Recipe.SOOCHOCOLATE.create());
                s3.addRecipe(Recipe.CHOCOCOLALALA.create());

                s4.addRecipe(Recipe.DARKTEMPTATION.create());
                s4.addRecipe(Recipe.SOOCHOCOLATE.create());
                s4.addRecipe(Recipe.CHOCOCOLALALA.create());

                company.addShop(new Shop("Valbonne", 0.3,"bonne cookie", 10, 19,s1));
                company.addShop(new Shop("Antibes",0.3,"best cookie",9, 20, s2));
                company.addShop(new Shop("Biot",0.3,"cookie bro", 9, 19,s3));
                company.addShop(new Shop("Nice",0.3,"nice cookie", 8, 22,s4));
            });
    }
}
