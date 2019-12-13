package fr.unice.polytech.si4.otake.cookiefactory.cinema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

public class checkTicketTest {

    @Test
    public void checkTicketMethodeTest() {
        Shop shop = new Shop("city", "name", new ParentCompany());
        CinemaAPI cinemaapi = CinemaAPI.getInstance();
        Cinema cinema1 = new Cinema("pathepourchat");
        Cinema cinema2 = new Cinema("pathelaiscafe");

        cinemaapi.addCinema(cinema1);
        cinemaapi.addCinema(cinema2);

        cinema1.addTicket("EB65S8");
        cinema1.addTicket("EB65B9");
        cinema2.addTicket("ZG42F2");

        shop.addCinema("pathepourchat");
        shop.addCinema("pathemaiscafe");
        assertEquals(true, shop.checkTicket("pathepourchat:EB65S8"));
        assertEquals(false, shop.checkTicket("pathepourchat:EB65S8"));
        assertEquals(true, shop.checkTicket("pathepourchat:EB65B9"));
        assertEquals(false, shop.checkTicket("pathepourchatEB65B11"));

    }

}
