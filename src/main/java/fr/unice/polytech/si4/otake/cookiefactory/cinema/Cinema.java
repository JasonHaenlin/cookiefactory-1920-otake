package fr.unice.polytech.si4.otake.cookiefactory.cinema;

import java.util.ArrayList;
import java.util.List;

public class Cinema{   
    String ID;
    String Nom;
    List<String> listTicket;
       
    //Constructeur par défaut
    public Cinema(String Nom ,String ID){   
      this.ID = ID;
      listTicket = new ArrayList<>();
    } 

    public void addTicket(String TicketId) {
        listTicket.add(TicketId);
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


  }