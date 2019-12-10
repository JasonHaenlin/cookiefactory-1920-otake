package fr.unice.polytech.si4.otake.cookiefactory.cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
  String name;
  List<String> listTicket;

  // Constructeur par défaut
  public Cinema(String name) {
    this.name = name;
    listTicket = new ArrayList<>();
  }

  public void addTicket(String ticketId) {
    listTicket.add(ticketId);
  }

  public boolean isRealTicket(String ticket) {
    for (String string : listTicket) {
      if (string.equals(ticket)) {
        return true;
      }
    }
    return false;
  }

  public List<String> getTickets() {
    return this.listTicket;
  }

  public String getName() {
    return name;
  }

}
