package antunmod.projects.pricetag.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.RestServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnterBarcodeFragment extends Fragment {

    Button btn_addProduct;
    View inflatedView;
    EditText editText_barcode;

    public EnterBarcodeFragment() {
        // Required empty public constructor
    }

    String barcode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        this.inflatedView = inflater.inflate(R.layout.fragment_enter_barcode, container, false);
        btn_addProduct = inflatedView.findViewById(R.id.btn_find_product_for_barcode);
        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                barcode = editText_barcode.getText().toString();
                if (barcode.isEmpty()) {
                    Toast.makeText(getContext(), "Unesite barkod", Toast.LENGTH_SHORT).show();
                } else {

                    RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
                    Call<List<String>> call = restServiceClient.getStoreNames();
                    call.enqueue(new Callback<List<String>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                            List<String> storeList = response.body();
                            if (storeList != null && storeList.size() > 0) {
                                goToSelectFragment(storeList);

                            } else {
                                Toast.makeText(getContext(), "Nešto je pošlo po krivu. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {
                            Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        editText_barcode = inflatedView.findViewById(R.id.editText_find_product_for_barcode);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.get("outputMessage") != null) {
                Toast.makeText(getContext(), bundle.getString("outputMessage"), Toast.LENGTH_SHORT).show();
                bundle.remove("outputMessage");
            }
        }

        return inflatedView;
    }

    private void goToSelectFragment(List<String> storeList) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("storeList", (ArrayList<String>) storeList);
        bundle.putString("barcode", barcode);
        SelectFragment selectFragment = new SelectFragment();
        selectFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, selectFragment)
                .addToBackStack("enterBarcodeFragment")
                .commit();
    }

}
