package antunmod.projects.pricetag.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.view.activity.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateProductFragment extends Fragment {


    private final Float BOTTOM_LIMIT_FOR_CORRECT_PRICE_FACTOR = (float) 0.5;
    private final Float TOP_LIMIT_FOR_CORRECT_PRICE_FACTOR = (float) 1.5;

    private byte[] photoByteArray;

    private OnFragmentInteractionListener mListener;

    public UpdateProductFragment() {
        // Required empty public constructor
    }


    public static UpdateProductFragment newInstance(String param1, String param2) {
        UpdateProductFragment fragment = new UpdateProductFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    private UpdateProduct updateProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            updateProduct = (UpdateProduct) bundle.getSerializable("updateProduct");
            Byte[] photoObjectPhotoArray = (Byte[]) bundle.getSerializable("photoByteArray");
            setBytePhotoArray(photoObjectPhotoArray);
        }
    }

    View inflatedView;
    ImageView imageView_updateProduct;
    TextView textView_producer;
    TextView textView_productNameAndSize;
    TextView textView_averagePrice;
    EditText editText_newPrice;
    TextView textView_updateProduct;
    String updateProductDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_update_product, container, false);
        textView_producer = inflatedView.findViewById(R.id.textView_producer);
        textView_productNameAndSize = inflatedView.findViewById(R.id.textView_product_name_and_size);
        textView_averagePrice = inflatedView.findViewById(R.id.textView_average_price);
        editText_newPrice = inflatedView.findViewById(R.id.editText_new_price);
        textView_updateProduct = inflatedView.findViewById(R.id.textView_update_product);

        imageView_updateProduct = inflatedView.findViewById(R.id.imageView_update_product);

        setImageView();


        textView_producer.setText(updateProduct.getProducer());
        String productNameAndSize = updateProduct.getName() + ", " +
                updateProduct.getSize() + " " + updateProduct.getSizeType();
        textView_productNameAndSize.setText(productNameAndSize);
        textView_averagePrice.setText(Float.toString(updateProduct.getAveragePrice()) + " kn");


        textView_updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_newPrice.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Unesite novu cijenu proizvoda", Toast.LENGTH_SHORT).show();
                } else {
                    Float newPrice = Float.valueOf(editText_newPrice.getText().toString());
                    Float averagePrice = updateProduct.getAveragePrice();
                    if (newPrice > TOP_LIMIT_FOR_CORRECT_PRICE_FACTOR * averagePrice ||
                            newPrice < BOTTOM_LIMIT_FOR_CORRECT_PRICE_FACTOR * averagePrice) {
                        Toast.makeText(getContext(), "Zbog velike izmjene cijene, proizvod se šalje na potvrdu prije spremanja.", Toast.LENGTH_SHORT).show();
                    } else {
                        Integer productUpdates = updateProduct.getProductUpdates();
                        averagePrice = averagePrice * productUpdates + newPrice;
                        averagePrice /= ++productUpdates;
                        updateProduct.setAveragePrice(averagePrice);
                        updateProductDate = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                        updateProduct.setPriceChangeDate(updateProductDate);
                        updateProduct.setPrice(newPrice);
                        updateProduct.setUserId(HomeActivity.user.getUserId());
                        saveUpdatedProduct(updateProduct);
                    }

                }
            }
        });


        return inflatedView;
    }

    private void setImageView() {

        Bitmap bitmap = BitmapFactory.decodeByteArray(photoByteArray, 0, photoByteArray.length);
        imageView_updateProduct.setImageBitmap(bitmap);

    }

    private void saveUpdatedProduct(UpdateProduct updateProduct) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Boolean> call = restServiceClient.saveUpdatedProduct(updateProduct);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                boolean productWasSaved = response.body();
                if (response.body() != null) {
                    //awardPointsToUser();
                    String toastString = "Proizvod " + (productWasSaved ? " je " : " nije ") + "spremljen";
                    if (!productWasSaved) {
                        Toast.makeText(getContext(), toastString, Toast.LENGTH_SHORT).show();
                    } else {
                        goToEnterBarcodeFragment(toastString);
                    }
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

    private void setBytePhotoArray(Byte[] byteObjectPhotoArray) {
        Integer j = 0;

        photoByteArray = new byte[byteObjectPhotoArray.length];
        for (Byte b : byteObjectPhotoArray)
            photoByteArray[j++] = b;
    }

    private void goToEnterBarcodeFragment(String toastString) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(this);
        trans.commit();

        // Pop enterBarcode, selectStore and selectStoreLocation Back Stack
        manager.popBackStack();

        Bundle bundle = new Bundle();
        bundle.putString("toastString", toastString);
        EnterBarcodeFragment enterBarcodeFragment = new EnterBarcodeFragment();
        enterBarcodeFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, enterBarcodeFragment)
                .commit();
    }

    private void awardPointsToUser() {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Integer> call = restServiceClient.awardPointsToUserForUserId(updateProduct.getUserId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer numberOfPoints = response.body();
                if (response.body() != null) {
                    Toast.makeText(getContext(), "Dodijeljeno vam je " + numberOfPoints + "bodova.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Nešto je pošlo po krivu. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

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