package antunmod.projects.pricetag.service;

import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.view.activity.ForgotYourPasswordActivity;
import antunmod.projects.pricetag.view.fragment.AddProductFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *  This class will be used by for
 */
public class UserService {

    private RestServiceClient restServiceClient;

    public UserService() {
        restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
    }

    public void sendPasswordToEmail(final ForgotYourPasswordActivity forgotYourPasswordActivity, String username, String email) {
        Call<Boolean> call = restServiceClient.sendPasswordToEmail(username, email);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean success = response.body();
                if (success) {
                    ForgotYourPasswordActivity.sentMail(forgotYourPasswordActivity);
                } else {
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }
        });
    }
}
