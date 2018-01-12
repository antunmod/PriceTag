package antunmod.projects.pricetag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectStoreLocationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectStoreLocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectStoreLocationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectStoreLocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectStoreLocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectStoreLocationFragment newInstance(String param1, String param2) {
        SelectStoreLocationFragment fragment = new SelectStoreLocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    List<String> storeLocations;
    String barcode;
    private ListView listView_storeLocations;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();

        // Set values
        if (bundle != null) {
            storeLocations =  bundle.getStringArrayList("storeLocations");
            barcode = bundle.getString("barcode");
        }



    }

    private View inflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflatedView = inflater.inflate(R.layout.fragment_select_store_location, container, false);

        listView_storeLocations = inflatedView.findViewById(R.id.listView_store_locations);

        listView_storeLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedStoreAddress = listView_storeLocations.getItemAtPosition(i).toString();
                findProductForBarcodeAndStoreId(selectedStoreAddress);
            }
        });
        String[] storeLocationsArrayList;
        if(storeLocations!=null) {
            int listSize = storeLocations.size();
            storeLocationsArrayList = new String[listSize];

            for(int i = 0; i<listSize; ++i) {
                storeLocationsArrayList[i] = storeLocations.get(i);
            }
        }
        else {
            storeLocationsArrayList = new String[0];
        }


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                storeLocationsArrayList
        );
        listView_storeLocations.setAdapter(listViewAdapter);


        return inflatedView;
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

    private  void findProductForBarcodeAndStoreId(String storeAddress) {


        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<UpdateProduct> call = restServiceClient.getUpdateProductForBarcodeAndStoreAddress(barcode, storeAddress);
        call.enqueue(new Callback<UpdateProduct>() {
            @Override
            public void onResponse(Call<UpdateProduct> call, Response<UpdateProduct> response) {
                UpdateProduct updateProduct = response.body();
                if (updateProduct != null && updateProduct.getName()!= null) {
                    goToUpdateProductFragment(updateProduct);

                } else {
                    goToSelectSector();
                }
            }

            @Override
            public void onFailure(Call<UpdateProduct> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToSelectSector() {

    }

    private void goToUpdateProductFragment(UpdateProduct updateProduct) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateProduct", updateProduct);
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        updateProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, updateProductFragment)
                .addToBackStack("selectStoreLocation")
                .commit();
    }

}
