package antunmod.projects.pricetag.service;

import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.User;
import antunmod.projects.pricetag.view.activity.ForgotYourPasswordActivity;
import antunmod.projects.pricetag.view.activity.HomeActivity;
import antunmod.projects.pricetag.view.activity.LoginActivity;
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

    public void loginUser(final LoginActivity loginActivity, String username, final String password) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<User> call = restServiceClient.loginUser(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getName() != null) {
                    loginActivity.userFound(loginActivity, user);


                } else {
                    Toast.makeText(loginActivity, UtilService.LOGIN_ERROR, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                UtilService.toastServerError(loginActivity);
            }
        });
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
                    UtilService.toastError(forgotYourPasswordActivity.getApplicationContext(), UtilService.NON_EXISTING_ACCOUNT);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                UtilService.toastServerError(forgotYourPasswordActivity.getApplicationContext());
            }
        });
    }
}
