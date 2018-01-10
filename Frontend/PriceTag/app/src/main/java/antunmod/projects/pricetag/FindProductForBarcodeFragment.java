package antunmod.projects.pricetag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindProductForBarcodeFragment extends Fragment {

    Button btn_addProduct;
    View inflatedView;
    EditText editText_barcode;

    public FindProductForBarcodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.inflatedView = inflater.inflate(R.layout.fragment_find_product_for_barcode, container, false);
        btn_addProduct = inflatedView.findViewById(R.id.btn_find_product_for_barcode);
        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String barcode = editText_barcode.getText().toString();
                if (barcode.isEmpty()) {
                    Toast.makeText(getContext(), "Unesite barkod", Toast.LENGTH_SHORT).show();
                } else {

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

                            }
                        }

                        @Override
                        public void onFailure(Call<ProductDetails> call, Throwable t) {
                        }
                    });


                    Fragment fragment = new AddProductFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.layout_for_fragment, fragment);
                    transaction.commit();
                }
            }
        });

        editText_barcode = inflatedView.findViewById(R.id.editText_barcode);

        return inflatedView;
    }

}
