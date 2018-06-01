package antunmod.projects.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.transfer.InformationFeedback;
import antunmod.projects.pricetag.view.fragment.ProductFragment;
import antunmod.projects.pricetag.view.fragment.SearchFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
    A service for handling InformationFeedback.
 */
public class ProductService {

    private RestServiceClient restServiceClient;

    public ProductService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void getInformationFeedbackForUserAndPriceId(final ProductFragment productFragment, Short userId, Integer priceId) {
        Call<InformationFeedback> call = restServiceClient.getInformationFeedbackForUserAndPriceId(userId, priceId);
        call.enqueue(new Callback<InformationFeedback>() {
            @Override
            public void onResponse(Call<InformationFeedback> call, Response<InformationFeedback> response) {
                InformationFeedback informationFeedback = (InformationFeedback) response.body();
                ProductFragment.foundInformationFeedback(productFragment, informationFeedback);
                }

            @Override
            public void onFailure(Call<InformationFeedback> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

    public void saveInformationFeedback(final ProductFragment productFragment, InformationFeedback informationFeedback) {
        Call<Boolean> call = restServiceClient.saveInformationFeedback(informationFeedback);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success != null) {
                    ProductFragment.savedInformationFeedback(productFragment, success);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                //SearchFragment.setErrorString(ERROR_STRING);
            }
        });
    }

}
