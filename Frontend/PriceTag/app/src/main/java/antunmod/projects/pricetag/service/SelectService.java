package antunmod.projects.pricetag.service;

import android.widget.Toast;

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

    public void findProducersForSubcategoryName(String subcategoryName) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getProducersForSubcategoryName(subcategoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> producersList = (ArrayList) response.body();
                if (producersList != null) {
                    SelectFragment.setProducerList(producersList);

                } else {
                    SelectFragment.setErrorString(ERROR_STRING);

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }

            ;

        });
    }

    public void findProductsForSubcategoryAndProducerName(String subcategoryName, String producerName) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getProductNamesForSubcategoryNameAndProducer(subcategoryName, producerName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> productsList = (ArrayList) response.body();
                if (productsList != null) {
                    SelectFragment.setProductList(productsList);
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

    public void findProductIdForProducerAndProductName(String producerName, String productName) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Short> call = restServiceClient.getProductIdForProducerAndProductName(producerName, productName);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productId = response.body();
                if (productId != null) {
                    SelectFragment.setProductId(productId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findSizeValuesForProductId(Short productId) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getSizeValuesForProductId(productId);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> sizeList = response.body();
                if (sizeList != null) {
                    SelectFragment.setSizeList(sizeList);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });

    }
}
