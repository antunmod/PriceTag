package antunmod.projects.pricetag.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import antunmod.projects.pricetag.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentProductsFragment extends Fragment {


    public RecentProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_products, container, false);
    }

}
