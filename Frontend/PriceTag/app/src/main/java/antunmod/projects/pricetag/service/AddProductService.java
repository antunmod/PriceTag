package antunmod.projects.pricetag.service;

import java.io.File;
import java.nio.ByteBuffer;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.ProductData;
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
        Call<Short> call = restServiceClient.addProductSpecific(addProductSpecific);
        call.enqueue(new Callback<Short>() {
            @Override
            public void onResponse(Call<Short> call, Response<Short> response) {
                Short productSpecificId = response.body();
                addProductFragment.addedProduct(addProductFragment, productSpecificId);
            }

            @Override
            public void onFailure(Call<Short> call, Throwable t) {
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
                    AddProductFragment.setProductAdded(false);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                AddProductFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void saveImage(final AddProductFragment addProductFragment, Byte[] imageArray, Short productSpecificId) {
        Call<String> call = restServiceClient.addImage(imageArray, productSpecificId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String photoURI = response.body();
                if (photoURI != null) {
                    addProductFragment.addedPhoto(addProductFragment, photoURI);

                } else {
                    AddProductFragment.setErrorString(ERROR_STRING);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
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

    public Bitmap getScaledImageBitmap(AddProductFragment addProductFragment, Uri imageURI) {
        Bitmap d = getImageBitmap(addProductFragment, imageURI);
        int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
        Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
        return scaled;
    }

    public byte[] getImage(AddProductFragment addProductFragment, Uri imageURI)
    {
       Bitmap bitmap = getImageBitmap(addProductFragment, imageURI);

        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }

    public byte[] getScaledImage(AddProductFragment addProductFragment, Uri imageURI)
    {
        Bitmap bitmap = getScaledImageBitmap(addProductFragment, imageURI);

        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }

}
