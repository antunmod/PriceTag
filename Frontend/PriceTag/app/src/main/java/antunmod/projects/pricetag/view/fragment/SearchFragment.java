package antunmod.projects.pricetag.view.fragment;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Target target;
    private Dialog dialog;
    private String defaultString = "-";

    private List<String> categoriesList;
    private List<String> subcategoriesList;
    private List<String> producersList;
    private List<String> storesList;

    private List<String> emptyList;
    private SearchFragment searchFragment;

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

    Spinner spinner_category;
    Spinner spinner_subcategory;
    Spinner spinner_producer;
    Spinner spinner_store;

    Integer productNumber = 0;

    private Runnable updateGridViewOnUiThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_search, container, false);

        searchFragment = this;

        target = new Target() {

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
        };

        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_dialog);

        btn_search = inflatedView.findViewById(R.id.btn_search);
        editText_productName = inflatedView.findViewById(R.id.editText_product_name);
        gridView = inflatedView.findViewById(R.id.gridView);
        fab_filter = inflatedView.findViewById(R.id.fab_filter);

        emptyList = new ArrayList<>();
        emptyList.add(defaultString);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFilter.setProductName(editText_productName.getText().toString());
                imageItems = new ArrayList<>();
                setGridView(imageItems);
                productNumber = 0;
                UtilService.hideKeyboardFrom(getContext(), getView());
                findProducts();
            }
        });

        updateGridViewOnUiThread = new Runnable() {
            @Override
            public void run() {
                gridViewAdapter.notifyDataSetChanged();
            }
        };


        fab_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    findLocationsForProductSpecificId(selectedProductData.getProductSpecificId());
            }
        });

        if (imageItems != null) {
            setGridView(imageItems);
        } else {
            showFilterDialog();
        }

        return inflatedView;
    }

    private void findLocationsForProductSpecificId(Short productSpecificId) {
        searchService.getLocationsForProductSpecificId(this, productSpecificId);
    }

    public static void foundLocations(SearchFragment searchFragment, ArrayList<StoreProductPrice> storeProductPriceList) {
        searchFragment.goToProductFragment(storeProductPriceList);
    }

    private void resetValues() {
        searchFilter = new SearchFilter();
    }

    private void findProducts() {
        searchService.findProducts(this, searchFilter);
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
            Picasso.with(getContext()).load(searchProductData.getImageURI()).into(target);
        }

    }


    private void updateGridView(Bitmap bitmap) {

        SearchProductData searchProductData = searchProductDataList.get(productNumber);
        String text = searchProductData.getProducerName() + " " + searchProductData.getProductName() + " " +
                searchProductData.getProductDescription() + " " + searchProductData.getProductSize();
        ImageItem imageItem = new ImageItem(bitmap, text);

        imageItems.add(imageItem);

        updateGridViewOnUiThread.run();

        productNumber++;
        findNextImage();
    }

    private void setGridView(ArrayList<ImageItem> imageItems) {
        gridViewAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, imageItems);
        gridView.setAdapter(gridViewAdapter);
    }


    public void showFilterDialog() {

        final Spinner spinner_category = dialog.findViewById(R.id.spinner_category);
        final Spinner spinner_subcategory = dialog.findViewById(R.id.spinner_subcategory);
        final Spinner spinner_producer = dialog.findViewById(R.id.spinner_producer);
        final Spinner spinner_store = dialog.findViewById(R.id.spinner_store);

        Button btn_saveFilter = dialog.findViewById(R.id.btn_save_filter);

        if (spinner_category.getAdapter() == null) {
            if (categoriesList == null)
                categoriesList = new ArrayList<>(emptyList);
            updateSpinner(this, spinner_category, categoriesList);
        }
        if (spinner_subcategory.getAdapter() == null) {
            if (subcategoriesList == null)
                subcategoriesList = new ArrayList<>(emptyList);
            updateSpinner(this, spinner_subcategory, subcategoriesList);
        }
        if (spinner_producer.getAdapter() == null) {
            if (producersList == null)
                producersList = new ArrayList<>(emptyList);
            updateSpinner(this, spinner_producer, producersList);
        }
        if (spinner_store.getAdapter() == null) {
            if (storesList == null)
                storesList = new ArrayList<>(emptyList);
            updateSpinner(this, spinner_store, storesList);
        }

        spinner_category.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && spinner_category.getSelectedItem().equals(defaultString))
                    findCategories();
                return false;
            }
        });

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner_category.getSelectedItem().toString().equals(defaultString)) {
                    subcategoriesList = new ArrayList<>(emptyList);
                    updateSpinner(searchFragment, spinner_subcategory, subcategoriesList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinner_subcategory.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (spinner_category.getSelectedItem().equals(defaultString))
                        Toast.makeText(getContext(), "Kategorija mora biti odabrana", Toast.LENGTH_SHORT).show();
                    else if (spinner_subcategory.getSelectedItem().equals(defaultString)) {
                        findSubcategoriesForCategoryName(spinner_category.getSelectedItem().toString());

                    }
                }
                return false;
            }
        });

        spinner_producer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && spinner_producer.getSelectedItem().equals(defaultString))
                    findProducers();
                return false;
            }
        });

        spinner_store.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && spinner_store.getSelectedItem().equals(defaultString))
                    findStores();
                return false;
            }
        });

        btn_saveFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchFilter.setCategoryName(spinner_category.getSelectedItem().equals(defaultString) ? "" : spinner_category.getSelectedItem().toString());
                searchFilter.setSubcategoryName(spinner_subcategory.getSelectedItem().equals(defaultString) ? "" : spinner_subcategory.getSelectedItem().toString());
                searchFilter.setProducerName(spinner_producer.getSelectedItem().equals(defaultString) ? "" : spinner_producer.getSelectedItem().toString());
                searchFilter.setStoreName(spinner_store.getSelectedItem().equals(defaultString) ? "" : spinner_store.getSelectedItem().toString());

                dialog.dismiss();
            }
        });

        /*
            Set copies of final objects.
         */

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
        searchFragment.categoriesList = new ArrayList<>(searchFragment.emptyList);
        searchFragment.categoriesList.addAll(categoryList);
        SearchFragment.updateSpinner(searchFragment, searchFragment.spinner_category, searchFragment.categoriesList);
    }

    private void findSubcategoriesForCategoryName(String categoryName) {
        searchService.findSubcategoriesForCategoryName(this, categoryName);
    }

    public static void foundSubcategoriesForCategoryName(SearchFragment searchFragment, List<String> subcategoryList) {
        searchFragment.subcategoriesList = new ArrayList<>(searchFragment.emptyList);
        searchFragment.subcategoriesList.addAll(subcategoryList);
        SearchFragment.updateSpinner(searchFragment, searchFragment.spinner_subcategory, searchFragment.subcategoriesList);
    }

    private void findProducers() {
        String selectedSubcategory = spinner_subcategory.getSelectedItem().toString();
        String subcategoryName = selectedSubcategory.equals(defaultString) ? "" : selectedSubcategory;
        searchService.findProducers(this, subcategoryName);
    }

    public static void foundProducers(SearchFragment searchFragment, List<String> producerList) {
        searchFragment.producersList = new ArrayList<>(searchFragment.emptyList);
        searchFragment.producersList.addAll(producerList);
        SearchFragment.updateSpinner(searchFragment, searchFragment.spinner_producer, searchFragment.producersList);
    }

    private void findStores() {
        searchService.findStores(this);
    }

    public static void foundStores(SearchFragment searchFragment, List<String> storeList) {
        searchFragment.storesList = new ArrayList<>(searchFragment.emptyList);
        searchFragment.storesList.addAll(storeList);
        SearchFragment.updateSpinner(searchFragment, searchFragment.spinner_store, searchFragment.storesList);
    }

    private static void updateSpinner(SearchFragment searchFragment, Spinner spinner, List<String> dataList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(searchFragment.getContext(), android.R.layout.simple_spinner_dropdown_item, dataList);
        spinner.setAdapter(adapter);
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
