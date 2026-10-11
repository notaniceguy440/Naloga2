package com.example.naloga2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etFullName;
    private EditText etCountry;
    private EditText etEmail;
    private EditText etPhoneNumber;
    private EditText etPassword;
    private RadioGroup rgGender;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private CheckBox cbTerms;
    private Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, new OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                }
            });
        }

        initViews();
        setupListeners();
    }

    private void initViews() {
        etUsername = findViewById(R.id.etUsername);
        etFullName = findViewById(R.id.etFullName);
        etCountry = findViewById(R.id.etCountry);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        cbTerms = findViewById(R.id.cbTerms);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
    }

    private void setupListeners() {
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration() {
        String username = etUsername.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String country = etCountry.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        String password = etPassword.getText().toString();

        String gender = getString(R.string.gender_not_specified);
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        if (selectedGenderId == R.id.rbMale) {
            gender = rbMale.getText().toString();
        } else if (selectedGenderId == R.id.rbFemale) {
            gender = rbFemale.getText().toString();
        }

        boolean termsAccepted = cbTerms.isChecked();
        String termsText = termsAccepted ? "Yes" : "No";

        StringBuilder details = new StringBuilder();
        details.append("Username: ").append(username).append("\n");
        details.append("Full name: ").append(fullName).append("\n");
        details.append("Country: ").append(country).append("\n");
        details.append("Email: ").append(email).append("\n");
        details.append("Phone number: ").append(phoneNumber).append("\n");
        details.append("Password: ").append(password).append("\n");
        details.append("Gender: ").append(gender).append("\n");
        details.append("Terms Accepted: ").append(termsText);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(details.toString())
                .setPositiveButton(getString(R.string.dialog_ok), null)
                .show();
    }
}