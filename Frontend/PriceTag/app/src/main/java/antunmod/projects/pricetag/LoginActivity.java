package antunmod.projects.pricetag;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    int REGISTER_ACTIVITY_REQUEST_CODE = 1;
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

        editText_username =  findViewById(R.id.editText_username);
        editText_password =  findViewById(R.id.editText_password);

        btn_signIn =  findViewById(R.id.btn_sign_in);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Oba polja moraju biti ispunjena!", Toast.LENGTH_SHORT).show();
                } else {
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
                startActivityForResult(createAnAccountIntent, REGISTER_ACTIVITY_REQUEST_CODE);
            }
        });
    }


    public void loginUser(String username, final String password) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<User> call = restServiceClient.loginUser(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null && user.getName() != null) {

                    Intent loginIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    loginIntent.putExtra("user", user);
                    startActivity(loginIntent);

                } else {
                    Toast.makeText(getApplicationContext(), "Korisnik s unesenim korisničkim imenom i lozinkom ne postoji!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User createdUser = (User) data.getSerializableExtra("user");
            Toast.makeText(getApplicationContext(), "Račun s korisničkim imenom " + createdUser.getName() + " je registriran!", Toast.LENGTH_SHORT).show();
        }
    }

}
