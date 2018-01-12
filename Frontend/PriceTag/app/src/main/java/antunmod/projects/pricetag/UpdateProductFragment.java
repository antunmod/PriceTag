package antunmod.projects.pricetag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final float BOTTOM_LIMIT_FOR_CORRECT_PRICE_FACTOR = (float) 0.5;
    private final float TOP_LIMIT_FOR_CORRECT_PRICE_FACTOR = (float) 1.5;


    private OnFragmentInteractionListener mListener;

    public UpdateProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateProductFragment newInstance(String param1, String param2) {
        UpdateProductFragment fragment = new UpdateProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private UpdateProduct updateProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            updateProduct =  (UpdateProduct) bundle.getSerializable("updateProduct");
        }
    }

    View inflatedView;
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

        textView_producer.setText(updateProduct.getProducer());
        String productNameAndSize = updateProduct.getName() + ", " +
                updateProduct.getSize() + " " + updateProduct.getSizeType();
        textView_productNameAndSize.setText(productNameAndSize);
        textView_averagePrice.setText(Float.toString(updateProduct.getAveragePrice()) + " kn");
        
        textView_updateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText_newPrice.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Unesite novu cijenu proizvoda", Toast.LENGTH_SHORT).show();
                }
                else {
                    float newPrice = Float.valueOf(editText_newPrice.getText().toString());
                    float averagePrice = updateProduct.getAveragePrice();
                    if(newPrice > TOP_LIMIT_FOR_CORRECT_PRICE_FACTOR*averagePrice ||
                            newPrice < BOTTOM_LIMIT_FOR_CORRECT_PRICE_FACTOR*averagePrice) {
                        Toast.makeText(getContext(), "Zbog velike izmjene cijene, proizvod se šalje na potvrdu prije spremanja.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int productUpdates = updateProduct.getProductUpdates();
                        averagePrice = averagePrice*productUpdates + newPrice;
                        averagePrice /= productUpdates+1;
                        updateProduct.setAveragePrice(averagePrice);
                        updateProductDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
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

    private void saveUpdatedProduct(UpdateProduct updateProduct) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Boolean> call = restServiceClient.saveUpdatedProduct(updateProduct);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                boolean productWasSaved = response.body();
                if (response.body()!=null) {
                    //awardPointsToUser();
                    String toastString = "Proizvod " + (productWasSaved? " je ":" nije ") + "spremljen";
                    if(!productWasSaved) {
                        Toast.makeText(getContext(), toastString, Toast.LENGTH_SHORT).show();
                    }
                    else {
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

    private void goToEnterBarcodeFragment(String toastString) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(this);
        trans.commit();

        // Pop enterBarcode, selectStore and selectStoreLocation Back Stack
        manager.popBackStack();
        manager.popBackStack();
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
                if (response.body()!=null) {
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
