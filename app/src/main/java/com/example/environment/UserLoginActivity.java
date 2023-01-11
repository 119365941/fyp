package com.example.environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserLoginActivity extends AppCompatActivity {

    //Initialise variables and database connections.
    private TextView textviewWelcome, textviewName;

    private DatabaseReference rootDatabaseref;

    private Button btnHypertension, btnCholesterol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //Adds variables to the layout when the emulator loads.
        textviewWelcome = findViewById(R.id.textviewWelcome);
        textviewName = findViewById(R.id.textviewName);
        btnCholesterol = findViewById(R.id.btnCholesterol);
        btnHypertension = findViewById(R.id.btnHypertension);

        //Connection to the database when the emulator loads.
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("Users");


        btnHypertension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hypertension");
                Intent intentH = new Intent(UserLoginActivity.this, HypertensionActivity.class);
                startActivity(intentH) ;
            }
        });

        btnCholesterol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cholesterol");
                Intent intentC = new Intent(UserLoginActivity.this, CholesterolActivity.class);
                startActivity(intentC) ;
            }
        });
    }

}
