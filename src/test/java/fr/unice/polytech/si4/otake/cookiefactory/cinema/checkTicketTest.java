package fr.unice.polytech.si4.otake.cookiefactory.cinema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import fr.unice.polytech.si4.otake.cookiefactory.ParentCompany;
import fr.unice.polytech.si4.otake.cookiefactory.product.cookie.Cookie;
import fr.unice.polytech.si4.otake.cookiefactory.shop.Shop;
import fr.unice.polytech.si4.otake.helper.HelperRecipe;

public class checkTicketTest {

    @Test
    public void checkTicketMethodeTest() {
        ParentCompany parent = new ParentCompany();
        Shop shop = new Shop("city", "name", parent);
        HelperRecipe helper = new HelperRecipe(parent.getRecipes());
        parent.changeRecipeOfTheDay(helper.getChocolalala());
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
        // assertEquals(true, shop.checkTicket("pathepourchat:EB65S8"));
        List<Cookie> free = shop.getFreeCookiesOfTheDay("pathepourchat:EB65S8");
        assertEquals(2, free.size());
        assertEquals("Chocolalala", free.get(0).getName());
        assertEquals(false, shop.checkTicket("pathepourchat:EB65S8"));
        parent.changeRecipeOfTheDay(helper.getDarkTemptation());
        List<Cookie> free2 = shop.getFreeCookiesOfTheDay("pathepourchat:EB65B9");
        assertEquals(2, free2.size());
        assertEquals("Dark Temptation", free2.get(0).getName());
        assertEquals(false, shop.checkTicket("pathepourchatEB65B11"));

    }

}
