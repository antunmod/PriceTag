package antunmod.projects.pricetag.service;

import android.widget.Toast;

import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.ProductData;
import antunmod.projects.pricetag.transfer.AddProduct;
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


    public void getSizesTypes() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getSizeTypes();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> tmp = response.body();
                if (tmp != null) {
                    AddProductFragment.setSizeTypeList(tmp);
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
    public void addProduct(ProductData productData) {

        this.productData = productData;

        /*
            If only the product info is given.
         */
        if (productData.getStoreName()!=null) {
            if (productData.getProductId() != null) {
                saveProductSpecific();
            }
            if (productData.getProducerId() != null) {
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
            if (productData.getProducerId() != null) {
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
            if (productData.getProducerId() != null) {
                saveStoreProduct();
            }
            else {
                saveStoreProducer();
            }

        }
    }

    private void saveProductSpecific() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Boolean> call = restServiceClient.addProductSpecific(productData.toAddProductSpecific());
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

    private void saveProduct() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
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
