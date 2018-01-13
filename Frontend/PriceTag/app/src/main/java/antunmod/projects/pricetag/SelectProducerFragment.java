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
 * {@link SelectProducerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectProducerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectProducerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ProductStore productStore;
    private List<String> producerList;
    private String subcategoryName;

    public SelectProducerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectProducerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectProducerFragment newInstance(String param1, String param2) {
        SelectProducerFragment fragment = new SelectProducerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            productStore = (ProductStore) bundle.getSerializable("productStore");
            producerList = bundle.getStringArrayList("producerList");
            subcategoryName = bundle.getString("subcategoryName");
        }
    }

    private View inflatedView;
    private ListView listView_producer;
    private Product product = new Product();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_select_producer, container, false);
        listView_producer = inflatedView.findViewById(R.id.listView_producer);
        listView_producer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedProducer = listView_producer.getItemAtPosition(i).toString();
                findProductsForSubcategoryNameAndProducer(selectedProducer);
            }
        });

        String[] categoryArrayList;
        if(producerList!=null) {
            int listSize = producerList.size();
            categoryArrayList = new String[listSize];

            for(int i = 0; i<listSize; ++i) {
                categoryArrayList[i] = producerList.get(i);
            }
        }
        else {
            categoryArrayList = new String[0];
        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                categoryArrayList
        );

        listView_producer.setAdapter(listViewAdapter);

        return inflatedView;
    }


    private void findProductsForSubcategoryNameAndProducer(String producer) {
        product.setProducer(producer);
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getProductNamesForSubcategoryNameAndProducer(subcategoryName, producer);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> productList = (ArrayList) response.body();
                if (productList != null) {
                    goToSelectProductFragment(productList);

                } else {
                    Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToSelectProductFragment(ArrayList<String> productList) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        bundle.putStringArrayList("productList", productList);
        bundle.putSerializable("productStore", productStore);
        bundle.putString("subcategoryName", subcategoryName);
        SelectProductFragment selectProductFragment = new SelectProductFragment();
        selectProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, selectProductFragment)
                .addToBackStack("selectProducer")
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
