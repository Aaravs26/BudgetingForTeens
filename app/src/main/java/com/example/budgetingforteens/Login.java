package com.example.budgetingforteens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button login;
    TextView signup;
    EditText user, password;
    private SharedPreferenceConfig sharedPreferenceConfig;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        if (sharedPreferenceConfig.readLoginStatus()) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == signup) {
            Intent i = new Intent(Login.this, Signup.class);
            startActivity(i);
        }

        if (view == login) {

//            private void loginUser() {
                String username_entered = user.getText().toString();
                String password_entered = password.getText().toString();

                if (TextUtils.isEmpty(username_entered)) {
                    user.setError("Username cannot be empty");
                    user.requestFocus();
                } else if (TextUtils.isEmpty(password_entered)) {
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                } else {
                    mAuth.signInWithEmailAndPassword(username_entered, password_entered).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, MainActivity.class));
                                sharedPreferenceConfig.writeLoginStatus(true);
                                SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                                editor.putString("name", username_entered);
                                editor.apply();
                            } else {
                                Toast.makeText(Login.this, "Login error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }

//        if (view == login) {
//            String user_name_entered = "";
//            String password_entered = "";
//
//            user_name_entered = user.getText().toString();
//            password_entered = password.getText().toString();
//            if (user_name_entered.equals(getResources().getString(R.string.user_name)) && password_entered.equals(getResources().getString(R.string.user_password))){
//                Intent i = new Intent(Login.this, MainActivity.class);
//                startActivity(i);
//                finish();
//                sharedPreferenceConfig.writeLoginStatus(true);
//                SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
//                editor.putString("name", user_name_entered);
//                editor.apply();
//            }
//            else {
//                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
//                user.setText("");
//                password.setText("");
//            }
//        }
    }
