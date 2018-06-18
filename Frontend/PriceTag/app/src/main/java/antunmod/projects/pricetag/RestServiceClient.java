package antunmod.projects.pricetag;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.model.User;
import antunmod.projects.pricetag.transfer.AddPrice;
import antunmod.projects.pricetag.transfer.AddProducer;
import antunmod.projects.pricetag.transfer.AddProduct;
import antunmod.projects.pricetag.transfer.AddProductSpecific;
import antunmod.projects.pricetag.transfer.AddStoreProducer;
import antunmod.projects.pricetag.transfer.AddStoreProduct;
import antunmod.projects.pricetag.transfer.AddStoreProductSpecific;
import antunmod.projects.pricetag.transfer.AddStoreProductStore;
import antunmod.projects.pricetag.transfer.AddStoreSpecificProducer;
import antunmod.projects.pricetag.transfer.AddStoreSpecificProduct;
import antunmod.projects.pricetag.transfer.AddStoreSpecificProductSpecific;
import antunmod.projects.pricetag.transfer.AddStoreSpecificProductStore;
import antunmod.projects.pricetag.transfer.InformationFeedback;
import antunmod.projects.pricetag.transfer.ProductInformation;
import antunmod.projects.pricetag.transfer.SearchFilter;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.transfer.StoreProductPrice;
import antunmod.projects.pricetag.transfer.UserInformation;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *  RestServiceClient containing Calls to the server.
 */

public interface RestServiceClient {

    //String serverIP = " https://pricetag-1701.herokuapp.com/";
    String serverIP = " http://192.168.1.2:8080/";

    /*
        Adding products to an existing store location.
    */

    @Headers("Content-Type: application/json")
    @POST("add/productSpecific")
    Call<Short> addProductSpecific(@Body AddProductSpecific addProductSpecific);

    @Headers("Content-Type: application/json")
    @POST("add/product")
    Call<Short> addProduct(@Body AddProduct addProduct);

    @Headers("Content-Type: application/json")
    @POST("add/producer")
    Call<Short> addProducer(@Body AddProducer addProducer);

    /*
        Adding products to a new store location.
    */

    @Headers("Content-Type: application/json")
    @POST("add/storeSpecificProductSpecific")
    Call<Short> addStoreSpecificProductSpecific(@Body AddStoreSpecificProductSpecific addStoreSpecificProductSpecific);

    @Headers("Content-Type: application/json")
    @POST("add/storeSpecificProduct")
    Call<Short> addStoreSpecificProduct(@Body AddStoreSpecificProduct addStoreSpecificProduct);

    @Headers("Content-Type: application/json")
    @POST("add/storeSpecificProducer")
    Call<Short> addStoreSpecificProducer(@Body AddStoreSpecificProducer addStoreSpecificProducer);

    /*
        Adding products to a new store.
    */

    @Headers("Content-Type: application/json")
    @POST("add/storeProductSpecific")
    Call<Short> addStoreProductSpecific(@Body AddStoreProductSpecific addStoreProductSpecific);

    @Headers("Content-Type: application/json")
    @POST("add/storeProduct")
    Call<Short> addStoreProduct(@Body AddStoreProduct addStoreProduct);

    @Headers("Content-Type: application/json")
    @POST("add/storeProducer")
    Call<Short> addStoreProducer(@Body AddStoreProducer addStoreProducer);


    /*
        Updating products.
    */

    @Headers("Content-Type: application/json")
    @POST("add/price")
    Call<Boolean> addPrice(@Body AddPrice addPrice);

    @Headers("Content-Type: application/json")
    @POST("add/storeSpecificProductStore")
    Call<Boolean> addStoreSpecificProductStore(@Body AddStoreSpecificProductStore addStoreSpecificProductStore);

    @Headers("Content-Type: application/json")
    @POST("add/storeProductStore")
    Call<Boolean> addStoreProductStore(@Body AddStoreProductStore addStoreProductStore);

    @Headers("Content-Type: application/json")
    @GET("products/productInformation")
    Call<ResponseBody> getBasicProductInformationForProductSpecificId(@Query("productSpecificId") Short productSpecificId);

    /*
        Searching for products.
    */

    @Headers("Content-Type: application/json")
    @POST("search")
    Call<List<SearchProductData>> getProducts(@Body SearchFilter searchFilter);

    @Headers("Content-Type: application/json")
    @GET("search/locations")
    Call<List<StoreProductPrice>> getLocationsForProductSpecificId(@Query("productSpecificId") Short productSpecificId);

    @Headers("Content-Type: application/json")
    @GET("search/recent")
    Call<List<SearchProductData>> getRecentProducts();

    /*
        Simple GET calls while adding a new product.
    */

    @Headers("Content-Type: application/json")
    @GET("products/productInformation")
    Call<ProductInformation> getProductInformationForBarcode(@Query("barcode") String barcode);

    @Headers("Content-Type: application/json")
    @GET("categories")
    Call<List<String>> getCategoriesForSectorName(@Query("sectorName") String sectorName);

    @Headers("Content-Type: application/json")
    @GET("subcategories")
    Call<List<String>> getSubcategoriesForCategoryName(@Query("categoryName") String categoryName);

    @Headers("Content-Type: application/json")
    @GET("subcategories/id")
    Call<Short> getSubcategoryIdForCategoryAndSubcategoryName(@Query("categoryName") String categoryName,
                                                                @Query("subcategoryName") String subcategoryName);

    @Headers("Content-Type: application/json")
    @GET("producers")
    Call<List<String>> getProducersForSubcategoryName(@Query("subcategoryName") String subcategoryName);

    @Headers("Content-Type: application/json")
    @GET("producers/id")
    Call<Short> getProducerId(@Query("producerName") String producerName);

    @Headers("Content-Type: application/json")
    @GET("products/productNames")
    Call<List<String>> getProductNamesForSubcategoryAndProducerName(@Query("subcategoryName") String subcategoryName,
                                                                    @Query("producer") String producer);

    @Headers("Content-Type: application/json")
    @GET("products/productId")
    Call<Short> getProductIdForProducerAndProductName(@Query("producer") String producer,
                                                        @Query("productName") String productName);

    @Headers("Content-Type: application/json")
    @GET("stores")
    Call<List<String>> getStoreNames();

    @Headers("Content-Type: application/json")
    @GET("stores/id")
    Call<Short> getStoreId(@Query("storeName") String storeName);

    @Headers("Content-Type: application/json")
    @GET("sizes/sizeValue")
    Call<List<String>> getSizeValuesForProductId(@Query("productId") Short productId);

    @Headers("Content-Type: application/json")
    @GET("stores/locations")
    Call<List<String>> getStoreLocations(@Query("storeName") String selectedStore);

    @Headers("Content-Type: application/json")
    @GET("stores/address")
    Call<Short> getStoreSpecificIdForAddress(@Query("storeAddress") String selectedStoreAddress);

    @Headers("Content-Type: application/json")
    @GET("sizes")
    Call<List<String>> getSizeTypes();

    /*
        Calls for handling user data.
    */

    @Headers("Content-Type: application/json")
    @GET("users")
    Call<User> loginUser(@Query("name") String name, @Query("password") String password);

    @Headers("Content-Type: application/json")
    @POST("users")
    Call<User> registerUser(@Body User user);

    @Headers("Content-Type: application/json")
    @GET("users/requestPassword")
    Call<Boolean> sendPasswordToEmail(@Query("username") String username, @Query("email") String email);

    /*
        Calls for handling photos.
     */

    @Headers("Content-Type: application/json")
    @POST("photos")
    Call<Boolean> addImage(@Body Byte[] imageArray, @Query("productSpecificId") Short productSpecificId);

    @Headers("Content-Type: application/json")
    @GET("photos")
    Call<ResponseBody> getEncodedImageForProductSpecificId(@Query("productSpecificId") Short productSpecificId);

    /*
        Profile tab calls
     */
    @Headers("Content-Type: application/json")
    @GET("users/information")
    Call<UserInformation> getUserInformation(@Query("id") Short id);

    /*
        InformationFeedback calls
     */
    @Headers("Content-Type: application/json")
    @GET("informationFeedback")
    Call<InformationFeedback> getInformationFeedbackForUserAndPriceId(@Query("userId") Short userId,
                                                                      @Query("priceId") Integer priceId);

    @Headers("Content-Type: application/json")
    @POST("informationFeedback/save")
    Call<Boolean> saveInformationFeedback(@Body InformationFeedback informationFeedback);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(serverIP)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().setLenient().create()))
            .build();

}
