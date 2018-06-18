package antunmod.projects.pricetag.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import antunmod.projects.pricetag.R;
import antunmod.projects.pricetag.service.UserService;
import antunmod.projects.pricetag.service.UtilService;

import static antunmod.projects.pricetag.service.UtilService.sqlInjectionTest;

public class ForgotYourPasswordActivity extends AppCompatActivity {

    EditText editText_username;
    EditText editText_email;
    Button btn_sendPassword;

    ForgotYourPasswordActivity forgotYourPasswordActivity;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_your_password);

        userService = new UserService();

        editText_username = findViewById(R.id.editText_username);
        editText_email = findViewById(R.id.editText_email);
        btn_sendPassword = findViewById(R.id.btn_send_password);

        forgotYourPasswordActivity = this;

        btn_sendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editText_username.getText().toString();
                String email = editText_email.getText().toString();
                if (username.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Potrebno je unijeti i korisničko ime i adresu e-pošte", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                if (sqlInjectionTest(username) && sqlInjectionTest(email)) {
                    Toast.makeText(ForgotYourPasswordActivity.this, "Znakovi ; \" ) nisu dozvoljeni", Toast.LENGTH_SHORT).show();
                    return;
                }

                userService.sendPasswordToEmail(forgotYourPasswordActivity, username, email);
            }
        });
    }

    public static void sentMail(ForgotYourPasswordActivity forgotYourPasswordActivity) {
        Toast.makeText(forgotYourPasswordActivity.getApplicationContext(), "Lozinka je poslana", Toast.LENGTH_SHORT).show();
    }


}
