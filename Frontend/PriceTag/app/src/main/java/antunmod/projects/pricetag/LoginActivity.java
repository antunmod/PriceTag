package antunmod.projects.pricetag;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_signIn;
    TextView textView_forgotYourPassword;
    TextView textView_CreateAnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_signIn = (Button) findViewById(R.id.btn_sign_in);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeIntent);
            }
        });


        textView_forgotYourPassword = findViewById(R.id.textView_forgot_your_password);
        textView_forgotYourPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotYourPasswordIntent = new Intent (getApplicationContext(), ForgotYourPasswordActivity.class);
                startActivity(forgotYourPasswordIntent);
            }
        });

        textView_CreateAnAccount = findViewById(R.id.textView_create_an_account);
        textView_CreateAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAnAccountIntent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(createAnAccountIntent);
            }
        });

        getAllUsers();
    }


    public void getAllUsers() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<User>> call = restServiceClient.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                String outputString = "";
                for (User user : userList) {
                    outputString += user.getName() + " " + user.getEmail() + "\n";
                }
                Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No users", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
