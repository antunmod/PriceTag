package antunmod.projects.pricetag.service;

import java.util.ArrayList;
import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.transfer.UserInformation;
import antunmod.projects.pricetag.view.activity.HomeActivity;
import antunmod.projects.pricetag.view.fragment.SelectFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antun on 5/29/2018.
 */

public class HomeService {

    private RestServiceClient restServiceClient;

    public HomeService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void findUserInformation(final HomeActivity homeActivity, Short id) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<UserInformation> call = restServiceClient.getUserInformation(HomeActivity.user.getId());
        call.enqueue(new Callback<UserInformation>() {
            @Override
            public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                UserInformation userInformation = response.body();
                if (userInformation != null) {
                    homeActivity.foundUserInformation(userInformation);
                }
            }

            @Override
            public void onFailure(Call<UserInformation> call, Throwable t) {
            }
        });
    }


}
