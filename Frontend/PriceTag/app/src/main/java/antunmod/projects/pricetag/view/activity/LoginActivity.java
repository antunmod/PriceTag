package antunmod.projects.pricetag.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.RestServiceClient;
import antunmod.projects.pricetag.model.User;
import antunmod.projects.pricetag.service.UserService;
import antunmod.projects.pricetag.service.UtilService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Integer REGISTER_ACTIVITY_REQUEST_CODE = 1;
    Button btn_signIn;
    TextView textView_forgotYourPassword;
    TextView textView_CreateAnAccount;
    EditText editText_username;
    EditText editText_password;
    static LoginActivity loginActivity;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginActivity = this;
        userService = new UserService();

        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);

        btn_signIn = findViewById(R.id.btn_sign_in);
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
                Intent createAccountIntent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivityForResult(createAccountIntent, REGISTER_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REGISTER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User createdUser = (User) data.getSerializableExtra("user");
            Toast.makeText(getApplicationContext(), "Račun s korisničkim imenom " + createdUser.getName() + " je registriran!", Toast.LENGTH_SHORT).show();
        }
    }

    public void loginUser(String username, final String password) {
        userService.loginUser(this, username, password);
    }

    public static void userFound(LoginActivity loginActivity, User user) {
        loginActivity.goToHomeActivity(user);
    }

    private void goToHomeActivity(User user) {
        Intent loginIntent = new Intent(this, HomeActivity.class);
        loginIntent.putExtra("user", user);
        startActivity(loginIntent);
    }

    public static void serverInHibernation() {
        UtilService.toastServerError(LoginActivity.loginActivity);
    }

}
