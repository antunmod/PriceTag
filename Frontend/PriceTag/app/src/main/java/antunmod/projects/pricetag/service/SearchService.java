package antunmod.projects.pricetag.service;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.transfer.SearchFilter;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.transfer.StoreProductPrice;
import antunmod.projects.pricetag.view.fragment.RecentProductsFragment;
import antunmod.projects.pricetag.view.fragment.SearchFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    A service for getting data lists from SearchFragment.
 */
public class SearchService {

    private RestServiceClient restServiceClient;

    public SearchService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void findCategoriesForSectorName(final SearchFragment searchFragment, String sectorName) {
        Call<List<String>> call = restServiceClient.getCategoriesForSectorName(sectorName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    SearchFragment.foundCategories(searchFragment, categoriesList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                UtilService.toastServerError(searchFragment.getContext());
            }
        });
    }

    public void findSubcategoriesForCategoryName(final SearchFragment searchFragment, String categoryName) {
        Call<List<String>> call = restServiceClient.getSubcategoriesForCategoryName(categoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    SearchFragment.foundSubcategoriesForCategoryName(searchFragment, categoriesList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProducers(final SearchFragment searchFragment, String subcategoryName) {
        Call<List<String>> call = restServiceClient.getProducersForSubcategoryName(subcategoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> subcategoriesList = (ArrayList) response.body();
                if (subcategoriesList != null) {
                    SearchFragment.foundProducers(searchFragment, subcategoriesList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findStores(final SearchFragment searchFragment) {
        Call<List<String>> call = restServiceClient.getStoreNames();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> storeNamesList = (ArrayList) response.body();
                if (storeNamesList != null) {
                    SearchFragment.foundStores(searchFragment, storeNamesList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProducts(final SearchFragment searchFragment, SearchFilter searchFilter) {
        Call<List<SearchProductData>> call = restServiceClient.getProducts(searchFilter);
        call.enqueue(new Callback<List<SearchProductData>>() {
            @Override
            public void onResponse(@NonNull Call<List<SearchProductData>> call, @NonNull Response<List<SearchProductData>> response) {
                ArrayList<SearchProductData> searchProductDataList = (ArrayList<SearchProductData>) response.body();
                if (searchProductDataList != null) {
                    SearchFragment.foundProducts(searchFragment, searchProductDataList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SearchProductData>> call, @NonNull Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void getLocationsForProductSpecificId(final Fragment fragment, Short productSpecificId) {
        Call<List<StoreProductPrice>> call = restServiceClient.getLocationsForProductSpecificId(productSpecificId);
        call.enqueue(new Callback<List<StoreProductPrice>>() {
            @Override
            public void onResponse(@NonNull Call<List<StoreProductPrice>> call, @NonNull Response<List<StoreProductPrice>> response) {
                ArrayList<StoreProductPrice> storeProductPriceList = (ArrayList<StoreProductPrice>) response.body();
                if (storeProductPriceList != null) {
                    if (fragment instanceof SearchFragment)
                        SearchFragment.foundLocations( (SearchFragment) fragment, storeProductPriceList);
                    else
                        RecentProductsFragment.foundLocations( (RecentProductsFragment) fragment, storeProductPriceList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<StoreProductPrice>> call, @NonNull Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

}
