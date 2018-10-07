package antunmod.projects.pricetag.view.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.service.ProductService;
import antunmod.projects.pricetag.service.UtilService;
import antunmod.projects.pricetag.transfer.InformationFeedback;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.transfer.StoreProductPrice;
import antunmod.projects.pricetag.view.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    ArrayList<StoreProductPrice> storeProductPriceList;
    SearchProductData searchProductData;
    View inflatedView;
    ImageView imageView;
    TextView textView_product;
    TextView textView_producer;
    ListView listView_storeProductPrice;

    /*
        Contains dialog copy for dismissing dialog after receiving response from server.
     */
    private Dialog dialog;

    private final String NEGATIVE_FEEDBACK = "N";
    private final String POSITIVE_FEEDBACK = "P";
    private final String FEEDBACK_ADDED = "Vaša ocjena je zabilježena";
    private final String FEEDBACK_NOT_ADDED = "Vaša ocjena nije zabilježena";
    private final String CANNOT_RATE_YOUR_PROUDUCT_UPDATE = "Ne možete ocjenjivati proizvod koji ste dodali";


    private ProductService productService;

    private InformationFeedback informationFeedbackForSelected;
    private StoreProductPrice selectedStoreProductPrice;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();


        if (bundle != null) {
            storeProductPriceList = (ArrayList<StoreProductPrice>) bundle.getSerializable("storeProductPriceList");
            searchProductData = (SearchProductData) bundle.getSerializable("searchProductData");
        }

        UtilService.hideKeyboard(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_product, container, false);

        productService = new ProductService();

        imageView = inflatedView.findViewById(R.id.imageView_product);
        textView_product = inflatedView.findViewById(R.id.textView_product);
        textView_producer = inflatedView.findViewById(R.id.textView_producer);
        listView_storeProductPrice = inflatedView.findViewById(R.id.listView_store_product_price);

        Picasso.with(getContext()).load(searchProductData.getImageURI()).into(imageView);

        ListViewAdapter listViewAdapter = new ListViewAdapter();
        listView_storeProductPrice.setAdapter(listViewAdapter);

        listView_storeProductPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (HomeActivity.user.getId().equals(storeProductPriceList.get(position).getUserId()))
                    Toast.makeText(getContext(), CANNOT_RATE_YOUR_PROUDUCT_UPDATE, Toast.LENGTH_SHORT).show();
                else {
                    selectedStoreProductPrice = storeProductPriceList.get(position);
                    findInformationFeedbackForUserAndPriceId(position);
                }
            }
        });

        String productName = searchProductData.getProductName();
        if (!searchProductData.getProductDescription().isEmpty())
            productName += " " + searchProductData.getProductDescription();
        productName += " " + searchProductData.getProductSize();
        textView_product.setText(productName);
        textView_producer.setText(searchProductData.getProducerName());
        return inflatedView;
    }

    private void findInformationFeedbackForUserAndPriceId(int position) {
        productService.getInformationFeedbackForUserAndPriceId(this, HomeActivity.user.getId(),
                storeProductPriceList.get(position).getPriceId());
    }

    public static void foundInformationFeedback(ProductFragment productFragment, InformationFeedback informationFeedback) {
        productFragment.informationFeedbackForSelected = informationFeedback;
        productFragment.showInformationFeedbackDialog();

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

    public class ListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return storeProductPriceList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.store_product_price_list_item, null);

            TextView textView_storeName = view.findViewById(R.id.textView_store_name);
            TextView textView_storeAddress = view.findViewById(R.id.textView_store_address);
            TextView textView_price = view.findViewById(R.id.textView_price);
            TextView textView_userRating = view.findViewById(R.id.textView_user_rating);

            textView_storeName.setText(storeProductPriceList.get(i).getStoreName());
            textView_storeAddress.setText(storeProductPriceList.get(i).getStoreAddress());
            String price = storeProductPriceList.get(i).getPrice() + " kn";
            textView_price.setText(price);
            textView_userRating.setText(storeProductPriceList.get(i).getUserRating());

            return view;
        }
    }

    public void showInformationFeedbackDialog() {
        final Dialog dialog = new Dialog(getContext());
        this.dialog = dialog;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.information_feedback_dialog);

        final TextView textView_no = dialog.findViewById(R.id.textView_no);
        final TextView textView_yes = dialog.findViewById(R.id.textView_yes);

        if (informationFeedbackForSelected.getFeedback() != null) {
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0x00000000); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(5);
            gd.setStroke(2, 0xFFFF0000);
            switch(informationFeedbackForSelected.getFeedback()) {

                case "P": textView_yes.setBackground(gd);
                        break;
                case "N": textView_no.setBackground(gd);
            }
        }

        textView_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (informationFeedbackForSelected.getFeedback() == null || (informationFeedbackForSelected.getFeedback() != null
                        && !informationFeedbackForSelected.getFeedback().equals(NEGATIVE_FEEDBACK)))
                    saveInformationFeedback(NEGATIVE_FEEDBACK);
            }
        });

        textView_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (informationFeedbackForSelected.getFeedback() == null || (informationFeedbackForSelected.getFeedback() != null
                        && !informationFeedbackForSelected.getFeedback().equals(POSITIVE_FEEDBACK)))
                    saveInformationFeedback(POSITIVE_FEEDBACK);
            }
        });

        dialog.show();
    }

    private void saveInformationFeedback(String feedback) {
        InformationFeedback informationFeedback = new InformationFeedback(selectedStoreProductPrice.getPriceId(),
                informationFeedbackForSelected.getInformationProviderUserId(), HomeActivity.user.getId(), feedback);
        productService.saveInformationFeedback(this, informationFeedback);
    }

    public static void savedInformationFeedback(ProductFragment productFragment, Boolean success) {
        productFragment.dialog.dismiss();
        String output = productFragment.FEEDBACK_NOT_ADDED;
        if (success)
            output = productFragment.FEEDBACK_ADDED;
        Toast.makeText(productFragment.getContext(), output, Toast.LENGTH_SHORT).show();

    }

}
