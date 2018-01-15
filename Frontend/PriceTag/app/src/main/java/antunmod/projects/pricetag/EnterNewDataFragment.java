package antunmod.projects.pricetag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EnterNewDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EnterNewDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterNewDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String title;

    private final String STORE = "Trgovina";
    private final String STORE_ADDRESS = "Adresa trgovine";
    private final String SECTOR = "Sektor";
    private final String CATEGORY = "Kategorija";
    private final String SUBCATEGORY = "Potkategorija";
    private final String PRODUCER = "Proizvođač";
    private final String PRODUCT = "Proizvod";
    private final String SIZE = "Veličina";

    private Store store = new Store();

    private OnFragmentInteractionListener mListener;

    public EnterNewDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnterNewDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EnterNewDataFragment newInstance(String param1, String param2) {
        EnterNewDataFragment fragment = new EnterNewDataFragment();
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

        Bundle bundle = getArguments();

        if(bundle!=null) {
            title = bundle.getString("title");
        }
    }

    private View inflatedView;
    private TextView textView_title;
    private EditText editText_new_data;
    private TextView textView_next;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflatedView = inflater.inflate(R.layout.fragment_enter_new_data, container, false);

        textView_title = inflatedView.findViewById(R.id.textView_new_data);
        editText_new_data = inflatedView.findViewById(R.id.editText_new_data);
        textView_next = inflatedView.findViewById(R.id.textView_next);

        textView_title.setText(title);

        textView_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText_new_data.getText().toString();
                if(text.isEmpty()) {
                    Toast.makeText(getContext(), "Unesite naziv!", Toast.LENGTH_SHORT).show();
                }
                else
                    proceed(text);
            }
        });

        return inflatedView;
    }


    private void proceed(String text) {
        /*switch(title) {
            case STORE: store.setStoreName(text);
                updateFragment(STORE_ADDRESS);
            case STORE_ADDRESS: store.setStoreAddress(text);
                updateFragment(STORE, storeStringArray);
                break;
            case SECTOR:  updateFragment(STORE_ADDRESS, storeAddressStringArray);
                break;
            case CATEGORY:  updateFragment(SECTOR, sectorStringArray);
                break;
            case SUBCATEGORY:  updateFragment(CATEGORY, categoryStringArray);
                break;
            case PRODUCER:  updateFragment(SUBCATEGORY, subcategoryStringArray);
                break;
            case PRODUCT:  updateFragment(PRODUCER, producerStringArray);
                break;
            case SIZE:  updateFragment(PRODUCT, productStringArray);
                break;
        }*/
    }

    private void updateFragment(String title) {
        textView_title.setText(title);

        this.title = title;
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

    public boolean onBackPressed() {
        /*switch(title) {
            case STORE: return false;
            case STORE_ADDRESS: updateFragment(STORE);
                break;
            case SECTOR:  updateFragment(STORE_ADDRESS, storeAddressStringArray);
                break;
            case CATEGORY:  updateFragment(SECTOR, sectorStringArray);
                break;
            case SUBCATEGORY:  updateFragment(CATEGORY, categoryStringArray);
                break;
            case PRODUCER:  updateFragment(SUBCATEGORY, subcategoryStringArray);
                break;
            case PRODUCT:  updateFragment(PRODUCER, producerStringArray);
                break;
            case SIZE:  updateFragment(PRODUCT, productStringArray);
                break;
        }*/
        return true;
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
