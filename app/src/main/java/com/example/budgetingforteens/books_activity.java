package com.example.budgetingforteens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class books_activity extends AppCompatActivity {
    ListView listView;
    DatabaseReference dr;
    FirebaseAuth mAuth;
    String uID;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books2);
        listView = findViewById(R.id.lv1);
        tv = findViewById(R.id.tv1);
        ArrayList<String> userList = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,userList);
        listView.setAdapter(adapter);
        mAuth = FirebaseAuth.getInstance();
//        System.out.println(mAuth.getUid());
//        uID = Objects.requireNonNull(mAuth.getCurrentUser()).toString();
//        Toast.makeText(this, uID, Toast.LENGTH_SHORT).show();
        dr = FirebaseDatabase.getInstance().getReference("/users/Books");
//        System.out.println(dr.getKey());
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot userDataSnap: dataSnapshot.getChildren()) {
                    try {
                        String currentUid = Objects.requireNonNull(userDataSnap.child("uid").getValue()).toString();
                        if(Objects.equals(mAuth.getUid(), currentUid)) {
                            System.out.println(currentUid);

//                            String ma = userDataSnap.child("activity").getValue().toString();
//                            tv.setText(ma);
//                            Map map = (Map) userDataSnap.getValue();
//                            userList.add(map.get("activity").toString());
//                            userList.add(map.get("date").toString());
//                            userList.add(map.get("expenditure").toString());

                            userList.add(userDataSnap.getValue().toString());
                            System.out.println("LIST"+userList);
                        }
                    }
                    catch(DatabaseException e) {
                        dataSnapshot.getKey();
                    }
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}