package antunmod.projects.pricetag.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.ProductData;
import antunmod.projects.pricetag.service.AddProductService;
import antunmod.projects.pricetag.service.UtilService;

import static android.app.Activity.RESULT_CANCELED;
import static android.content.ContentValues.TAG;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    private final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA, Manifest.permission.INTERNET};
    private static final Integer CAMERA_REQUEST = 1;
    private static final Float MAX_SIZE_VALUE = (float) 1000.0;
    private static final Float MAX_PRICE_VALUE = (float) 10000.0;
    private static final Short NON_EXISTING_PRODUCT_ID = 0;
    private static final String PRODUCT_ADDED = "Uspješno ste dodali proizvod";
    private static final int STORAGE_REQUEST_CODE = 1;

    private final String SELECT_FRAGMENT_TAG = "selectFragment";

    private static ProductData productData;
    private byte[] photo;
    boolean pictureSet = false;
    private static String errorString;

    private Boolean productBeingAdded;

    private UtilService utilService;
    private AddProductService addProductService;
    public static AddProductFragment addProductFragment;

    public AddProductFragment() {
        // Required empty public constructor
    }

    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        // Set values
        if (bundle != null) {
            productData = (ProductData) bundle.getSerializable("productData");
        }


    }

    private View inflatedView;
    private View progressBar_loading;
    private ImageView imageView_addProduct;
    private TextView textView_producer;
    private TextView textView_productName;
    private EditText editText_productDescription;
    private EditText editText_size;
    private Spinner spinner_size;
    private EditText editText_price;
    private TextView textView_addProduct;
    private ProgressBar progress;

    private String sizeString;
    private String priceString;

    private Float size;
    private Float price;

    private static List<String> sizeTypeList;

    private Uri imageURI;
    File photoFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_add_product, container, false);

        productBeingAdded = false;

        addProductFragment = this;

        imageView_addProduct = inflatedView.findViewById(R.id.imageView_add_product);
        textView_producer = inflatedView.findViewById(R.id.textView_product);
        textView_productName = inflatedView.findViewById(R.id.textView_product_name);
        editText_productDescription = inflatedView.findViewById(R.id.editText_product_description);
        editText_size = inflatedView.findViewById(R.id.editText_size);
        spinner_size = inflatedView.findViewById(R.id.spinner_size);
        editText_price = inflatedView.findViewById(R.id.editText_price);
        textView_addProduct = inflatedView.findViewById(R.id.textView_add_product);
        progress = inflatedView.findViewById(R.id.progress);

        addProductService = new AddProductService();
        utilService = new UtilService();

        imageView_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissions();
            }
        });

        progressBar_loading = inflatedView.findViewById(R.id.progressBar_loading);
        textView_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldsAreValid()) {
                    textView_addProduct.setVisibility(View.GONE);
                    progress.setVisibility(View.VISIBLE);
                    productBeingAdded = true;
                    addPhoto();
                }
            }
        });

        setProducerAndProductName();
        findSizesTypes();
        return inflatedView;
    }

    public static void callCamera() throws IOException {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(addProductFragment.getActivity().getPackageManager()) != null) {
            String imageFileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File storageDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            addProductFragment.photoFile = image;
            if (image != null) {
                Uri uri = null;
                if (Build.VERSION.SDK_INT < 23) {
                    uri = Uri.fromFile(image);
                } else {
                    uri = FileProvider.getUriForFile(
                            addProductFragment.getContext(),
                            addProductFragment.getContext()
                                    .getPackageName() + ".provider", image);
                }

                if (uri == null) {
                    Toast.makeText(addProductFragment.getContext(), "Uri wasn't set", Toast.LENGTH_SHORT).show();
                }

                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        uri);
                addProductFragment.startActivityForResult(intent, CAMERA_REQUEST);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 5;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            ExifInterface exif = null;
            try {
                exif = new ExifInterface(photoFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (exif == null)
                return;
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

            Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);
            if (orientation == 6) {
                Toast.makeText(getContext(), "Orijentacija slike mora biti horizontalna!", Toast.LENGTH_SHORT).show();
                return;
            }
            imageView_addProduct.setBackgroundResource(android.R.color.transparent);
            imageView_addProduct.setAdjustViewBounds(true);
            imageView_addProduct.setScaleType(ImageView.ScaleType.CENTER);
            imageView_addProduct.setImageBitmap(bitmap);
            pictureSet = true;


        } else if (resultCode == RESULT_CANCELED) {
            // User cancelled the image capture
            //finish();
        }

    }

    private void checkPermissions() {
        // Create an image file name
        if (Build.VERSION.SDK_INT >= 23) {
            int write = ContextCompat.checkSelfPermission(this.getContext(), permissions[0]);
            /*if (write == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(getContext(), "true", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "falseeeeeeee", Toast.LENGTH_SHORT).show();*/

            int read = ContextCompat.checkSelfPermission(this.getContext(), permissions[1]);
            /*if (read == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(getContext(), "true", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();*/

            int camera = ContextCompat.checkSelfPermission(this.getContext(), permissions[2]);
            int internet = ContextCompat.checkSelfPermission(this.getContext(), permissions[3]);
            if (write != PackageManager.PERMISSION_GRANTED || read != PackageManager.PERMISSION_GRANTED ||
                    camera != PackageManager.PERMISSION_GRANTED || internet != PackageManager.PERMISSION_GRANTED)
                requestPermissions(permissions, STORAGE_REQUEST_CODE);
            //Toast.makeText(getContext(), "Došlo je do greške", Toast.LENGTH_SHORT).show();
            //requestPermissions(, STORAGE_REQUEST_CODE);
            //File write logic here*/
        }
        try {
            callCamera();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void setProducerAndProductName() {
        textView_producer.setText(productData.getProducerName());
        textView_productName.setText(productData.getProductName());
    }

    private boolean fieldsAreValid() {

        String toastMessage = "";

        if (!pictureSet)
            toastMessage = "Postavite sliku proizvoda";

        if ((sizeString = editText_size.getText().toString()).isEmpty())
            toastMessage = "Veličina mora biti unesena";

        else if ((priceString = editText_price.getText().toString()).isEmpty())
            toastMessage = "Cijena mora biti unesena";

        else if ((size = Float.parseFloat(sizeString)) > MAX_SIZE_VALUE)
            toastMessage = "Veličina prevelika. Unesite manji broj ili promjenite mjernu jedinicu";

        else if ((price = Float.parseFloat(priceString)) > MAX_PRICE_VALUE)
            toastMessage = "Cijena prevelika. Unesite manji broj";

        if (toastMessage.isEmpty())
            return true;

        Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();

        return false;
    }

    private void findSizesTypes() {
        startProgress();
        addProductService.getSizesTypes(this);
    }

    public static void foundSizesTypes(AddProductFragment addProductFragment, List<String> newSizeTypeList) {
        addProductFragment.finishProgress();
        sizeTypeList = newSizeTypeList;
        addProductFragment.updateSpinner(newSizeTypeList);
    }

    private void updateSpinner(List<String> sizeList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sizeList);
        spinner_size.setAdapter(adapter);
    }

    public void startProgress() {
        utilService.showProgress(true, textView_addProduct, progressBar_loading);
    }

    public void finishProgress() {
        utilService.showProgress(false, textView_addProduct, progressBar_loading);
    }

    private void addNewProduct() {
        setupProductData();
        addProduct();
    }

    private void setupProductData() {
        productData.getBaseProduct().setPrice(Float.parseFloat(editText_price.getText().toString()));
        productData.getBaseProduct().setSize(Float.parseFloat(editText_size.getText().toString()));
        productData.getBaseProduct().setSizeUnit(spinner_size.getSelectedItem().toString());
        productData.getBaseProduct().setDescription(editText_productDescription.getText().toString());

    }

    private void addProduct() {
        //startProgress();
        addProductService.addProduct(this, productData);
    }

    public static void addedProduct(AddProductFragment addProductFragment, Short productSpecificId) {
        if (productSpecificId == null) {
            addProductFragment.outputString("Proizvod nije dodan, pokušajte ponovo");
            return;
        }
        addProductFragment.progress.setVisibility(View.GONE);
        addProductFragment.goToEnterBarcodeFragment(PRODUCT_ADDED);
    }

    private void addPhoto() {
        final Map config = new HashMap();
        config.put("cloud_name", "antunmod");
        config.put("api_key", "367855928352147");
        config.put("api_secret", "9Y45IQdgDo_sHUvviRdwaZb8ej4");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Cloudinary cloudinary = new Cloudinary(config);
                try {
                    Map result = cloudinary.uploader().upload(photoFile.getAbsolutePath(),
                            ObjectUtils.asMap("transformation",
                                    new Transformation().width(2000).height(1000).crop("limit")));
                    String uri = (String) result.get("url");
                    productData.getBaseProduct().setPhotoURI(uri);
                    addNewProduct();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void goToEnterBarcodeFragment(String outputMessage) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(this);
        trans.commit();

        Fragment fragment = manager.findFragmentByTag(SELECT_FRAGMENT_TAG);
        while (fragment != null)
            manager.beginTransaction().remove(fragment).commit();

        Bundle bundle = new Bundle();
        bundle.putString("outputMessage", outputMessage);
        EnterBarcodeFragment enterBarcodeFragment = new EnterBarcodeFragment();
        enterBarcodeFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, enterBarcodeFragment)
                .commit();

    }

    private void outputString(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public static void setErrorString(String error) {
        errorString = error;
    }

    public Boolean getProductBeingAdded() {
        return productBeingAdded;
    }

}
