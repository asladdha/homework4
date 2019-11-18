package com.example.homework4birdtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Page2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextZipCode2;
    Button buttonSearch2, buttonGoToReport2;
    TextView textViewBirdName2, textViewPersonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        editTextZipCode2 = findViewById(R.id.editTextZipCode2);

        textViewBirdName2 = findViewById(R.id.textViewBirdName2);
        textViewPersonName = findViewById(R.id.textViewPersonName);

        buttonSearch2  = findViewById(R.id.buttonSearch2);
        buttonGoToReport2 = findViewById(R.id.buttonGoToReport2);

        buttonSearch2.setOnClickListener(this);
        buttonGoToReport2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");
        if(view == buttonSearch2){
            int zipCodeSearch = Integer.parseInt(editTextZipCode2.getText().toString());
            myRef.orderByChild("zipcode").equalTo(zipCodeSearch).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Bird foundBird = dataSnapshot.getValue(Bird.class);

                    String findBird = foundBird.birdName;
                    String findPerson = foundBird.personName;

                    textViewBirdName2.setText(findBird);
                    textViewPersonName.setText(findPerson);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else if(view == buttonGoToReport2){
            Intent goToReportIntent = new Intent(this, MainActivity.class);
            startActivity(goToReportIntent);
        }
    }
}
