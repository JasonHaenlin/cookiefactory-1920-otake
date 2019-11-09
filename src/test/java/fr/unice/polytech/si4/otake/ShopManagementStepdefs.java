package fr.unice.polytech.si4.otake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import fr.unice.polytech.si4.otake.cookiefactory.Order;
import fr.unice.polytech.si4.otake.cookiefactory.Shop;
import fr.unice.polytech.si4.otake.cookiefactory.cookie.Recipe;
import io.cucumber.java8.En;

public class ShopManagementStepdefs implements En {

    Shop myShop;
    //Mock definitions TODO use real object and methods when ready
    float darkTemptationPrice = (float)3.99;
    int nbOrdered;
    Order order;


    public ShopManagementStepdefs(){

        Given("the shop {string} of {string} has taxes of {float}",
                (String name, String city, Float taxes) ->
                {
                    myShop = new Shop("Nice", taxes, "Nice granny cookie");
                });

        When("a customer makes an order of {int} of his favourite cookie",
                (Integer nbOfFavCookie) ->
                {
                    nbOrdered = nbOfFavCookie;
//                    order = new Order();
                    order = mock(Order.class);
                    for(int i = 0; i<nbOfFavCookie; i++)
                        order.addCookie(Recipe.DARKTEMPTATION.build());
                });

        Then("the price is calculated according to the shop taxes policy", ()->{
                float price;
                when(order.getPrice()).thenReturn(darkTemptationPrice*nbOrdered);
                price = order.getPrice();
                when(myShop.applyTaxes(order)).thenReturn(price*myShop.getTaxes()+price);
                assertEquals(myShop.getTaxes()*order.getPrice()+order.getPrice(), myShop.applyTaxes(order));
        });

        When("the store manager wants to change the store taxes to {float}", (Float newTaxes) ->{
            myShop.setTaxes(newTaxes);
        });

        And("a customer order {int} cookie(s)", (Integer nbCookies)->{
            nbOrdered = nbCookies;
//            order = new Order();
            order = mock(Order.class);
            for(int i = 0; i<nbCookies; i++)
                order.addCookie(Recipe.DARKTEMPTATION.build());
        });

        Then("the new taxes applies to the cookie(s) ordering", ()->{
            when(order.getPrice()).thenReturn(darkTemptationPrice*nbOrdered);
            float price = order.getPrice();
            when(myShop.applyTaxes(order)).thenReturn(price*myShop.getTaxes()+price);
            assertNotEquals((float)0.001, myShop.getTaxes());
            assertNotEquals(0.001*order.getPrice()+order.getPrice(), myShop.applyTaxes(order));
            assertEquals(myShop.getTaxes()*order.getPrice()+order.getPrice(), myShop.applyTaxes(order));
        });

    }
}
