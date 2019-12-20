package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertEquals;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.cinema.Cinema;
import fr.unice.polytech.si4.otake.cookiefactory.cinema.CinemaAPI;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import io.cucumber.java8.En;

public class CheckCinemaTicketStepdefs implements En {

    Shop shop;
    Shop shop2;
    CinemaAPI cinemaapi = CinemaAPI.getInstance();
    Cinema cinema1;
    boolean result;

    public CheckCinemaTicketStepdefs() {

        Given("a customer who went to the cinema today and want 2 cookies of the day at the shop", () -> {
            cinema1 = new Cinema("pathelaiscafe");
            cinema1.addTicket("ZG42F2");
        });

        When("the customer enter in the shop named {string} near the cinema", (String name) -> {
            shop = new Shop("city", name, new ParentCompany());
            cinemaapi.addCinema(cinema1);
            shop.addCinema("pathemaiscafe");
        });

        Then("the customer get {int} free cookies", (Integer deux) -> {
            // assertTrue(shop.checkTicket("pathemaiscafe:ZG42F2"));
            assertEquals(2, shop.getFreeCookiesOfTheDay("pathemaiscafe:ZG42F2").size());
        });

        When("the customer enter in the shop named {string} with no partnership", (String name) -> {
            shop2 = new Shop("city", name, new ParentCompany());
        });

        Then("no cookies is given to the customer", () -> {
            assertEquals(0, shop2.getFreeCookiesOfTheDay("pathemaiscafe:ZG42F2").size());
        });

        When("a customer try to get free cookies with an old tickets", () -> {
            result = shop.checkTicket("pathemaiscafe:ZG42F2");
        });

        Then("the ticket is refused", () -> {
            assertEquals(0, shop.getFreeCookiesOfTheDay("pathemaiscafe:ZG42F2").size());
        });

    }

}
