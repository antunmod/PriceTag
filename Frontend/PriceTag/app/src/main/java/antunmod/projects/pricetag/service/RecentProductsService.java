package antunmod.projects.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.transfer.SearchFilter;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.view.fragment.RecentProductsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antun on 5/31/2018.
 */

public class RecentProductsService {

    private RestServiceClient restServiceClient;

    public RecentProductsService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void findRecentProducts(final RecentProductsFragment recentProductsFragment) {
        Call<List<SearchProductData>> call = restServiceClient.getRecentProducts();
        call.enqueue(new Callback<List<SearchProductData>>() {
            @Override
            public void onResponse(Call<List<SearchProductData>> call, Response<List<SearchProductData>> response) {
                ArrayList<SearchProductData> searchProductDataList = (ArrayList<SearchProductData>) response.body();
                if (searchProductDataList != null) {
                    recentProductsFragment.foundRecentProducts(recentProductsFragment, searchProductDataList);
                }
            }

            @Override
            public void onFailure(Call<List<SearchProductData>> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }
}
