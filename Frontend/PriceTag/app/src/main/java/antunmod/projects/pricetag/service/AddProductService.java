package antunmod.projects.pricetag.service;

import android.util.Base64;
import android.widget.Toast;

import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.ProductData;
import antunmod.projects.pricetag.transfer.AddProduct;
import antunmod.projects.pricetag.transfer.AddProductSpecific;
import antunmod.projects.pricetag.view.fragment.AddProductFragment;
import antunmod.projects.pricetag.view.fragment.SelectFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  This class will be used by AddProductFragment for Calls to the server.
 */

public class AddProductService {

    private final String ERROR_STRING = "Došlo je do greške, pokušajte ponovo";
    private ProductData productData;
    private RestServiceClient restServiceClient;

    public AddProductService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void getSizesTypes(final AddProductFragment addProductFragment) {
        Call<List<String>> call = restServiceClient.getSizeTypes();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> sizeTypesList = response.body();
                if (sizeTypesList != null) {
                    AddProductFragment.foundSizesTypes(addProductFragment, sizeTypesList);
                } else {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    /*
        This method determines which object should be sent to server.
     */
    public void addProduct(AddProductFragment addProductFragment, ProductData productData) {

        this.productData = productData;

        /*
            If only the product info is given.
         */
        if (productData.getStoreName()!=null) {
            if (productData.getProductId() != null) {
                saveProductSpecific(addProductFragment);
            }
            else if (productData.getProducerId() != null) {
                saveProduct();
            }
            else {
                saveProducer();
            }

        }

        /*
            If product info and a new store location were given.
         */
        else if (productData.getStoreId()!=null) {
            if (productData.getProductId() != null) {
                saveStoreSpecificProductSpecific();
            }
            else if (productData.getProducerId() != null) {
                saveStoreSpecificProduct();
            }
            else {
                saveStoreSpecificProducer();
            }

        }

        /*
            If product info and a new store name were given.
         */
        else if (productData.getStoreId()!=null) {
            if (productData.getProductId() != null) {
                saveStoreProductSpecific();
            }
            else if (productData.getProducerId() != null) {
                saveStoreProduct();
            }
            else {
                saveStoreProducer();
            }

        }
    }

    private void saveProductSpecific(final AddProductFragment addProductFragment) {
        AddProductSpecific addProductSpecific = productData.toAddProductSpecific();
        Call<Boolean> call = restServiceClient.addProductSpecific(addProductSpecific);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    addProductFragment.addedProduct(addProductFragment, productSavedSuccessfully);

                } else {
                    addProductFragment.addedProduct(addProductFragment, false);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveProduct() {
        Call<Boolean> call = restServiceClient.addProduct(productData.toAddProduct());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveProducer() {
        Call<Boolean> call = restServiceClient.addProducer(productData.toAddProducer());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreSpecificProductSpecific() {
        Call<Boolean> call = restServiceClient.addStoreSpecificProductSpecific(productData.toAddStoreSpecificProductSpecific());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreSpecificProduct() {
        Call<Boolean> call = restServiceClient.addStoreSpecificProduct(productData.toAddStoreSpecificProduct());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreSpecificProducer() {
        Call<Boolean> call = restServiceClient.addStoreSpecificProducer(productData.toAddStoreSpecificProducer());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreProductSpecific() {
        Call<Boolean> call = restServiceClient.addStoreProductSpecific(productData.toAddStoreProductSpecific());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreProduct() {
        Call<Boolean> call = restServiceClient.addStoreProduct(productData.toAddStoreProduct());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreProducer() {
        Call<Boolean> call = restServiceClient.addStoreProducer(productData.toAddStoreProducer());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }


    public void addPhoto(byte[] photo) {
        String encodedImage = Base64.encodeToString(photo, Base64.DEFAULT);

    }

    private void savePhoto() {
        Call<Boolean> call = restServiceClient.addStoreProducer(productData.toAddStoreProducer());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    AddProductFragment.setProductAdded(true);

                } else {
                    AddProductFragment.setProductAdded(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

}
