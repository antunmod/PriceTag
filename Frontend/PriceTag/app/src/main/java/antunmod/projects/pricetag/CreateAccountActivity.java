package antunmod.projects.pricetag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {

    EditText editText_username;
    EditText editText_password;
    EditText editText_passwordRetype;
    EditText editText_email;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editText_username = (EditText) findViewById(R.id.editText_username);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_passwordRetype = (EditText) findViewById(R.id.editText_password_retype);
        editText_email = (EditText) findViewById(R.id.editText_email);

        btn_register = (Button) findViewById(R.id.btn_create_account);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputIsValid()) {
                    User user = new User();
                    user.setName(editText_username.getText().toString());
                    user.setPassword(editText_password.getText().toString());
                    user.setEmail(editText_email.getText().toString());
                    registerUser(user);
                }
            }
        });
    }

    private boolean inputIsValid() {
        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();
        String passwordRetype = editText_passwordRetype.getText().toString();
        String email = editText_email.getText().toString();
        if(username.isEmpty() ||password.isEmpty() || passwordRetype.isEmpty() || email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Sva polja moraju biti ispunjena!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!username.matches("[a-zA-Z0-9]*")) {
            Toast.makeText(getApplicationContext(), "Korisničko ime smije sadržavati samo slova i brojeve!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!email.contains("@") || sqlInjectionTest(email)) {
            Toast.makeText(getApplicationContext(), "Neispravan email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (sqlInjectionTest(password)) {
            Toast.makeText(getApplicationContext(), "Lozinka sadrži neispravne znakove!", Toast.LENGTH_SHORT).show();
        }
        else if (!password.equals(passwordRetype)) {
            Toast.makeText(getApplicationContext(), "Lozinka i ponovljena lozinka nisu jednake!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void registerUser(User user) {
        RestServiceClient restServiceClient = RestServiceClient.retrofit.create(RestServiceClient.class);
        Call<User> call = restServiceClient.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user.getName() != null) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("user", user);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Korisnik s unesenim korisničkim imenom već postoji!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Došlo je do greške. Pokušajte ponovo.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private boolean sqlInjectionTest(String string) {
        return string.contains(";") || string.contains("\"") || string.contains(")");
    }
}
