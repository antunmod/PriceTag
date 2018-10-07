package antunmod.projects.pricetag.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.model.ProductData;
import antunmod.projects.pricetag.model.UpdateProductData;
import antunmod.projects.pricetag.service.SelectService;
import antunmod.projects.pricetag.service.UtilService;
import antunmod.projects.pricetag.transfer.ProductInformation;

import static antunmod.projects.pricetag.view.activity.HomeActivity.user;


/**
 * This fragment is used in selecting existing or adding new data to a product while adding it.
 */
public class SelectFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private SelectService selectService;

    private final String SUPERMARKETS = "Supermarketi";

    /*
        Lists containing data received from the database
     */
    private static List<String> storeList;
    private static List<String> storeAddressList;
    private static List<String> sectorList;
    private static List<String> categoryList;
    private static List<String> subcategoryList;
    private static List<String> producerList;
    private static List<String> productList;

    /*
        Names of selected Sector, Category and Subcategory.
        Will be used later for finding ID-s
     */
    private static String sectorName;
    private static String categoryName;
    private static String subcategoryName;

    /*
        The base product which will be filled with new data.
        Other data that will be sent to server is below it.
     */
    private static ProductData productData;

    private ProductInformation productInformation;
    /*
        TextView text.
     */
    private String title = "";

    /*
        errorString will contain error which will be shown to the user. Set by SelectService.
     */
    private static String errorString;

    /*
        String variables containing new List values added by the user
     */
    private String newStoreName;
    private String newStoreAddress;
    private String newSectorName;
    private String newCategoryName;
    private String newSubcategoryName;
    private String newProducerName;
    private String newProductName;

    /*
        Final variables containing different titles.
     */
    private static final String STORE = "Trgovina";
    private static final String STORE_ADDRESS = "Adresa trgovine";
    private static final String SECTOR = "Sektor";
    private static final String CATEGORY = "Kategorija";
    private static final String SUBCATEGORY = "Potkategorija";
    private static final String PRODUCER = "Proizvođač";
    private static final String PRODUCT = "Proizvod";
    private static final String SIZE = "Veličina";


    public SelectFragment() {
    }

    public static SelectFragment newInstance(String param1, String param2) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        // Set values
        if (bundle != null) {
            productData = new ProductData();
            storeList = bundle.getStringArrayList("storeList");
            productData.getBaseProduct().setBarcode(bundle.getString("barcode"));
            productData.getBaseProduct().setUserId(user.getId());
        }

    }

    private ListView listView_select;
    private TextView textView_select;
    private FloatingActionButton fab_addNew;
    private View progressBar_loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_layout_select, container, false);

        selectService = new SelectService();

        listView_select = inflatedView.findViewById(R.id.listView_select);
        textView_select = inflatedView.findViewById(R.id.textView_new_data);
        progressBar_loading = inflatedView.findViewById(R.id.progressBar_loading);

        listView_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = listView_select.getItemAtPosition(i).toString();
                findListData(selected);
            }
        });

        fab_addNew = inflatedView.findViewById(R.id.fab_add_new);
        fab_addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewDialog(generateDialogName(title));
                UtilService.showKeyboardIn(getContext());
            }
        });

        setFragment();
        findProductInformationForBarcode();
        return inflatedView;
    }

    /*
        Title, or TextView text, determines the following action to be taken.
     */
    private void setFragment() {
        switch (title) {
            case STORE_ADDRESS:
                updateFragment(STORE_ADDRESS, storeAddressList);
                break;
            case SECTOR:
                updateFragment(SECTOR, sectorList);
                break;
            case CATEGORY:
                updateFragment(CATEGORY, categoryList);
                break;
            case SUBCATEGORY:
                updateFragment(SUBCATEGORY, subcategoryList);
                break;
            case PRODUCER:
                updateFragment(PRODUCER, producerList);
                break;
            case PRODUCT:
                updateFragment(PRODUCT, productList);
                break;
            default:
                updateFragment(STORE, storeList);
        }
    }

    private List<String> getListViewItems() {
        switch (title) {
            case STORE_ADDRESS:
                return storeAddressList;
            case SECTOR:
                return sectorList;
            case CATEGORY:
                return categoryList;
            case SUBCATEGORY:
                return subcategoryList;
            case PRODUCER:
                return producerList;
            case PRODUCT:
                return productList;
            default:
                return new ArrayList<>();
        }
    }

    private String generateDialogName(String title) {

        if (title.equals(STORE) || title.equals(STORE_ADDRESS) || title.equals(CATEGORY) || title.equals(SUBCATEGORY))
            return "Nova " + title;

        if (title.equals(SECTOR) || title.equals(PRODUCT) || title.equals(PRODUCER))
            return "Novi " + title;

        else return title;
    }

    private void openAddNewDialog(final String title) {
        AlertDialog.Builder ab = new AlertDialog.Builder(getContext());

        final EditText et = new EditText(getContext());
        et.setHint(title);
        et.setGravity(Gravity.CENTER);
        et.setPadding(0, 100, 0, 20);
        ab.setView(et);

        ab.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newValue = et.getText().toString();
                if (newValue.isEmpty()) {
                    Toast.makeText(getContext(), "Unesite vrijednost", Toast.LENGTH_SHORT).show();
                } else if (getListViewItems() != null && getListViewItems().contains(newValue)) {
                    Toast.makeText(getContext(), "Unesena vrijednost već se postoji", Toast.LENGTH_SHORT).show();
                } else {
                    saveNewValue(newValue);
                    setArrayList();
                }
            }
        });

        ab.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Nije dodano", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog a = ab.create();
        a.show();
    }

    private void setArrayList() {
        switch (title) {
            case STORE_ADDRESS:
                // The barcode is new
                if (productInformation == null)
                    findCategoriesForSectorName(SUPERMARKETS);
                else
                    goToUpdateProductFragment();
                break;
            case SECTOR:
                categoryList = new ArrayList<>();
                title = CATEGORY;
                break;
            case CATEGORY:
                subcategoryList = new ArrayList<>();
                title = SUBCATEGORY;
                break;
            case SUBCATEGORY:
                producerList = new ArrayList<>();
                title = PRODUCER;
                break;
            case PRODUCER:
                productList = new ArrayList<>();
                title = PRODUCT;
                setFragment();
                break;
            case PRODUCT:
                goToAddProductFragment();
                break;
            default:
                storeAddressList = new ArrayList<>();
                title = STORE_ADDRESS;
                setFragment();
        }
    }

    private void saveNewValue(String newValue) {
        switch (title) {

            case STORE:
                newStoreName = newValue;
                productData.setStoreName(newStoreName);
                break;
            case STORE_ADDRESS:
                newStoreAddress = newValue;
                productData.setStoreAddress(newStoreAddress);
                break;
            case SECTOR:
                newSectorName = newValue;
                break;
            case CATEGORY:
                newCategoryName = newValue;
                break;
            case SUBCATEGORY:
                newSubcategoryName = newValue;
                break;
            case PRODUCER:
                newProducerName = newValue;
                productData.setProducerName(newProducerName);
                break;
            case PRODUCT:
                newProductName = newValue;
                productData.setProductName(newProductName);
                findSubcategoryIdForCategoryAndSubcategoryName();
                break;
            case SIZE:
                newStoreName = newValue;
                break;
        }
    }

    /*
        The following method finds data for the listView according to the title which is currently set.
        If the data chosen from existing, get required data. Otherwise just update View.
     */
    private void findListData(String selected) {

        title = textView_select.getText().toString();

        switch (title) {
            case STORE:
                if (selected.equals(newStoreName))
                    updateFragment(STORE_ADDRESS, storeAddressList = new ArrayList<>());
                else {
                    productData.setStoreName(selected);
                    findStoreId(selected);
                    findStoreAddresses(selected);
                }
                break;
            case STORE_ADDRESS:
                productData.setStoreAddress(selected);
                findStoreSpecificId(selected);
                break;
            case SECTOR:
                if (selected.equals(newSectorName))
                    updateFragment(CATEGORY, categoryList = new ArrayList<>());
                else
                    findCategoriesForSectorName(selected);
                break;
            case CATEGORY:
                if (selected.equals(newCategoryName))
                    updateFragment(SUBCATEGORY, subcategoryList = new ArrayList<>());
                else
                    findSubcategoriesForCategoryName(selected);
                break;
            case SUBCATEGORY:
                if (selected.equals(newSubcategoryName))
                    updateFragment(PRODUCER, producerList = new ArrayList<>());
                else
                    findProducersForSubcategoryName(selected);
                break;
            case PRODUCER:
                productData.setProducerName(selected);
                if (selected.equals(newProducerName)) {
                    if (productList != null && productList.contains(newProductName))
                        updateFragment(PRODUCT, productList);
                    else
                        updateFragment(PRODUCT, productList = new ArrayList<>());

                } else {
                    findProductsForSubcategoryAndProducerName(selected);
                }
                break;
            case PRODUCT:
                productData.setProductName(selected);
                if (selected.equals(newProductName)) {
                    findSubcategoryIdForCategoryAndSubcategoryName();
                } else {
                    findProductIdForProducerAndProductName(selected);
                }

                break;
        }


    }

    private void findStoreAddresses(String selectedStore) {
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findStoreAddresses(this, selectedStore);
    }

    public static void foundStoreAddressesStatic(SelectFragment selectFragment, List<String> storeAddresses) {
        storeAddressList = storeAddresses;
        selectFragment.foundStoreAddresses();
    }

    public void foundStoreAddresses() {
        UtilService.showProgress(false, listView_select, progressBar_loading);
        if (errorString != null) {
            Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT).show();
            errorString = null;
        } else {
            updateFragment(STORE_ADDRESS, storeAddressList);
        }
    }

    private void findProductInformationForBarcode() {
        selectService.findProductInformationForBarcode(this, productData.getBaseProduct().getBarcode());

    }

    public void foundProductInformationForBarcode(SelectFragment selectFragment, ProductInformation productInformation) {
        selectFragment.productInformation = productInformation;
    }

    private void goToUpdateProductFragment() {
        Bundle bundle = new Bundle();
        updateProductData();
        productData.setProductSpecificId(productInformation.getProductSpecificId());
        UpdateProductData updateProductData = new UpdateProductData(productData);
        bundle.putSerializable("updateProductData", updateProductData);
        bundle.putSerializable("productInformation", productInformation);
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        updateProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, updateProductFragment)
                .addToBackStack("selectFragment")
                .commit();
    }

    private void updateProductData() {
        if (productData.getStoreName() != null && !productData.getStoreName().equals(newStoreName))
            productData.setStoreName(null);
        if (productData.getStoreAddress() != null && !productData.getStoreAddress().equals(newStoreAddress))
            productData.setStoreId(null);
    }

    private void findStoreId(String storeName) {
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findStoreId(this, storeName);
    }

    public static void foundStoreId(SelectFragment selectFragment, Short storeId) {
        productData.setStoreId(storeId);
        selectFragment.closeProgressAndCheckForErrors();
    }

    private void closeProgressAndCheckForErrors() {
        UtilService.showProgress(false, listView_select, progressBar_loading);

        if (errorString != null) {
            Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT).show();
            errorString = null;
        }
    }

    private void findStoreSpecificId(String storeAddress) {

        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findStoreSpecificId(this, storeAddress);
    }

    public static void foundStoreSpecificId(SelectFragment selectFragment, Short storeSpecificId) {

        productData.setStoreSpecificId(storeSpecificId);
        selectFragment.closeProgressAndCheckForErrors();
        // If the barcode is new find categories
        if (selectFragment.productInformation == null)
            selectFragment.findCategoriesForSectorName(selectFragment.SUPERMARKETS);
        else
            selectFragment.goToUpdateProductFragment();
    }

    private void findCategoriesForSectorName(String sectorName) {
        selectService.findCategoriesForSectorName(this, sectorName);
    }

    public static void foundCategoriesForSectorName(SelectFragment selectFragment, List<String> newCategoryList) {
        categoryList = newCategoryList;
        selectFragment.closeProgressAndUpdateFragment(CATEGORY, newCategoryList);
    }

    private void findSubcategoriesForCategoryName(String categoryName) {
        SelectFragment.categoryName = categoryName;
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findSubcategoriesForCategoryName(this, categoryName);
    }

    public static void foundSubcategoriesForSectorName(SelectFragment selectFragment, List<String> newSubcategoryList) {
        subcategoryList = newSubcategoryList;
        selectFragment.closeProgressAndUpdateFragment(SUBCATEGORY, newSubcategoryList);
    }

    private void findProducersForSubcategoryName(String subcategoryName) {
        SelectFragment.subcategoryName = subcategoryName;
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findProducersForSubcategoryName(this, subcategoryName);
    }

    public static void foundProducersForSubcategoryName(SelectFragment selectFragment, List<String> newProducerList) {
        producerList = newProducerList;
        selectFragment.closeProgressAndUpdateFragment(PRODUCER, newProducerList);
    }

    private void findProductsForSubcategoryAndProducerName(String producerName) {
        productData.setProducerName(producerName);
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findProductsForSubcategoryAndProducerName(this, subcategoryName, producerName);

        UtilService.showProgress(false, listView_select, progressBar_loading);
        if (errorString != null) {
            Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT).show();
            errorString = null;
        }
    }

    public static void foundProductsForSubcategoryAndProducerName(SelectFragment selectFragment, List<String> newProductList) {
        productList = newProductList;
        selectFragment.closeProgressAndUpdateFragment(PRODUCT, newProductList);
    }

    public void closeProgressAndUpdateFragment(String title, List<String> stringList) {
        UtilService.showProgress(false, listView_select, progressBar_loading);
        updateFragment(title, stringList);

    }

    /*
        If the product being added is a new product, find subcategoryId and go to AddProductFragment.
     */
    private void findSubcategoryIdForCategoryAndSubcategoryName() {
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findSubcategoryIdForCategoryAndSubcategoryName(this, categoryName, subcategoryName);
    }

    public static void foundSubcategoryIdForCategoryAndSubcategoryName(SelectFragment selectFragment, Short subcategoryId) {
        productData.setSubcategoryId(subcategoryId);
        if (!productData.getProducerName().equals(selectFragment.newProducerName))
            selectFragment.findProducerId();
        else
            selectFragment.goToAddProductFragment();
    }

    private void findProducerId() {
        selectService.findProducerId(this, productData.getProducerName());
    }

    public static void foundProducerId(SelectFragment selectFragment, Short producerId) {
        productData.setProducerId(producerId);
        selectFragment.goToAddProductFragment();
    }

    /*
        If the product being added exists in DB, find its id.
     */
    private void findProductIdForProducerAndProductName(String productName) {
        UtilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findProductIdForProducerAndProductName(this, productData.getProducerName(), productName);
    }

    public static void foundProductIdForProducerAndProductName(SelectFragment selectFragment, Short productId) {
        productData.setProductId(productId);
        selectFragment.goToAddProductFragment();
    }

    /*
        This method changes title and enables/disables fab when neccessary.
     */
    private void updateFragment(String newTitle, List<String> stringList) {

        title = newTitle;
        if (newTitle.equals(SECTOR) || newTitle.equals(CATEGORY) || newTitle.equals(SUBCATEGORY)) {
            if (fab_addNew.getVisibility() != View.GONE)
                fab_addNew.setVisibility(View.GONE);
        } else {
            if (fab_addNew.getVisibility() != View.VISIBLE)
                fab_addNew.setVisibility(View.VISIBLE);
        }
        textView_select.setText(newTitle);
        addValuesToListView(stringList);
        UtilService.hideKeyboard(getActivity());

    }

    private void addValuesToListView(List<String> stringList) {

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                stringList
        );
        listView_select.setAdapter(listViewAdapter);
        if (stringList.isEmpty() && (title.equals(PRODUCT) || title.equals(PRODUCER) || title.equals(STORE) || title.equals(STORE_ADDRESS))) {
            fab_addNew.performClick();
        }
    }

    private void goToAddProductFragment() {
        updateProductData();
        Bundle bundle = new Bundle();
        bundle.putSerializable("productData", productData);
        AddProductFragment addProductFragment = new AddProductFragment();
        addProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, addProductFragment)
                .addToBackStack("selectFragment")
                .commit();
    }

    /*
        Called on back pressed. The previous list is loaded back in listView and TextView is set to previous value.
     */
    public boolean onBackPressed() {
        switch (title) {

            case STORE:
                return false;
            case STORE_ADDRESS:
                productData.setStoreName(null);
                updateFragment(STORE, storeList);
                break;
            case SECTOR:
                updateFragment(STORE_ADDRESS, storeAddressList);
                break;
            case CATEGORY:
                updateFragment(STORE_ADDRESS, storeAddressList);
                break;
            case SUBCATEGORY:
                updateFragment(CATEGORY, categoryList);
                break;
            case PRODUCER:
                updateFragment(SUBCATEGORY, subcategoryList);
                break;
            case PRODUCT:
                updateFragment(PRODUCER, producerList);
                break;

        }
        return true;
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

    /*
        The following are getters and setters used while doing retrofit calls
     */

    public static void setErrorString(String error) {
        errorString = error;
    }

}
