package antunmod.projects.pricetag.service;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.transfer.ProductInformation;
import antunmod.projects.pricetag.view.fragment.SelectFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A service for handling retrofit calls from SelectFragment.
 */

public class SelectService {

    private final String ERROR_STRING = "Došlo je do greške, pokušajte ponovo";
    private RestServiceClient restServiceClient;

    public SelectService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }


    public void findStoreAddresses(final SelectFragment selectFragment, String selectedStore) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getStoreLocations(selectedStore);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> storeAddresses = (ArrayList<String>) response.body();
                if (storeAddresses != null) {
                    SelectFragment.foundStoreAddressesStatic(selectFragment, storeAddresses);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findStoreSpecificId(final SelectFragment selectFragment, String storeAddress) {

        Call<Short> call = restServiceClient.getStoreSpecificIdForAddress(storeAddress);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(@NonNull Call<Short> call, @NonNull Response<Short> response) {
                Short storeId = response.body();

                if (storeId != null) {
                    SelectFragment.foundStoreSpecificId(selectFragment, storeId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Short> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void pingServer() {
        Call<List<String>> call = restServiceClient.getSectors();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {

            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
            }
        });
    }

    public void findCategoriesForSectorName(final SelectFragment selectFragment, String sectorName) {
        Call<List<String>> call = restServiceClient.getCategoriesForSectorName(sectorName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    SelectFragment.foundCategoriesForSectorName(selectFragment, categoriesList);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findSubcategoriesForCategoryName(final SelectFragment selectFragment, String categoryName) {
        Call<List<String>> call = restServiceClient.getSubcategoriesForCategoryName(categoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> subcategoriesList = (ArrayList) response.body();
                if (subcategoriesList != null) {
                    SelectFragment.foundSubcategoriesForSectorName(selectFragment, subcategoriesList);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProducersForSubcategoryName(final SelectFragment selectFragment, String subcategoryName) {
        Call<List<String>> call = restServiceClient.getProducersForSubcategoryName(subcategoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> producersList = (ArrayList) response.body();
                if (producersList != null) {
                    SelectFragment.foundProducersForSubcategoryName(selectFragment, producersList);

                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }

        });
    }

    public void findProductsForSubcategoryAndProducerName(final SelectFragment selectFragment, String subcategoryName, String producerName) {

        Call<List<String>> call = restServiceClient.getProductNamesForSubcategoryAndProducerName(subcategoryName, producerName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                ArrayList<String> productsList = (ArrayList) response.body();
                if (productsList != null) {
                    SelectFragment.foundProductsForSubcategoryAndProducerName(selectFragment, productsList);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }

        });

    }

    public void findProductIdForProducerAndProductName(final SelectFragment selectFragment, String producerName, String productName) {

        Call<Short> call = restServiceClient.getProductIdForProducerAndProductName(producerName, productName);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(@NonNull Call<Short> call, @NonNull Response<Short> response) {
                Short productId = response.body();
                if (productId != null) {
                    SelectFragment.foundProductIdForProducerAndProductName(selectFragment, productId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Short> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findSubcategoryIdForCategoryAndSubcategoryName(final SelectFragment selectFragment, String categoryName, String subcategoryName) {

        Call<Short> call = restServiceClient.getSubcategoryIdForCategoryAndSubcategoryName(categoryName, subcategoryName);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(@NonNull Call<Short> call, @NonNull Response<Short> response) {
                Short subcategoryId = response.body();
                if (subcategoryId != null) {
                    SelectFragment.foundSubcategoryIdForCategoryAndSubcategoryName(selectFragment, subcategoryId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Short> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });

    }

    public void findStoreId(final SelectFragment selectFragment, String storeName) {
        Call<Short> call = restServiceClient.getStoreId(storeName);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(@NonNull Call<Short> call, @NonNull Response<Short> response) {
                Short storeId = response.body();
                if (storeId != null) {
                    SelectFragment.foundStoreId(selectFragment, storeId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Short> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProducerId(final SelectFragment selectFragment, String producerName) {
        Call<Short> call = restServiceClient.getProducerId(producerName);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(@NonNull Call<Short> call, @NonNull Response<Short> response) {
                Short producerId = response.body();
                if (producerId != null) {
                    SelectFragment.foundProducerId(selectFragment, producerId);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Short> call, @NonNull Throwable t) {
                SelectFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void findProductInformationForBarcode(final SelectFragment selectFragment, String barcode) {
        Call<ProductInformation> call = restServiceClient.getProductInformationForBarcode(barcode);
        call.enqueue(new Callback<ProductInformation>() {
            @Override
            public void onResponse(@NonNull Call<ProductInformation> call, @NonNull Response<ProductInformation> response) {
                ProductInformation productInformation = response.body();
                if (productInformation != null) {
                    selectFragment.foundProductInformationForBarcode(selectFragment, productInformation);
                } else {
                    SelectFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductInformation> call, @NonNull Throwable t) {
            }
        });
    }
}
