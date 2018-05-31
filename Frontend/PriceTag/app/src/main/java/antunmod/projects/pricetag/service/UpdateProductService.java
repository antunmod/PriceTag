package antunmod.projects.pricetag.service;

import android.widget.Toast;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.UpdateProductData;
import antunmod.projects.pricetag.transfer.AddPrice;
import antunmod.projects.pricetag.transfer.AddProductSpecific;
import antunmod.projects.pricetag.transfer.AddStoreProductStore;
import antunmod.projects.pricetag.transfer.AddStoreSpecificProductStore;
import antunmod.projects.pricetag.view.fragment.AddProductFragment;
import antunmod.projects.pricetag.view.fragment.UpdateProductFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antun on 5/22/2018.
 */

public class UpdateProductService {

    private RestServiceClient restServiceClient;
    private UpdateProductData updateProductData;
    private final String UPDATE_PRODUCT_ERROR = "Došlo je do greške, pokušajte ponovo";

    public UpdateProductService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void updateProduct(UpdateProductFragment updateProductFragment, UpdateProductData updateProductData) {
        this.updateProductData = updateProductData;

        /*
            Add existing product to a new store at a new location.
         */
        if (updateProductData.getStoreName() != null) {
            saveStoreProductStore(updateProductFragment);
        }

        /*
            Add existing product to a new store location.
         */
        else if (updateProductData.getStoreId() != null) {
            saveStoreSpecificProductStore(updateProductFragment);
        }

        /*
            Update/Add product price in an existing store location.
         */
        else
            savePrice(updateProductFragment);
    }

    private void saveStoreProductStore(final UpdateProductFragment updateProductFragment) {
        AddStoreProductStore addStoreProductStore = updateProductData.toAddStoreProductStore();

        Call<Boolean> call = restServiceClient.addStoreProductStore(addStoreProductStore);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success != null) {
                   updateProductFragment.updatedProduct(updateProductFragment, success);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(updateProductFragment.getContext(), UPDATE_PRODUCT_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveStoreSpecificProductStore(final UpdateProductFragment updateProductFragment) {
        AddStoreSpecificProductStore addStoreSpecificProductStore = updateProductData.toAddStoreSpecificProductStore();

        Call<Boolean> call = restServiceClient.addStoreSpecificProductStore(addStoreSpecificProductStore);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success != null) {
                    updateProductFragment.updatedProduct(updateProductFragment, success);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(updateProductFragment.getContext(), UPDATE_PRODUCT_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void savePrice(final UpdateProductFragment updateProductFragment) {
        AddPrice addPrice = updateProductData.toAddPrice();

        Call<Boolean> call = restServiceClient.addPrice(addPrice);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success != null) {
                    updateProductFragment.updatedProduct(updateProductFragment, success);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(updateProductFragment.getContext(), UPDATE_PRODUCT_ERROR, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
