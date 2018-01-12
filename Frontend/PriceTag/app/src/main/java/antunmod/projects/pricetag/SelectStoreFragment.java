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
 * {@link SelectStoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectStoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SelectStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectStoreFragment newInstance(String param1, String param2) {
        SelectStoreFragment fragment = new SelectStoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private List<String> storeList;
    private String barcode;

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
            storeList =  bundle.getStringArrayList("storeList");
            barcode = bundle.getString("barcode");
        }
    }

    private View inflatedView;
    private ListView listView_store;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_select_store, container, false);
        listView_store = inflatedView.findViewById(R.id.listView_store);

        listView_store.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedStore = listView_store.getItemAtPosition(i).toString();
                findStoreLocations(selectedStore);
            }
        });
        String[] storeArrayList;
        if(storeList!=null) {
            int listSize = storeList.size();
            storeArrayList = new String[listSize];

            for(int i = 0; i<listSize; ++i) {
                storeArrayList[i] = storeList.get(i);
            }
        }
        else {
            storeArrayList = new String[0];
        }


        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                storeArrayList
        );
        listView_store.setAdapter(listViewAdapter);


        return inflatedView;
    }

    private void findStoreLocations(String selectedStore) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getStoreLocations(selectedStore);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> storeLocations = response.body();
                if (storeLocations != null && storeLocations.size()>0) {
                    goToSelectStoreLocationFragment(storeLocations);
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

    private void goToSelectStoreLocationFragment(List<String> storeLocations) {
        Bundle bundle = new Bundle();
        //bundle.putStringArrayList("storeList", (ArrayList) storeList);
        bundle.putStringArrayList("storeLocations", (ArrayList) storeLocations);
        bundle.putString("barcode", barcode);
        SelectStoreLocationFragment selectStoreLocationFragment = new SelectStoreLocationFragment();
        selectStoreLocationFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, selectStoreLocationFragment)
                .addToBackStack("selectStoreFragment")
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