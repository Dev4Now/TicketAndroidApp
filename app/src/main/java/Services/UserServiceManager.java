package Services;

import Model.User;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by paolatilve on 10/24/17.
 */

public interface UserServiceManager {
    @GET("/posts/1")
    Call<User> GetUser();
}
