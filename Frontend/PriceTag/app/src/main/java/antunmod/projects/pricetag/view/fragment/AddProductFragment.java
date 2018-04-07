package antunmod.projects.pricetag.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.Product;
import antunmod.projects.pricetag.model.ProductStore;
import antunmod.projects.pricetag.view.activity.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    private static final int CAMERA_REQUEST = 1;
    private static final float MAX_SIZE_VALUE = (float) 50.0;
    private static final float MAX_PRICE_VALUE = (float) 10000.0;
    private static final int NON_EXISTING_PRODUCT_ID = 0;
    private static final int FIRST_PRODUCT_UPDATE = 1;

    private Product product;
    private ProductStore productStore;
    private byte[] photo;
    boolean pictureSet = false;
    private int subcategoryId;

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
            product = (Product) bundle.getSerializable("product");
            productStore = (ProductStore) bundle.getSerializable("productStore");
            subcategoryId = bundle.getInt("subcategoryId");
        }


    }

    private View inflatedView;
    private ImageView imageView_addProduct;
    private TextView textView_producer;
    private TextView textView_productName;
    private EditText editText_size;
    private Spinner spinner_size;
    private EditText editText_price;
    private TextView textView_addProduct;

    private String sizeString;
    private String priceString;

    private float size;
    private float price;

    private List<String> sizeTypeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_add_product, container, false);

        imageView_addProduct = inflatedView.findViewById(R.id.imageView_add_product);
        textView_producer = inflatedView.findViewById(R.id.textView_producer);
        textView_productName = inflatedView.findViewById(R.id.textView_product_name);
        editText_size = inflatedView.findViewById(R.id.editText_size);
        spinner_size = inflatedView.findViewById(R.id.spinner_size);
        editText_price = inflatedView.findViewById(R.id.editText_price);
        textView_addProduct = inflatedView.findViewById(R.id.textView_add_product);

        imageView_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });

        textView_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fieldsAreValid()) {
                    if (product.getProductId() == NON_EXISTING_PRODUCT_ID)
                        addProduct();

                }
            }
        });

        setProducerAndProductName();
        getSizesTypes();
        return inflatedView;
    }

    public void callCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                if (bmp.getHeight() > bmp.getWidth()) {
                    Toast.makeText(getContext(), "Orijentacija slike mora biti horizontalna!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 90, stream);
                photo = stream.toByteArray();

                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0,
                        photo.length);

                imageView_addProduct.setImageBitmap(bitmap);
                pictureSet = true;
            }
        }
    }

    private void setProducerAndProductName() {
        textView_producer.setText(product.getProducer());
        textView_productName.setText(product.getProductName());

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
            toastMessage = "Veličina prevelika. Unesite manji broj";

        else if ((price = Float.parseFloat(priceString)) > MAX_PRICE_VALUE)
            toastMessage = "Cijena prevelika. Unesite manji broj";

        if (toastMessage.isEmpty())
            return true;

        Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();

        return false;
    }

    private void getSizesTypes() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getSizeTypes();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> tmp = response.body();
                if (tmp != null) {
                    saveSizeTypeList(tmp);
                } else {
                    Toast.makeText(getContext(), "Nešto je pošlo po krivu. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveSizeTypeList(List<String> sizeTypeList) {

        this.sizeTypeList = sizeTypeList;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                sizeTypeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_size.setAdapter(adapter);

    }

    private void addProduct() {

        AddProduct addProduct = createAddProduct();
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Boolean> call = restServiceClient.addProduct(addProduct);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean productSavedSuccessfully = response.body();

                if (productSavedSuccessfully != null) {
                    goToEnterBarcodeFragment();

                } else {
                    Toast.makeText(getContext(), "Nešto je pošlo po krivu. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private AddProduct createAddProduct() {

        AddProduct addProduct = new AddProduct();
        addProduct.setProductName(product.getProductName());
        addProduct.setProducer(product.getProducer());
        addProduct.setPhoto(photo);
        addProduct.setStoreId(productStore.getStoreId());
        addProduct.setUserId(HomeActivity.user.getUserId());
        addProduct.setBarcode(productStore.getBarcode());
        addProduct.setProductDescription(productStore.getProductDescription());
        addProduct.setProductSize(size);
        addProduct.setProductSizeId(spinner_size.getSelectedItemPosition() + 1);
        addProduct.setPrice(price);
        addProduct.setPriceChangeDate(getDateString());
        addProduct.setAveragePrice(price);
        addProduct.setSubcategoryId(subcategoryId);

        return addProduct;
    }

    private String getDateString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);

    }

    private void goToEnterBarcodeFragment() {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(this);
        trans.commit();

        // Pop selectFrament
        manager.popBackStack();

        String toastString = "Uspješno ste dodali proizvod  " + product.getProductName();

        Bundle bundle = new Bundle();
        bundle.putString("toastString", toastString);
        EnterBarcodeFragment enterBarcodeFragment = new EnterBarcodeFragment();
        enterBarcodeFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, enterBarcodeFragment)
                .commit();

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
}
