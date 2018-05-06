package antunmod.projects.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.view.fragment.SelectFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A service for handling retrofit calls from SelectFragment.
 */

public class SelectService {

    private final String ERROR_STRING = "Došlo je do greške, pokušajte ponovo";

    public void findStoreAddresses(String selectedStore) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getStoreLocations(selectedStore);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> storeAddresses = (ArrayList<String>) response.body();
                if (storeAddresses != null) {
                    SelectFragment.setStoreList(storeAddresses);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findSectors(final String storeAddress) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getAllSectorNames();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> sectorList = response.body();

                if (sectorList != null) {
                    SelectFragment.setSectorList(sectorList);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findStoreId(String storeAddress) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Byte> call = restServiceClient.getStoreIdForAddress(storeAddress);
        call.enqueue(new Callback<Byte>() {
            @Override
            public void onResponse(Call<Byte> call, Response<Byte> response) {
                Byte storeId = response.body();

                if (storeId != null) {
                    SelectFragment.setStoreId(storeId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<Byte> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findCategoriesForSectorName(String sectorName) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getCategoriesForSectorName(sectorName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    SelectFragment.setCategoryList(categoriesList);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findSubcategoriesForCategoryName(String categoryName) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getSubcategoriesForCategoryName(categoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> subcategoriesList = (ArrayList) response.body();
                if (subcategoriesList != null) {
                    SelectFragment.setSubcategoryList(subcategoriesList);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

}
