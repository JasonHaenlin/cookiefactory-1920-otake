package fr.unice.polytech.si4.otake.cookiefactory.cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaAPI {
    private List<Cinema> listCinema;

    private CinemaAPI() {
        listCinema = new ArrayList<Cinema>();
    }

    private static CinemaAPI INSTANCE = new CinemaAPI();

    public static CinemaAPI getInstanceCinemaAPI() {
        return INSTANCE;
    }

    public void addCinema(Cinema cine) {
        this.listCinema.add(cine);
    }

    public boolean checkTicket(String ticketId) {
        for (Cinema cinema : listCinema) {
            if (cinema.isRealTicket(ticketId)) {
                return true;
            }
        }
        return false;
    }

}