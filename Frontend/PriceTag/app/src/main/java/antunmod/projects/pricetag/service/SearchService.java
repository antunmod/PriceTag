package antunmod.projects.pricetag.service;


import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.transfer.SearchFilter;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.view.fragment.SearchFragment;
import antunmod.projects.pricetag.view.fragment.SelectFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    A service for getting data lists from SearchFragment.
 */
public class SearchService {

    private final String ERROR_STRING = "Došlo je do greške, pokušajte ponovo";
    private RestServiceClient restServiceClient;

    public SearchService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void findCategoriesForSectorName(final SearchFragment searchFragment, String sectorName) {
        Call<List<String>> call = restServiceClient.getCategoriesForSectorName(sectorName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    SearchFragment.foundCategories(searchFragment, categoriesList);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findSubcategoriesForCategoryName(final SearchFragment searchFragment, String categoryName) {
        Call<List<String>> call = restServiceClient.getSubcategoriesForCategoryName(categoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    SearchFragment.foundSubcategoriesForCategoryName(searchFragment, categoriesList);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProducers(final SearchFragment searchFragment, String subcategoryName) {
        Call<List<String>> call = restServiceClient.getProducersForSubcategoryName(subcategoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> subcategoriesList = (ArrayList) response.body();
                if (subcategoriesList != null) {
                    SearchFragment.foundProducers(searchFragment, subcategoriesList);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findStores(final SearchFragment searchFragment) {
        Call<List<String>> call = restServiceClient.getStoreNames();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> storeNamesList = (ArrayList) response.body();
                if (storeNamesList != null) {
                    SearchFragment.foundStores(searchFragment, storeNamesList);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProducts(final SearchFragment searchFragment, SearchFilter searchFilter) {
        Call<List<SearchProductData>> call = restServiceClient.getProducts(searchFilter);
        call.enqueue(new Callback<List<SearchProductData>>() {
            @Override
            public void onResponse(Call<List<SearchProductData>> call, Response<List<SearchProductData>> response) {
                ArrayList<SearchProductData> searchProductDataList = (ArrayList) response.body();
                if (searchProductDataList != null) {
                    SearchFragment.foundProducts(searchFragment, searchProductDataList);
                }
            }

            @Override
            public void onFailure(Call<List<SearchProductData>> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }
}
