package antunmod.projects.pricetag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<String> dataList;

    private String[] storeStringArray;
    private String[] storeAddressStringArray;
    private String[] sectorStringArray;
    private String[] categoryStringArray;
    private String[] subcategoryStringArray;
    private String[] producerStringArray;
    private String[] productStringArray;
    private String[] sizeStringArray;

    private Product product = new Product();
    private UpdateProduct updateProduct = new UpdateProduct();
    private ProductStore productStore = new ProductStore();

    private String barcode;
    String subcategoryName;
    private String title;
    
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
    // TODO: Rename and change types and number of parameters
    public static SelectFragment newInstance(String param1, String param2) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();

        // Set values
        if (bundle != null) {
            dataList =  bundle.getStringArrayList("storeList");
            saveStoreNames(dataList);
            barcode = bundle.getString("barcode");
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
        textView_select = inflatedView.findViewById(R.id.textView_select);
        
        fab_select = inflatedView.findViewById(R.id.fab_select);
        
        listView_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = listView_select.getItemAtPosition(i).toString();
                findListData(selected);
            }
        });

        updateFragment(STORE, storeStringArray);

        return inflatedView;
    }

    /*
    The following method finds data for the listView according to the title which is currently set.
     */
    
    private void findListData(String selected) {
        
        title = textView_select.getText().toString();
        
        switch (title) {
            case STORE: findStoreAddresses(selected);
                break;
            case STORE_ADDRESS: findProductForBarcodeAndStoreAddress(selected);
                break;
            case SECTOR: findCategoriesForSectorName(selected);
                break;
            case CATEGORY: findSubcategoriesForCategoryName(selected);
                break;
            case SUBCATEGORY: findProducersForSubcategoryName(selected);
                break;
            case PRODUCER: findProductsForSubcategoryNameAndProducer(selected);
                break;
            case PRODUCT:
                break;
            case SIZE:
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
                    updateFragment(STORE_ADDRESS, storeAddressStringArray);
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
        Call<UpdateProduct> call = restServiceClient.getUpdateProductForBarcodeAndStoreAddress(barcode, storeAddress);
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
                    updateFragment(SECTOR, sectorStringArray);
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
                    updateFragment(CATEGORY, categoryStringArray);

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
                    updateFragment(SUBCATEGORY, subcategoryStringArray);
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
                ArrayList<String> producerList = (ArrayList) response.body();
                if (producerList != null) {
                    saveProducerNames(producerList);
                    updateFragment(PRODUCER, producerStringArray);

                } else {
                    Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
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
                ArrayList<String> productList = (ArrayList) response.body();
                if (productList != null) {
                    saveProductNames(productList);
                    updateFragment(PRODUCT, productStringArray);

                } else {
                    Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(getContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void saveStoreNames(List<String> storeNames) {
        storeStringArray = new String[storeNames.size()];
        for(int i = 0; i < storeNames.size(); ++i) {

            storeStringArray[i] = storeNames.get(i);
        }
    }

    private void saveStoreAddresses(ArrayList<String> storeAddresses) {
        storeAddressStringArray = new String[storeAddresses.size()];
        for(int i = 0; i < storeAddresses.size(); ++i) {
            storeAddressStringArray[i] = storeAddresses.get(i);
        }
    }

    private void saveSectorNames(List<String> sectorNames) {
        sectorStringArray = new String[sectorNames.size()];
        for(int i = 0; i < sectorNames.size(); ++i) {

            sectorStringArray[i] = sectorNames.get(i);
        }
    }

    private void saveStoreIdToProductStore(int storeId) {
        productStore.setStoreId(storeId);
    }

    private void saveCategoryNames(List<String> categoryNames) {
        categoryStringArray = new String[categoryNames.size()];
        for(int i = 0; i < categoryNames.size(); ++i) {

            categoryStringArray[i] = categoryNames.get(i);
        }
    }

    private void saveSubcategoryNames(List<String> subcategoryNames) {
        subcategoryStringArray = new String[subcategoryNames.size()];
        for(int i = 0; i < subcategoryNames.size(); ++i) {

            subcategoryStringArray[i] = subcategoryNames.get(i);
        }
    }

    private void saveProducerNames (List<String> producerList) {
        producerStringArray = new String[producerList.size()];
        for(int i = 0; i < producerList.size(); ++i) {

            producerStringArray[i] = producerList.get(i);
        }
    }

    private void saveProductNames (List<String> productList) {
        productStringArray = new String[productList.size()];
        for(int i = 0; i < productList.size(); ++i) {

            productStringArray[i] = productList.get(i);
        }
    }

    private void updateFragment(String newTitle, String[] stringArray) {

        title = newTitle;
        textView_select.setText(newTitle);
        addValuesToListView(stringArray);
        
    }

    private void addValuesToListView(String[] dataStringArray) {

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                dataStringArray
        );
        listView_select.setAdapter(listViewAdapter);

    }

    /*
    Called on back pressed. The previous list is loaded back in listView and TextView is set to previous value.
     */
    public boolean onBackPressed() {
        switch(title) {

            case STORE: return false;
            case STORE_ADDRESS: updateFragment(STORE, storeStringArray);
                break;
            case SECTOR:  updateFragment(STORE_ADDRESS, storeAddressStringArray);
                break;
            case CATEGORY:  updateFragment(SECTOR, sectorStringArray);
                break;
            case SUBCATEGORY:  updateFragment(CATEGORY, categoryStringArray);
                break;
            case PRODUCER:  updateFragment(SUBCATEGORY, subcategoryStringArray);
                break;
            case PRODUCT:  updateFragment(PRODUCER, producerStringArray);
                break;
            case SIZE:  updateFragment(PRODUCT, productStringArray);
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
}
