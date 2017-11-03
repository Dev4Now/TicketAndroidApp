package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import Model.TicketInformation;

/**
 * Created by paolatilve on 10/14/17.
 */

public class TicketsInfo {

    @SerializedName("")
    private List<TicketInformation> ticketInformation;

    public  TicketsInfo(){}

    public TicketsInfo(List<TicketInformation> ticketInformation) {
        this.ticketInformation = ticketInformation;
    }

    public List<TicketInformation> getTicketInformation() {
        return ticketInformation;
    }

    public void setTicketInformation(List<TicketInformation> ticketInformation) {
        this.ticketInformation = ticketInformation;
    }

    public static TicketInformation parseJson(String response){
        Gson gson = new GsonBuilder().create();
        TicketInformation ticketInformation = gson.fromJson(response, TicketInformation.class);
        return  ticketInformation;
    }
}
