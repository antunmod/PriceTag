package antunmod.projects.pricetag.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.ProductData;
import antunmod.projects.pricetag.transfer.AddProducer;
import antunmod.projects.pricetag.transfer.AddProduct;
import antunmod.projects.pricetag.transfer.AddProductSpecific;
import antunmod.projects.pricetag.view.fragment.AddProductFragment;
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
            If product info and a new store name were given.
         */
        if (productData.getStoreName() != null) {
            if (productData.getProductId() != null) {
                saveStoreProductSpecific(addProductFragment);
            }
            else if (productData.getProducerId() != null) {
                saveStoreProduct(addProductFragment);
            }
            else {
                saveStoreProducer(addProductFragment);
            }

        }

        /*
            If product info and a new store location were given.
         */
        else if (productData.getStoreId()!=null) {
            if (productData.getProductId() != null) {
                saveStoreSpecificProductSpecific(addProductFragment);
            }
            else if (productData.getProducerId() != null) {
                saveStoreSpecificProduct(addProductFragment);
            }
            else {
                saveStoreSpecificProducer(addProductFragment);
            }

        }


        /*
            If only the product info is given.
         */
        else {
            if (productData.getProductId() != null) {
                saveProductSpecific(addProductFragment);
            }
            else if (productData.getProducerId() != null) {
                saveProduct(addProductFragment);
            }
            else {
                saveProducer(addProductFragment);
            }

        }


    }

    private void saveProductSpecific(final AddProductFragment addProductFragment) {
        AddProductSpecific addProductSpecific = productData.toAddProductSpecific();
        Call<Short> call = restServiceClient.addProductSpecific(addProductSpecific);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveProduct(final AddProductFragment addProductFragment) {
        AddProduct addProduct = productData.toAddProduct();
        Call<Short> call = restServiceClient.addProduct(addProduct);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);

            }
            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveProducer(final AddProductFragment addProductFragment) {
        AddProducer addProducer = productData.toAddProducer();
        Call<Short> call = restServiceClient.addProducer(addProducer);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreSpecificProductSpecific(final AddProductFragment addProductFragment) {
        Call<Short> call = restServiceClient.addStoreSpecificProductSpecific(productData.toAddStoreSpecificProductSpecific());
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreSpecificProduct(final AddProductFragment addProductFragment) {
        Call<Short> call = restServiceClient.addStoreSpecificProduct(productData.toAddStoreSpecificProduct());
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreSpecificProducer(final AddProductFragment addProductFragment) {
        Call<Short> call = restServiceClient.addStoreSpecificProducer(productData.toAddStoreSpecificProducer());
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreProductSpecific(final AddProductFragment addProductFragment) {
        Call<Short> call = restServiceClient.addStoreProductSpecific(productData.toAddStoreProductSpecific());
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreProduct(final AddProductFragment addProductFragment) {
        Call<Short> call = restServiceClient.addStoreProduct(productData.toAddStoreProduct());
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    private void saveStoreProducer(final AddProductFragment addProductFragment) {
        Call<Short> call = restServiceClient.addStoreProducer(productData.toAddStoreProducer());
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                if (productSpecificId == null || productSpecificId == -1) {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void saveImage(final AddProductFragment addProductFragment, Byte[] imageArray, Short productSpecificId) {
        Call<Boolean> call = restServiceClient.addImage(imageArray, productSpecificId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success != null) {
                    addProductFragment.addedPhoto(addProductFragment, success);

                } else {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public File createTemporaryFile(String part, String ext) throws Exception
    {
        File tempDir= Environment.getExternalStorageDirectory();
        tempDir=new File(tempDir.getAbsolutePath()+"/.temp/");
        if(!tempDir.exists())
        {
            tempDir.mkdirs();
        }
        return File.createTempFile(part, ext, tempDir);
    }

    public Bitmap getImageBitmap(AddProductFragment addProductFragment, Uri imageURI) {
        ContentResolver cr = addProductFragment.getContext().getContentResolver();
        Bitmap bitmap = null;
        try
        {
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, imageURI);
        }
        catch (Exception e)
        {
            Toast.makeText(addProductFragment.getContext(), "Neuspjelo učitavanje spremljene slike", Toast.LENGTH_SHORT).show();
        }
        return bitmap;
    }

    public Bitmap getCompressedBitmap(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
        Bitmap compressed = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
        return compressed;
    }



}
