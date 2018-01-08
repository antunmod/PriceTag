package antunmod.projects.pricetag;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by antun on 1/6/2018.
 */

public interface RestServiceClient {

    //@Headers("Content-Type: application/json")
    @GET("users")
    Call<List<User>> getUsers();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
