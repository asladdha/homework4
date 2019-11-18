package com.example.homework4birdtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextZipCode, editTextPersonName;
    Button buttonSubmit, buttonGoToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName.findViewById(R.id.editTextBirdName);
        editTextZipCode.findViewById(R.id.editTextZipCode);
        editTextPersonName.findViewById(R.id.editTextPersonName);

        buttonSubmit.findViewById(R.id.buttonSubmit);
        buttonGoToSearch.findViewById(R.id.buttonGoToSearch);
    }

    @Override
    public void onClick(View view) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if(view == buttonSubmit){
            String birdName = editTextBirdName.getText().toString();
            String personName = editTextPersonName.getText().toString();
            int zipCode = Integer.parseInt(editTextZipCode.getText().toString());

            Bird myBird = new Bird(birdName, personName, zipCode);

            myRef.push().setValue(myBird);

        }else if(buttonGoToSearch == view){
            Intent searchingIntent = new Intent(this, Page2Activity.class);
            startActivity(searchingIntent);
        }
    }
}
