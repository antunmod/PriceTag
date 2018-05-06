package antunmod.projects.pricetag;

import java.util.List;

import antunmod.projects.pricetag.model.User;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
    Call<List<String>> getSizeTypes();

    @Headers("Content-Type: application/json")
    @POST("products")
    Call<Boolean> addProduct(@Body AddProduct addProduct);

    @Headers("Content-Type: application/json")
    @POST("photos")
    Call<Integer> addPhoto(@Body byte[] photo);

    @Headers("Content-Type: application/json")
    @POST("productStore")
    Call<Long> addProductStore(@Body ProductStore productStore);

    @Headers("Content-Type: application/json")
    @GET("sectors")
    Call<List<String>> getAllSectorNames();

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
    @GET("products/productId")
    Call<Integer> getProductIdForProducerAndProductName(@Query("producer") String producer,
                                                        @Query("productName") String productName);

    @Headers("Content-Type: application/json")
    @GET("sizes/sizeValue")
    Call<List<String>> getSizeValuesForProductId(@Query("productId") Integer productId);

    @Headers("Content-Type: application/json")
    @GET("products/photo")
    Call<String> getPhotoForProductIdAndSize(@Query("productId") Integer productId, @Query("size") String size);

    @Headers("Content-Type: application/json")
    @GET("subcategories/id")
    Call<Integer> getSubcategoryIdForCategoryAndSubcategoryName(@Query("categoryName") String categoryName,
                                                                @Query("subcategoryName") String subcategoryName);

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
    @GET("photos/photo")
    Call<Byte[]> getPhotoByteArray(@Query("photoId") Integer photoId);

    @Headers("Content-Type: application/json")
    @POST("subcategoryProduct")
    Call<Boolean> addSubcategoryProduct(@Query("subcategoryName") String subcategoryName,
                                        @Query("productId") Integer productId);

    @Headers("Content-Type: application/json")
    @POST("productStore/update")
    Call<Boolean> saveUpdatedProduct(@Body UpdateProduct updateProduct);

    @Headers("Content-Type: application/json")
    @POST("users/updatePoints")
    Call<Integer> awardPointsToUserForUserId(@Query("userId") long userId);

    @Headers("Content-Type: application/json")
    @GET("stores/address")
    Call<Byte> getStoreIdForAddress(@Query("storeAddress") String selectedStoreAddress);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.6:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
