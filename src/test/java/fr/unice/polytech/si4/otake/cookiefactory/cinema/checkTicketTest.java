package fr.unice.polytech.si4.otake.cookiefactory.cinema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;

public class checkTicketTest {
    Shop shop;

    @Before
    public void systemCreation() {
        shop = new Shop("city", "name", null);
        CinemaAPI cinemaapi = CinemaAPI.getInstanceCinemaAPI();
        Cinema cinema1 = new Cinema("pathepourchat", "1");
        Cinema cinema2 = new Cinema("pathelaiscafe", "2");

        cinemaapi.addCinema(cinema1);
        cinemaapi.addCinema(cinema2);

        cinema1.addTicket("EB65S8");
        cinema1.addTicket("EB65B9");
        cinema2.addTicket("ZG42F2");

        shop.addCinema("pathepourchat");
        shop.addCinema("pathemaiscafe");
    }

    @Test
    public void checkTicketMethodeTest() {

        assertEquals(true,shop.checkTicket("pathepourchat:EB65S8"));
        assertEquals(false,shop.checkTicket("pathepourchat:EB65S8"));
        assertEquals(true,shop.checkTicket("pathepourchat:EB65B9"));
        assertEquals(false,shop.checkTicket("pathepourchat:EB65B11"));


    }

}
