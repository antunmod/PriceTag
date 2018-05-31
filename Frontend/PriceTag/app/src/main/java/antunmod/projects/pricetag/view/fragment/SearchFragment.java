package antunmod.projects.pricetag.view.fragment;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.GridViewAdapter;
import antunmod.projects.pricetag.model.ImageItem;
import antunmod.projects.pricetag.service.SearchService;
import antunmod.projects.pricetag.service.UtilService;
import antunmod.projects.pricetag.transfer.SearchFilter;
import antunmod.projects.pricetag.transfer.SearchProductData;
import antunmod.projects.pricetag.transfer.StoreProductPrice;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private final String IMAGE_FOLDER_LOCATION = "C:/Users/antun/Documents/Projekti/PriceTag - Photos/";
    private final String IMAGE_NAME = "original.jpg";

    public SearchFragment() {
        // Required empty public constructor
    }

    private SearchService searchService;

    private final String SUPERMARKETS = "Supermarketi";

    private final String ERROR_STRING = "Došlo je do greške";

    private Button btn_search;
    private EditText editText_productName;
    private GridView gridView;
    private FloatingActionButton fab_filter;
    private SearchFilter searchFilter;
    private ArrayList<SearchProductData> searchProductDataList;

    private SearchProductData selectedProductData;

    private GridViewAdapter gridViewAdapter;

    ArrayList<ImageItem> imageItems;

    View inflatedView;

    CheckBox checkBox_category;
    CheckBox checkBox_subcategory;
    CheckBox checkBox_producer;
    CheckBox checkBox_store;

    Spinner spinner_category;
    Spinner spinner_subcategory;
    Spinner spinner_producer;
    Spinner spinner_store;

    Integer productNumber = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_search, container, false);

        btn_search = inflatedView.findViewById(R.id.btn_search);
        editText_productName = inflatedView.findViewById(R.id.editText_product_name);
        gridView = inflatedView.findViewById(R.id.gridView);
        fab_filter = inflatedView.findViewById(R.id.fab_filter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFilter.setProductName(editText_productName.getText().toString());
                setGridView(new ArrayList<ImageItem>());
                productNumber = 0;
                UtilService.hideKeyboardFrom(getContext(), getView());
                findProducts();
            }
        });

        //gridViewAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, getData());

        fab_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValues();
                showFilterDialog();
            }
        });

        searchService = new SearchService();
        searchFilter = new SearchFilter();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProductData = searchProductDataList.get(position);
                if (selectedProductData != null)
                    getLocationsForProductSpecificId(selectedProductData.getProductSpecificId());
            }
        });

        showFilterDialog();

        return inflatedView;
    }

    private void getLocationsForProductSpecificId(Short productSpecificId) {
        searchService.getLocationsForProductSpecificId(this, productSpecificId);

    }

    private void resetValues() {
        searchFilter = new SearchFilter();
    }

    private void findProducts() {
        searchService.findProducts(this, searchFilter);
        imageItems = new ArrayList<>();
    }

    public static void foundProducts(SearchFragment searchFragment, ArrayList<SearchProductData> searchProductDataList) {
        searchFragment.searchProductDataList = searchProductDataList;
        searchFragment.findNextImage();
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
            Picasso.with(getContext()).load(searchProductData.getImageURI()).into(new Target() {

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    updateGridView(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
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


    public void showFilterDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog);

        final CheckBox checkBox_category = dialog.findViewById(R.id.checkBox_category);
        final CheckBox checkBox_subcategory = dialog.findViewById(R.id.checkBox_subcategory);
        final CheckBox checkBox_producer = dialog.findViewById(R.id.checkBox_producer);
        final CheckBox checkBox_store = dialog.findViewById(R.id.checkBox_store);

        final Spinner spinner_category = dialog.findViewById(R.id.spinner_category);
        final Spinner spinner_subcategory = dialog.findViewById(R.id.spinner_subcategory);
        final Spinner spinner_producer = dialog.findViewById(R.id.spinner_producer);
        final Spinner spinner_store = dialog.findViewById(R.id.spinner_store);

        /*spinner_category.setEnabled(false);
        spinner_subcategory.setEnabled(false);
        spinner_producer.setEnabled(false);
        spinner_store.setEnabled(false);*/


        Button btn_saveFilter = dialog.findViewById(R.id.btn_save_filter);


        checkBox_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkBox_category.isChecked())
                    return;
                findCategories();
            }
        });

        checkBox_subcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkBox_subcategory.isChecked())
                    return;
                if (!checkBox_category.isChecked()) {
                    Toast.makeText(getContext(), "Prvo odaberite kategoriju", Toast.LENGTH_SHORT).show();
                    checkBox_subcategory.setChecked(false);
                    return;
                }
                findSubcategoriesForCategoryName(spinner_category.getSelectedItem().toString());
            }
        });

        checkBox_producer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkBox_producer.isChecked())
                    return;
                findProducers();
            }
        });

        checkBox_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkBox_store.isChecked())
                    return;
                findStores();
            }
        });

        btn_saveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox_category.isChecked())
                    searchFilter.setCategoryName(spinner_category.getSelectedItem().toString());

                if (checkBox_subcategory.isChecked())
                    searchFilter.setSubcategoryName(spinner_subcategory.getSelectedItem().toString());

                if (checkBox_producer.isChecked())
                    searchFilter.setProducerName(spinner_producer.getSelectedItem().toString());

                if (checkBox_store.isChecked())
                    searchFilter.setStoreName(spinner_store.getSelectedItem().toString());
                dialog.dismiss();
            }
        });

        /*
            Set copies of final objects.
         */
        this.checkBox_category = checkBox_category;
        this.checkBox_subcategory = checkBox_subcategory;
        this.checkBox_producer = checkBox_producer;
        this.checkBox_store = checkBox_store;

        this.spinner_category = spinner_category;
        this.spinner_subcategory = spinner_subcategory;
        this.spinner_producer = spinner_producer;
        this.spinner_store = spinner_store;

        dialog.show();
    }


    private void findCategories() {
        searchService.findCategoriesForSectorName(this, SUPERMARKETS);
    }

    public static void foundCategories(SearchFragment searchFragment, List<String> categoryList) {
        searchFragment.updateSpinner(searchFragment, searchFragment.spinner_category, categoryList);
    }

    private void findSubcategoriesForCategoryName(String categoryName) {
        searchService.findSubcategoriesForCategoryName(this, categoryName);
    }

    public static void foundSubcategoriesForCategoryName(SearchFragment searchFragment, List<String> subcategoryList) {
        searchFragment.updateSpinner(searchFragment, searchFragment.spinner_subcategory, subcategoryList);
    }

    private void findProducers() {
        String subcategoryName = "";
        if (checkBox_subcategory.isChecked())
            subcategoryName = spinner_subcategory.getSelectedItem().toString();
        searchService.findProducers(this, subcategoryName);
    }

    public static void foundProducers(SearchFragment searchFragment, List<String> producerList) {
        searchFragment.updateSpinner(searchFragment, searchFragment.spinner_producer, producerList);
    }

    private void findStores() {
        searchService.findStores(this);
    }

    public static void foundStores(SearchFragment searchFragment, List<String> storeList) {
        searchFragment.updateSpinner(searchFragment, searchFragment.spinner_store, storeList);
    }

    private static void updateSpinner(SearchFragment searchFragment, Spinner spinner, List<String> dataList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(searchFragment.getContext(), android.R.layout.simple_spinner_dropdown_item, dataList);
        spinner.setAdapter(adapter);
    }

    public static void foundLocations(SearchFragment searchFragment, ArrayList<StoreProductPrice> storeProductPriceList) {
        searchFragment.goToProductFragment(storeProductPriceList);
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
                .addToBackStack("searchFragment")
                .commit();
    }

}
