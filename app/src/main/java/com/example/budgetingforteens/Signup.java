package com.example.budgetingforteens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    EditText user, password, password1;
    Button signup_b;
    DatabaseReference dr;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        password1 = findViewById(R.id.password1);
        tv = findViewById(R.id.tv);
        signup_b = findViewById(R.id.signup_b);
        mAuth = FirebaseAuth.getInstance();
        signup_b.setOnClickListener(this);
        dr = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View view) {
        if (view == signup_b) {
            String p1 = password.getText().toString();
            String p2 = password1.getText().toString();
            if (p1.equals(p2)) {
                tv.setText("");

                String username = user.getText().toString();
                String pass = password.getText().toString();
                String pass1 = password1.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    user.setError("Username cannot be empty");
                    user.requestFocus();
                }

                else if (TextUtils.isEmpty(pass)) {
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                }

                else if (TextUtils.isEmpty(pass1)) {
                    password1.setError("You must confirm your password");
                    password1.requestFocus();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(username, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();

//                                User user = new User(username, pass);
//                                dr.child("users").push().setValue(user);

                                Intent i = new Intent(Signup.this, MainActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(Signup.this, "Registration error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
            else{
                tv.setText("Password confirmation incorrect");
            }
        }
    }
}