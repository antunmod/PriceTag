package antunmod.projects.pricetag;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectFragment extends Fragment {

    private final Integer NOT_FOUND_INTEGER = -1;

    private OnFragmentInteractionListener mListener;

    private List<String> dataList;

    private List<String> storeList;
    private List<String> storeAddressList;
    private List<String> sectorList;
    private List<String> categoryList;
    private List<String> subcategoryList;
    private List<String> producerList;
    private List<String> productList;
    private List<String> sizeList;

    private Product product = new Product();
    private UpdateProduct updateProduct = new UpdateProduct();
    private ProductStore productStore = new ProductStore();

    String subcategoryName;
    private String title = "";
    private String newStoreName;
    private String newStoreAddress;
    private String newSectorName;
    private String newCategoryName;
    private String newSubcategoryName;
    private String newProducerName;
    private String newProductName;

    private String storeName;

    private final String STORE = "Trgovina";
    private final String STORE_ADDRESS = "Adresa trgovine";
    private final String SECTOR = "Sektor";
    private final String CATEGORY = "Kategorija";
    private final String SUBCATEGORY = "Potkategorija";
    private final String PRODUCER = "Proizvođač";
    private final String PRODUCT = "Proizvod";
    private final String SIZE = "Veličina";



    public SelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectFragment.
     */
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
            dataList =  bundle.getStringArrayList("storeList");
            saveStoreNames(dataList);
            productStore.setBarcode(bundle.getString("barcode"));
        }


    }

    private View inflatedView;
    private ListView listView_select;
    private TextView textView_select;
    private FloatingActionButton fab_select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflatedView = inflater.inflate(R.layout.fragment_layout_select, container, false);
        
        listView_select = inflatedView.findViewById(R.id.listView_select);
        textView_select = inflatedView.findViewById(R.id.textView_new_data);
        
        fab_select = inflatedView.findViewById(R.id.fab_select);
        
        listView_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = listView_select.getItemAtPosition(i).toString();
                findListData(selected);
            }
        });

        fab_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddNewDialog(generateDialogName(title));
            }
        });

        setFragment();

        return inflatedView;
    }

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

        if(title.equals(STORE) || title.equals(STORE_ADDRESS) || title.equals(CATEGORY) || title.equals(SUBCATEGORY))
            return "Nova " + title;

        if(title.equals(SECTOR) || title.equals(PRODUCT) || title.equals(PRODUCER))
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
                if(newValue.isEmpty()) {
                    Toast.makeText(getContext(), "Unesite vrijednost", Toast.LENGTH_SHORT).show();
                }
                else
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

        String oldValue = "";

        switch(title) {

            case STORE:
                if(newStoreAddress!=null && storeList.contains(newStoreAddress)) {
                oldValue = newStoreName;
                storeList.remove(oldValue);
            }
                newStoreName = newValue;
                storeList.add(newValue);
                break;
            case STORE_ADDRESS:
                if(newStoreAddress!=null && storeAddressList.contains(newStoreAddress)) {
                    oldValue = newStoreAddress;
                    storeAddressList.remove(oldValue);
                }
                newStoreAddress = newValue;
                storeAddressList.add(newValue);
                break;
            case SECTOR:
                if(newSectorName!=null && sectorList.contains(newSectorName)) {
                    oldValue = newSectorName;
                    sectorList.remove(oldValue);
                }
                newSectorName = newValue;
                sectorList.add(newValue);
                break;
            case CATEGORY:
                if(newCategoryName!=null && categoryList.contains(newCategoryName)) {
                    oldValue = newCategoryName;
                    categoryList.remove(oldValue);
                }
                newCategoryName = newValue;
                categoryList.add(newValue);
                break;
            case SUBCATEGORY:
                if(newSubcategoryName!=null && subcategoryList.contains(newSubcategoryName)) {
                    oldValue = newSubcategoryName;
                    subcategoryList.remove(oldValue);
                }
                newSubcategoryName = newValue;
                subcategoryList.add(newValue);
                break;
            case PRODUCER:
                if(newProducerName!=null && producerList.contains(newProducerName)) {
                    oldValue = newProducerName;
                    producerList.remove(oldValue);
                }
                newProducerName = newValue;
                producerList.add(newValue);
                break;
            case PRODUCT:
                if(newProductName!=null && productList.contains(newProductName)) {
                    oldValue = newProductName;
                    productList.remove(oldValue);
                }
                newProductName = newValue;
                productList.add(newValue);
                break;
            case SIZE:
                if(newCategoryName!=null) {
                    oldValue = newCategoryName;
                    categoryList.remove(oldValue);
                }
                newStoreName = newValue;
                break;

        }
        if(!oldValue.isEmpty())
            Toast.makeText(getContext(), "Prethodno dodana vrijednost " + oldValue + " je zamijenjena sa " + newValue, Toast.LENGTH_SHORT).show();


        updateListView();

    }

    private void updateListView() {

        ArrayAdapter<String> listViewAdapter = (ArrayAdapter<String>) listView_select.getAdapter();
        listViewAdapter.notifyDataSetChanged();

    }

    /*
    The following method finds data for the listView according to the title which is currently set.
     */
    
    private void findListData(String selected) {
        
        title = textView_select.getText().toString();
        
        switch (title) {
            case STORE:
            if(newStoreName!=null && selected.equals(newStoreName))
                updateFragment(STORE_ADDRESS, storeAddressList = new ArrayList<>());
            else
                findStoreAddresses(selected);
                break;
            case STORE_ADDRESS:
                findProductForBarcodeAndStoreAddress(selected);
                break;
            case SECTOR:
                if(newSectorName!=null && selected.equals(newSectorName))
                    updateFragment(CATEGORY, categoryList = new ArrayList<>());
                else
                    findCategoriesForSectorName(selected);
                break;
            case CATEGORY:
                if(newCategoryName!=null && selected.equals(newCategoryName))
                    updateFragment(SUBCATEGORY, subcategoryList = new ArrayList<>());
                else
                    findSubcategoriesForCategoryName(selected);
                break;
            case SUBCATEGORY:
                if(newSubcategoryName!=null && selected.equals(newSubcategoryName))
                    updateFragment(PRODUCER, producerList = new ArrayList<>());
                else
                    findProducersForSubcategoryName(selected);
                break;
            case PRODUCER:
                if(newProducerName!=null && selected.equals(newProducerName)) {
                    product.setProducer(selected);
                    updateFragment(PRODUCT, productList == null ? productList = new ArrayList<>() : productList);

                }
                else {
                    product.setProducer("");
                    findProductsForSubcategoryNameAndProducer(selected);
                }
                break;
            case PRODUCT:
                if(newProductName!=null && selected.equals(newProductName)) {
                    product.setProductName(selected);
                    goToAddProductFragment();
                }
                else {
                    product.setProductName("");
                    findProductIdForProducerAndProductName(selected);
                }

                break;
            case SIZE:

                findPhotoForProductIdAndSize(selected);
                //showPhotoAndPriceFragment();
                break;
        }
        
        
    }

    private void findStoreAddresses(String selectedStore) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getStoreLocations(selectedStore);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> storeAddresses = (ArrayList<String>) response.body();
                if (storeAddresses != null && storeAddresses.size()>0) {
                    saveStoreAddresses(storeAddresses);
                    updateFragment(STORE_ADDRESS, storeAddressList);
                } else {
                    Toast.makeText(getContext(), "Nešto je pošlo po krivu. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void findProductForBarcodeAndStoreAddress(final String storeAddress) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<UpdateProduct> call = restServiceClient.getUpdateProductForBarcodeAndStoreAddress(productStore.getBarcode(), storeAddress);
        call.enqueue(new Callback<UpdateProduct>() {
            @Override
            public void onResponse(Call<UpdateProduct> call, Response<UpdateProduct> response) {
                UpdateProduct updateProduct = response.body();
                if (updateProduct != null && updateProduct.getName()!= null) {
                    goToUpdateProductFragment(updateProduct);

                } else {
                    findSectors(storeAddress);
                }
            }

            @Override
            public void onFailure(Call<UpdateProduct> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToUpdateProductFragment(UpdateProduct updateProduct) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("updateProduct", updateProduct);
        UpdateProductFragment updateProductFragment = new UpdateProductFragment();
        updateProductFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_for_fragment, updateProductFragment)
                .addToBackStack("selectFragment")
                .commit();
    }

    private void findSectors(final String storeAddress) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getAllSectorNames();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> sectorList = response.body();

                if(sectorList!=null) {
                    saveSectorNames(sectorList);
                    findStoreId(storeAddress);
                }
                else {
                    Toast.makeText(getContext(), "Neuspjelo dohvaćanje sektora. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findStoreId (String storeAddress) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Integer> call = restServiceClient.getStoreIdForAddress(storeAddress);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer storeId = response.body();

                if(storeId!=null) {
                    saveStoreIdToProductStore(storeId);
                    updateFragment(SECTOR, sectorList);
                }
                else {
                    Toast.makeText(getContext(), "Neuspjelo dohvaćanje identifikatora dućana. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void findCategoriesForSectorName(String sectorName) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getCategoriesForSectorName(sectorName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> categoriesList = (ArrayList) response.body();
                if (categoriesList != null) {
                    saveCategoryNames(categoriesList);
                    updateFragment(CATEGORY, categoryList);

                } else {
                    Toast.makeText(getContext(), "Ne postoje kategorije za odabrani sektor.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findSubcategoriesForCategoryName(String categoryName) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getSubcategoriesForCategoryName(categoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> subcategoriesList = (ArrayList) response.body();
                if (subcategoriesList != null) {
                    saveSubcategoryNames(subcategoriesList);
                    updateFragment(SUBCATEGORY, subcategoryList);
                } else {
                    Toast.makeText(getContext(), "Ne postoje kategorije za odabrani sektor.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findProducersForSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getProducersForSubcategoryName(subcategoryName);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> producersList = (ArrayList) response.body();
                if (producersList != null)
                    saveProducerNames(producersList);

                updateFragment(PRODUCER, producersList == null? new ArrayList<String>() : producersList);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findProductsForSubcategoryNameAndProducer(String producer) {
        product.setProducer(producer);
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getProductNamesForSubcategoryNameAndProducer(subcategoryName, producer);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                ArrayList<String> productsList = (ArrayList) response.body();
                if (productsList != null)
                    saveProductNames(productsList);

                updateFragment(PRODUCT, productsList == null? new ArrayList<String>() : productsList);

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findProductIdForProducerAndProductName(String productName) {

        product.setProductName(productName);
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<Integer> call = restServiceClient.getProductIdForProducerAndProductName(product.getProducer(), productName);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer productId = response.body();
                if (productId != NOT_FOUND_INTEGER) {
                    product.setProductId(productId);
                    productStore.setProductId(productId);
                    findSizeValuesForProductId();
                }
                //else
                    //updateFragment(SIZE, new String[0]);

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void findSizeValuesForProductId() {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<String>> call = restServiceClient.getSizeValuesForProductId(product.getProductId());
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> sizeList = (ArrayList) response.body();
                if (sizeList != null) {
                    saveSizeValues(sizeList);
                }
                updateFragment(SIZE, sizeList!=null? sizeList : new ArrayList<String>());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void findPhotoForProductIdAndSize (String size) {

        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<String> call = restServiceClient.getPhotoForProductIdAndSize(product.getProductId(), size);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String photo = response.body();
                if (photo != null) {
                   // productStore.setPhoto(photo);
                    showPhotoAndPriceFragment();
                }
                else {
                    showPhotoAndPriceFragment();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void saveStoreNames(List<String> storeNames) {
        storeList = storeNames;
    }

    private void saveStoreAddresses(ArrayList<String> storeAddresses) {
        storeAddressList = storeAddresses;
    }

    private void saveSectorNames(List<String> sectorNames) {
        sectorList = sectorNames;
    }

    private void saveStoreIdToProductStore(int storeId) {
        productStore.setStoreId(storeId);
    }

    private void saveCategoryNames(List<String> categoryNames) {
        categoryList = categoryNames;
    }

    private void saveSubcategoryNames(List<String> subcategoryNames) {
        subcategoryList = subcategoryNames;
    }

    private void saveProducerNames (List<String> producerList) {
        this.producerList = producerList;
    }

    private void saveProductNames (List<String> productList) {
        this.productList = productList;
    }

    private void saveSizeValues (List<String> sizeList) {
        this.sizeList = sizeList;
    }

    private void updateFragment(String newTitle, List<String> stringList) {

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
        if(stringList.isEmpty()) {
            fab_select.performClick();
        }
    }

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
    }


    private void goToAddProductFragment() {
        Bundle bundle = new Bundle();

        if(!product.getProducer().isEmpty())
            bundle.putSerializable("product", product);
        bundle.putSerializable("productStore", productStore);
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
        switch(title) {

           case STORE: return false;
            case STORE_ADDRESS: updateFragment(STORE, storeList);
                break;
            case SECTOR:  updateFragment(STORE_ADDRESS, storeAddressList);
                break;
            case CATEGORY:  updateFragment(SECTOR, sectorList);
                break;
            case SUBCATEGORY:  updateFragment(CATEGORY, categoryList);
                break;
            case PRODUCER:  updateFragment(SUBCATEGORY, subcategoryList);
                break;
            case PRODUCT:  updateFragment(PRODUCER, producerList);
                break;
            case SIZE:  updateFragment(PRODUCT, productList);
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
}
