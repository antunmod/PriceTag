package antunmod.projects.pricetag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectSectorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectSectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectSectorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<String> sectorList;

    private OnFragmentInteractionListener mListener;

    public SelectSectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectSectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectSectorFragment newInstance(String param1, String param2) {
        SelectSectorFragment fragment = new SelectSectorFragment();
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

        getSectorList();

    }

    private void getSectorList() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<ProductDetails> call = restServiceClient.findProductForBarcode(barcode);
        call.enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                ProductDetails productDetails = response.body();
                if (productDetails.getName() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("productDetails", productDetails);
                    AddProductFragment addProductFragment = new AddProductFragment();
                    addProductFragment.setArguments(bundle);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.layout_for_fragment, addProductFragment)
                            .commit();

                } else {
                    Bundle bundle = new Bundle();
                    productDetails.setBarcode(barcode);
                    bundle.putSerializable("productDetails", productDetails);
                    SelectSectorFragment selectSectorFragment = new SelectSectorFragment();
                    selectSectorFragment.setArguments(bundle);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.layout_for_fragment, selectSectorFragment)
                            .commit();
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ListView listView;
    View inflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.inflatedView = inflater.inflate(R.layout.fragment_find_product_for_barcode, container, false);
        listView = (ListView) inflatedView.findViewById(R.id.listView_sector);
        String[] items = {"Prvi", "Drugi", "Treći"};
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );

        listView.setAdapter(listViewAdapter);

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
}
