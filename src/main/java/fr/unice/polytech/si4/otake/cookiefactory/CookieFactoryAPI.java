package fr.unice.polytech.si4.otake.cookiefactory;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si4.otake.cookiefactory.cinema.CinemaAPI;

public class CookieFactoryAPI {
    private List<String> ticketsUsed;

    private CookieFactoryAPI() {
        ticketsUsed = new ArrayList<>();
    }

    private static CookieFactoryAPI INSTANCE = new CookieFactoryAPI();

    public static CookieFactoryAPI getInstanceCookieFactoryAPI() {
        return INSTANCE;
    }

    public void addUsedTicket(String ticket) {
        this.ticketsUsed.add(ticket);
    }

    public boolean isTicketNotUsed(String ticketId) {
        for (String string : ticketsUsed) {
            if (string.equals(ticketId)) {
                return false;
            }

        }
        addUsedTicket(ticketId);
        return true;
    }

    public boolean globalyCheck(String ticketId) {
        CinemaAPI cinemaapi = CinemaAPI.getInstance();
        if (isTicketNotUsed(ticketId)) {
            return cinemaapi.checkTicket(ticketId);
        } else {
            return false;
        }
    }

}
