package antunmod.projects.pricetag;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_signIn;
    TextView textView_forgotYourPassword;
    TextView textView_CreateAnAccount;
    EditText editText_username;
    EditText editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);

        btn_signIn = (Button) findViewById(R.id.btn_sign_in);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();
                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Oba polja moraju biti ispunjena!", Toast.LENGTH_SHORT).show();
                }
                else {
                    loginUser(username, password);

                }
            }
        });


        textView_forgotYourPassword = findViewById(R.id.textView_forgot_your_password);
        textView_forgotYourPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotYourPasswordIntent = new Intent(getApplicationContext(), ForgotYourPasswordActivity.class);
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
    }


    public void loginUser(String username, final String password) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<User> call = restServiceClient.loginUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                String tmp = password;
                if(user.getPassword().equals(password)) {
                    Intent loginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    loginIntent.putExtra("user", user);
                    startActivity(loginIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Neispravna lozinka.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void getAllUsers() {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<List<User>> call = restServiceClient.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                String outputString = "";
                Toast.makeText(LoginActivity.this, "Uspješnost: " + (response.isSuccessful()? "+" : "-"), Toast.LENGTH_LONG).show();
                if (userList != null) {
                    for (User user : userList) {
                        outputString += user.getName() + " " + user.getEmail() + "\n";
                    }
                    Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No users", Toast.LENGTH_SHORT).show();
            }
        });

    }*/


}
