package antunmod.projects.pricetag.view.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.GridViewAdapter;
import antunmod.projects.pricetag.model.ImageItem;
import antunmod.projects.pricetag.service.RecentProductsService;
import antunmod.projects.pricetag.service.SearchService;
import antunmod.projects.pricetag.service.SelectService;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.transfer.StoreProductPrice;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentProductsFragment extends Fragment {


    public RecentProductsFragment() {
        // Required empty public constructor
    }

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private Target target;

    ArrayList<ImageItem> imageItems;

    View inflatedView;
    private Integer productNumber = 0;

    private ArrayList<SearchProductData> searchProductDataList;
    private RecentProductsService recentProductsService = new RecentProductsService();
    private SearchService searchService = new SearchService();
    private SearchProductData selectedProductData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflatedView = inflater.inflate(R.layout.fragment_recent_products, container, false);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                updateGridView(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {}

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {}
        };

        gridView = inflatedView.findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProductData = searchProductDataList.get(position);
                if (selectedProductData != null)
                    findLocationsForProductSpecificId(selectedProductData.getProductSpecificId());
            }
        });
        if (imageItems != null)
            setGridView(imageItems);
        else
            findProducts();
        return inflatedView;
    }

    private void findProducts() {
        recentProductsService.findRecentProducts(this);
        imageItems = new ArrayList<>();
    }

    public static void foundRecentProducts(RecentProductsFragment recentProductsFragment, ArrayList<SearchProductData> searchProductDataList) {
        recentProductsFragment.searchProductDataList = searchProductDataList;
        recentProductsFragment.findNextImage();
    }

    public void findNextImage() {
        if (productNumber < searchProductDataList.size()) {
            SearchProductData searchProductData = searchProductDataList.get(productNumber);

            // Throw away dummy objects
            if (searchProductData.getImageURI().length() < 30) {
                productNumber++;
                findNextImage();
                return;
            }
            Picasso.with(getContext()).load(searchProductData.getImageURI()).into(target);
        }

    }


    private void updateGridView(Bitmap bitmap) {

        SearchProductData searchProductData = searchProductDataList.get(productNumber);
        String text = searchProductData.getProducerName() + " " + searchProductData.getProductName() + " " +
                searchProductData.getProductDescription() + " " + searchProductData.getProductSize();
        ImageItem imageItem = new ImageItem(bitmap, text);

        imageItems.add(imageItem);
        setGridView(imageItems);
        productNumber++;
        findNextImage();
    }

    private void setGridView(ArrayList<ImageItem> imageItems) {
        gridViewAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, imageItems);
        gridView.setAdapter(gridViewAdapter);
    }

    private void findLocationsForProductSpecificId(Short productSpecificId) {
        searchService.getLocationsForProductSpecificId(this, productSpecificId);
    }

    public static void foundLocations(RecentProductsFragment recentProductsFragment, ArrayList<StoreProductPrice> storeProductPriceList) {
        recentProductsFragment.goToProductFragment(storeProductPriceList);
    }

    private void goToProductFragment(ArrayList<StoreProductPrice> storeProductPriceList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("storeProductPriceList", storeProductPriceList);
        bundle.putSerializable("searchProductData", selectedProductData);
        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, productFragment)
                .addToBackStack("recentProductsFragment")
                .commit();
    }
}
