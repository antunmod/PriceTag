package antunmod.projects.pricetag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindProductForBarcodeFragment extends Fragment {

    Button btn_addProduct;
    View inflatedView;

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
                Fragment fragment = new AddProductFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.layout_for_fragment, fragment);
                transaction.commit();
            }
        });
        return inflatedView;
    }

}
