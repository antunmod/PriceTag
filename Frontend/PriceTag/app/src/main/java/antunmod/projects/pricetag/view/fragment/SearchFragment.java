package antunmod.projects.pricetag.view.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.GridViewAdapter;
import antunmod.projects.pricetag.model.ImageItem;
import antunmod.projects.pricetag.service.SearchService;
import antunmod.projects.pricetag.service.SelectService;
import antunmod.projects.pricetag.transfer.SearchFilter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    private SearchService searchService;

    private final String SUPERMARKETS = "Supermarketi";

    private Button btn_search;
    private EditText editText_productName;
    private GridView gridView;
    private FloatingActionButton fab_filter;
    private SearchFilter searchFilter;

    private GridViewAdapter gridViewAdapter;

    View inflatedView;

    CheckBox checkBox_category;
    CheckBox checkBox_subcategory;
    CheckBox checkBox_producer;
    CheckBox checkBox_store;

    Spinner spinner_category;
    Spinner spinner_subcategory;
    Spinner spinner_producer;
    Spinner spinner_store;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_search, container, false);

        btn_search = inflatedView.findViewById(R.id.btn_search);
        editText_productName = inflatedView.findViewById(R.id.editText_product_name);
        gridView = inflatedView.findViewById(R.id.gridView);
        //fab_filter = inflatedView.findViewById(R.id.fab_filter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFilter.setProductName(editText_productName.getText().toString());

            }
        });

        gridViewAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, getData());

        searchService = new SearchService();

        searchFilter = new SearchFilter();
        showFilterDialog();

        return inflatedView;
    }

    public void showFilterDialog() {
        Dialog dialog = new Dialog(getContext());
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

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        /*File dir = new File(FOLDER_LOCATION + File.separator + ".compressed");

        File[] folderList = dir.listFiles();
        if(folderList.length == 0)
            return imageItems;
        for(File folder : folderList) {
            File[] images = folder.listFiles(IMAGE_FILTER);
            if (images.length == 0)
                return imageItems;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            for(File image : images) {
                Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), options);
                String imageName = image.getName();
                imageItems.add(new ImageItem(bitmap, imageName.substring(0, imageName.length() - EXTENSION_LENGTH)));
            }
        }*/
        return imageItems;
    }



}
