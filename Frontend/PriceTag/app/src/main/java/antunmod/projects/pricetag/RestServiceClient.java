package antunmod.projects.pricetag;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by antun on 1/6/2018.
 */

public interface RestServiceClient {

    @GET("users")
    Call<List<User>> getUsers(

    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://localhost:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
