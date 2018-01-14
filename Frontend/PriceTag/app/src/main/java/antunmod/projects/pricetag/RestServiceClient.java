package antunmod.projects.pricetag;

import android.text.style.UpdateAppearance;

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

    @Headers("Content-Type: application/json")
    @GET("sizes")
    Call<List<String>> getSizeValues();

    @Headers("Content-Type: application/json")
    @GET("sectors")
    Call<List<Sector>> getSectors();

    @Headers("Content-Type: application/json")
    @GET("categories")
    Call<List<String>> getCategoriesForSectorName(@Query("sectorName") String sectorName);

    @Headers("Content-Type: application/json")
    @GET("subcategories")
    Call<List<String>> getSubcategoriesForCategoryName(@Query("categoryName") String categoryName);

    @Headers("Content-Type: application/json")
    @GET("producers")
    Call<List<String>> getProducersForSubcategoryName(@Query("subcategoryName") String subcategoryName);

    @Headers("Content-Type: application/json")
    @GET("products/productNames")
    Call<List<String>> getProductNamesForSubcategoryNameAndProducer(@Query("subcategoryName") String subcategoryName,
                                                                    @Query("producer") String producer);

    @Headers("Content-Type: application/json")
    @GET("products/id")
    Call<Integer> getProductIdForProducerAndProductName(@Query("producer") String producer,
                                                                    @Query("productName") String productName);

    @Headers("Content-Type: application/json")
    @GET("stores")
    Call<List<String>> getStoreNames();

    @Headers("Content-Type: application/json")
    @GET("stores/locations")
    Call<List<String>> getStoreLocations(@Query("storeName") String selectedStore);

    @Headers("Content-Type: application/json")
    @GET("products")
    Call<UpdateProduct> getUpdateProductForBarcodeAndStoreAddress(@Query("barcode") String barcode,
                                                                  @Query("storeAddress") String storeAddress);

    @Headers("Content-Type: application/json")
    @POST("productStore/update")
    Call<Boolean> saveUpdatedProduct (@Body UpdateProduct updateProduct);

    @Headers("Content-Type: application/json")
    @POST("users/updatePoints")
    Call<Integer> awardPointsToUserForUserId(@Query("userId") long userId);

    @Headers("Content-Type: application/json")
    @GET("stores/address")
    Call<Integer> getStoreIdForAddress(@Query("storeAddress") String selectedStoreAddress);




    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.4:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
