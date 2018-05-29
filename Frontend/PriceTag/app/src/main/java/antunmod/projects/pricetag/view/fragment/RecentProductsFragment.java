package antunmod.projects.pricetag.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.GridViewAdapter;
import antunmod.projects.pricetag.model.ImageItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentProductsFragment extends Fragment {


    public RecentProductsFragment() {
        // Required empty public constructor
    }

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;

    ArrayList<ImageItem> imageItems;

    View inflatedView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_recent_products, container, false);

        gridView = inflatedView.findViewById(R.id.gridView);
        return inflatedView;
    }

}
