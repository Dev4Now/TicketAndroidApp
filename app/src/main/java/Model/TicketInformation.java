package Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by paolatilve on 10/14/17.
 */

public class TicketInformation {

    private String adviser;
    @SerializedName("current_ticket")
    private int currentTicket;
    @SerializedName("to_atend")
    private int ticketsToAttend;

    public TicketInformation(){}

    public TicketInformation(String adviser, int currentTicket, int ticketsToAttend) {
        this.adviser = adviser;
        this.currentTicket = currentTicket;
        this.ticketsToAttend = ticketsToAttend;
    }

    public String getAdviser() {
        return adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public int getCurrentTicket() {
        return currentTicket;
    }

    public void setCurrentTicket(int currentTicket) {
        this.currentTicket = currentTicket;
    }

    public int getTicketsToAttend() {
        return ticketsToAttend;
    }

    public void setTicketsToAttend(int ticketsToAttend) {
        this.ticketsToAttend = ticketsToAttend;
    }
}
