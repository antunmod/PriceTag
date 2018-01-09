package antunmod.projects.pricetag;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by antun on 1/6/2018.
 */

public interface RestServiceClient {

    /*@Headers("Content-Type: application/json")
    @GET("users")
    Call<List<User>> getUsers();*/

    @Headers("Content-Type: application/json")
    @GET("users")
    Call<User> loginUser(@Query("username") String username);

    @Headers("Content-Type: application/json")
    @GET("users")
    Call<User> loginUser(@Query("username") String username, @Query("password") String password);

    @Headers("Content-Type: application/json")
    @POST("users")
    Call<User> registerUser(@Body User user);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
