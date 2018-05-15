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
import antunmod.projects.pricetag.service.SelectService;
import antunmod.projects.pricetag.service.UtilService;
import antunmod.projects.pricetag.view.activity.HomeActivity;


/**
 * This fragment is used in selecting existing or adding new data to a product while adding it.
 */
public class SelectFragment extends Fragment {

    private final Integer NOT_FOUND_INTEGER = -1;

    private OnFragmentInteractionListener mListener;

    private SelectService selectService;
    private UtilService utilService;

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
    private static List<String> sizeList;

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
        }

    }

    private View inflatedView;
    private ListView listView_select;
    private TextView textView_select;
    private FloatingActionButton fab_select;
    private View progressBar_loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_layout_select, container, false);

        selectService = new SelectService();
        utilService = new UtilService();

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

        fab_select = inflatedView.findViewById(R.id.fab_select);
        fab_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewDialog(generateDialogName(title));
            }
        });

        setFragment();

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
                } else
                    saveNewValue(newValue.toUpperCase());

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

    private void saveNewValue(String newValue) {
        switch (title) {

            case STORE:
                newStoreName = newValue;
                break;
            case STORE_ADDRESS:
                newStoreAddress = newValue;
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
                break;
            case PRODUCT:
                newProductName = newValue;
                break;
            case SIZE:
                newStoreName = newValue;
                break;
        }
    }

    private void updateListView() {

        ArrayAdapter<String> listViewAdapter = (ArrayAdapter<String>) listView_select.getAdapter();
        listViewAdapter.notifyDataSetChanged();

    }

    /*
        The following method finds data for the listView according to the title which is currently set.
        If the data chosen from existing, get required data. Otherwise just update View.
     */
    private void findListData(String selected) {

        title = textView_select.getText().toString();

        switch (title) {
            case STORE:
                productData.setStoreName(selected);
                if (newStoreName != null && selected.equals(newStoreName))
                    updateFragment(STORE_ADDRESS, storeAddressList = new ArrayList<>());
                else
                    findStoreId(selected);
                    findStoreAddresses(selected);
                break;
            case STORE_ADDRESS:
                productData.setStoreAddress(selected);
                findStoreSpecificId(selected);
                //findProductForBarcodeAndStoreAddress(selected);
                break;
            case SECTOR:
                if (newSectorName != null && selected.equals(newSectorName))
                    updateFragment(CATEGORY, categoryList = new ArrayList<>());
                else
                    findCategoriesForSectorName(selected);
                break;
            case CATEGORY:
                if (newCategoryName != null && selected.equals(newCategoryName))
                    updateFragment(SUBCATEGORY, subcategoryList = new ArrayList<>());
                else
                    findSubcategoriesForCategoryName(selected);
                break;
            case SUBCATEGORY:
                if (newSubcategoryName != null && selected.equals(newSubcategoryName))
                    updateFragment(PRODUCER, producerList = new ArrayList<>());
                else
                    findProducersForSubcategoryName(selected);
                break;
            case PRODUCER:
                productData.setProducerName(selected);
                if (newProducerName != null && selected.equals(newProducerName)) {
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
                if (newProductName != null && selected.equals(newProductName)) {
                    findSubcategoryIdForCategoryAndSubcategoryName();
                } else {
                    findProductIdForProducerAndProductName(selected);
                }

                break;
        }


    }

    private void findStoreAddresses(String selectedStore) {
        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findStoreAddresses(this, selectedStore);
    }

    public static void foundStoreAddressesStatic(SelectFragment selectFragment, List<String> storeAddresses) {
        storeAddressList = storeAddresses;
        selectFragment.foundStoreAddresses();
    }

    public void foundStoreAddresses() {
        utilService.showProgress(false, listView_select, progressBar_loading);
        if (errorString != null) {
            Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT);
            errorString = null;
        } else {
            updateFragment(STORE_ADDRESS, storeAddressList);
        }
    }

    /*
    private void findProductForBarcodeAndStoreAddress(final String storeAddress) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<UpdateProduct> call = restServiceClient.getUpdateProductForBarcodeAndStoreAddress(productStore.getBarcode(), storeAddress);
        call.enqueue(new Callback<UpdateProduct>() {
            @Override
            public void onResponse(Call<UpdateProduct> call, Response<UpdateProduct> response) {
                UpdateProduct updateProduct = response.body();
                if (updateProduct != null && updateProduct.getName() != null) {
                    SelectFragment.this.updateProduct = updateProduct;
                    getPhotoByteArray();

                } else {
                    findSectors(storeAddress);
                }
            }

            @Override
            public void onFailure(Call<UpdateProduct> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }*/

/*
    private void getPhotoByteArray() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Byte[]> call = restServiceClient.getPhotoByteArray(updateProduct.getPhotoId());
        call.enqueue(new Callback<Byte[]>() {
            @Override
            public void onResponse(Call<Byte[]> call, Response<Byte[]> response) {
                Byte[] photoByteArray = response.body();
                if (photoByteArray != null && photoByteArray.length > 0) {
                    goToUpdateProductFragment(photoByteArray);

                } else {
                    Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Byte[]> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

/*
    private void goToUpdateProductFragment(Byte[] photoByteArray) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("photoByteArray", photoByteArray);
        bundle.putSerializable("updateProduct", updateProduct);
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        updateProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, updateProductFragment)
                .addToBackStack("selectFragment")
                .commit();
    }*/

    private void findStoreId(String storeName) {
        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findStoreId(this, storeName);
    }

    public static void foundStoreId(SelectFragment selectFragment, Short storeId) {
        productData.setStoreId(storeId);
        selectFragment.closeProgressAndCheckForErrors();
    }

    private void closeProgressAndCheckForErrors() {
        utilService.showProgress(false, listView_select, progressBar_loading);

        if (errorString != null) {
            Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT);
            errorString = null;
        }
    }

    private void findStoreSpecificId(String storeAddress) {

        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findStoreSpecificId(this, storeAddress);
    }

    public static void foundStoreSpecificIdStatic(SelectFragment selectFragment, Short storeSpecificId) {

        productData.setStoreSpecificId(storeSpecificId);
        selectFragment.closeProgressAndCheckForErrors();
        selectFragment.findCategoriesForSectorName(selectFragment.SUPERMARKETS);
    }

    private void findCategoriesForSectorName(String sectorName) {
        selectService.findCategoriesForSectorName(this, sectorName);
    }

    public static void foundCategoriesForSectorNameStatic(SelectFragment selectFragment, List<String> newCategoryList) {
        categoryList = newCategoryList;
        selectFragment.closeProgressAndUpdateFragment(CATEGORY, newCategoryList);
    }

    private void findSubcategoriesForCategoryName(String categoryName) {
        this.categoryName = categoryName;
        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findSubcategoriesForCategoryName(this, categoryName);
    }

    public static void foundSubcategoriesForSectorName(SelectFragment selectFragment, List<String> newSubcategoryList) {
        subcategoryList = newSubcategoryList;
        selectFragment.closeProgressAndUpdateFragment(SUBCATEGORY, newSubcategoryList);
    }

    private void findProducersForSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findProducersForSubcategoryName(this, subcategoryName);
    }

    public static void foundProducersForSubcategoryName(SelectFragment selectFragment, List<String> newProducerList) {
        producerList = newProducerList;
        selectFragment.closeProgressAndUpdateFragment(PRODUCER, newProducerList);
    }

    private void findProductsForSubcategoryAndProducerName(String producerName) {
        productData.setProducerName(producerName);
        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findProductsForSubcategoryAndProducerName(this, subcategoryName, producerName);

        utilService.showProgress(false, listView_select, progressBar_loading);
        if (errorString != null) {
            Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT);
            errorString = null;
        } else {
        }
    }

    public static void foundProductsForSubcategoryAndProducerName(SelectFragment selectFragment, List<String> newProducerList) {
        producerList = newProducerList;
        selectFragment.closeProgressAndUpdateFragment(PRODUCT, newProducerList);
    }

    public void closeProgressAndUpdateFragment(String title, List<String> stringList) {
        utilService.showProgress(false, listView_select, progressBar_loading);
        updateFragment(title, stringList);

    }

    /*
        If the product being added is a new product, find subcategoryId and go to AddProductFragment.
     */
    private void findSubcategoryIdForCategoryAndSubcategoryName() {
        utilService.showProgress(true, listView_select, progressBar_loading);
        selectService.findSubcategoryIdForCategoryAndSubcategoryName(this, categoryName, subcategoryName);
    }

    public static void foundSubcategoryIdForCategoryAndSubcategoryName(SelectFragment selectFragment, Short subcategoryId) {
        productData.setSubcategoryId(subcategoryId);
        selectFragment.goToAddProductFragment();
    }
    
    /*
        If the product being added exists in DB, find its id.
     */
    private void findProductIdForProducerAndProductName(String productName) {
        utilService.showProgress(true, listView_select, progressBar_loading);
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

        //if (title.equals(STORE_ADDRESS) && newTitle.equals(SECTOR))
            title = newTitle;

        textView_select.setText(newTitle);
        addValuesToListView(stringList);
    }

    private void addValuesToListView(List<String> stringList) {

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                stringList
        );
        listView_select.setAdapter(listViewAdapter);
        if (stringList.isEmpty()) {
            fab_select.performClick();
        }
    }

/*
    private void showPhotoAndPriceFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateProduct", updateProduct);
        PhotoAndPriceFragment photoAndPriceFragment = new PhotoAndPriceFragment();
        photoAndPriceFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, photoAndPriceFragment)
                .addToBackStack("selectProduct")
                .commit();
    }*/


    private void goToAddProductFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("productData", productData);
        AddProductFragment addProductFragment = new AddProductFragment();
        addProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, addProductFragment)
                .addToBackStack("selectProduct")
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

    private void goToEnterNewDataFragment(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        EnterNewDataFragment enterNewDataFragment = new EnterNewDataFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, enterNewDataFragment)
                .addToBackStack("selectProduct")
                .commit();

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

    public static void setProductId(Short productId) {
        productData.setProductId(productId);
    }

    public static void setStoreId(Short storeId) {
        productData.setStoreId(storeId);
    }

    public static void setSubcategoryId(Short subcategoryId) {
        productData.setSubcategoryId(subcategoryId);
    }

    public static void setStoreList(List<String> newStoreList) {
        storeList = newStoreList;
    }

    public static void setStoreAddressList(List<String> newStoreAddressList) {
        storeAddressList = newStoreAddressList;
    }

    public static void setSectorList(List<String> newSectorList) {
        sectorList = newSectorList;
    }

    public static void setCategoryList(List<String> newCategoryList) {
        categoryList = newCategoryList;
    }

    public static void setSubcategoryList(List<String> newSubcategoryList) {
        subcategoryList = newSubcategoryList;
    }

    public static void setProducerList(List<String> newProducerList) {
        producerList = newProducerList;
    }

    public static void setProductList(List<String> newProductList) {
        productList = newProductList;
    }

    public static void setSizeList(List<String> newSizeList) {sizeList = newSizeList;}


    public static void setErrorString(String error) {
        errorString = error;
    }

}
