package fr.unice.polytech.si4.otake;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.cinema.Cinema;
import fr.unice.polytech.si4.otake.cookiefactory.cinema.CinemaAPI;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import io.cucumber.java8.En;

public class ShopTicketCinemaStepdefs implements En {

    Shop shop;
    Shop shop2;
    // ; new Shop("city", "name", new ParentCompany());
    CinemaAPI cinemaapi = CinemaAPI.getInstance();
    Cinema cinema1;

    public ShopTicketCinemaStepdefs() {

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
            assertTrue(shop.checkTicket("pathemaiscafe:ZG42F2"));
        });

        When("the customer enter in the shop named {string} with no partnership", (String name) -> {
            shop2 = new Shop("city", name, new ParentCompany());
        });

        Then("no cookies is given to the customer", () -> {
            assertFalse(shop.checkTicket(""));

        });

        When("a customer try to get free cookies with an old tickets", () -> {

        });

        Then("no cookies is also given to the customer", () -> {
            assertFalse(shop.checkTicket("pathemaiscafe:ZG42F2"));
        });

    }

}
