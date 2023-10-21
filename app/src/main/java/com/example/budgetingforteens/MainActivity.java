package com.example.budgetingforteens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
//    public static MyAppDatabase myAppDatabase;
    Button budget, leisure, transport, food, clothing, books, gifts, misc, signout;
    SharedPreferenceConfig sharedPreferenceConfig;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        budget = findViewById(R.id.budget);
        leisure = findViewById(R.id.leisure);
        transport = findViewById(R.id.transport);
        food = findViewById(R.id.food);
        clothing = findViewById(R.id.clothing);
        books = findViewById(R.id.books);
        gifts = findViewById(R.id.gifts);
        misc = findViewById(R.id.misc);
        signout = findViewById(R.id.signout);
        mAuth = FirebaseAuth.getInstance();
//        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "userDB").allowMainThreadQueries().build();
        sharedPreferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        SharedPreferences s = getSharedPreferences("name", MODE_PRIVATE);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                finish();
                sharedPreferenceConfig.writeLoginStatus(false);
            }
        });
    }

    public void budget(View view) {
        Intent i = new Intent(MainActivity.this, Budget.class);
        startActivity(i);
    }

    public void leisure(View view) {
        Intent i = new Intent(MainActivity.this, Leisure.class);
        startActivity(i);
    }

    public void transport(View view) {
        Intent i = new Intent(MainActivity.this, transport.class);
        startActivity(i);
    }

    public void food(View view) {
        Intent i = new Intent(MainActivity.this, food.class);
        startActivity(i);
    }

    public void clothing(View view) {
        Intent i = new Intent(MainActivity.this, clothing.class);
        startActivity(i);
    }

    public void books(View view) {
        Intent i = new Intent(MainActivity.this, books.class);
        startActivity(i);
    }

    public void gifts(View view) {
        Intent i = new Intent(MainActivity.this, gifts.class);
        startActivity(i);
    }

    public void misc(View view) {
        Intent i = new Intent(MainActivity.this, misc.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }
}