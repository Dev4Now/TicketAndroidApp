package Services;

import java.util.List;

import Model.TicketInformation;
import Model.TicketsInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by paolatilve on 10/14/17.
 */

public interface TicketsService {

    @GET("/posts/1")
    Call<TicketInformation> Get();

    @GET("/posts")
    Call<TicketsInfo> GetMeAll();

    @GET("/users")
    Call<List<TicketInformation>> GetAllTickets();
}
