package com.example.environment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

        //Initialised variables for use in the methods.
    private EditText inputID, inputName;
    private Button btnRead, btnSave, btnLogin;
    private TextView textViewID, textViewName, pwdPassword;
        //Database connections
        //Reference: https://www.youtube.com/watch?v=hXuI0nLWKTE&list=PLYx38U7gxBf3pmsHVTUwRT_lGON6ZIBHi&index=5
        //I code from the video referenced above to connect the Firebase database.
    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Added elements to the emulator upon load.
        inputID = findViewById(R.id.inputID);
        inputName = findViewById(R.id.inputName);
        pwdPassword = findViewById(R.id.pwdPassword);

        btnSave = findViewById(R.id.btnSave);
        btnLogin = findViewById(R.id.btnLogin);

        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("Users");

            /* When the Save button is clicked, variables are initialised and changed to strings.
               Text in textviews is assigned to the variables.
               Used the push() function to push data from the variables to the Database as new records.*/
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if ((inputID.getText().toString().equals("")) && (inputName.getText().toString().equals("")) && (pwdPassword.getText().toString().equals(""))) {
                    inputID.setError("FIELD CANNOT BE EMPTY");
                    inputName.setError("FIELD CANNOT BE EMPTY");
                }else if (inputID.getText().toString().equals("")){
                    inputID.setError("FIELD CANNOT BE EMPTY");
                }else if (inputName.getText().toString().equals("")){
                    inputName.setError("FIELD CANNOT BE EMPTY");
                }else if (pwdPassword.getText().toString().equals("")) {
                   pwdPassword.setError("FIELD CANNOT BE EMPTY");
               }
                else {
                   int id = Integer.parseInt(inputID.getText().toString());
                   String name = inputName.getText().toString();
                   String password = pwdPassword.getText().toString();

                       //Adds the values for ID and Name to the database as the ID and Name values for the Object User.
                       //Code Reference https://www.youtube.com/watch?v=c9W6dQQQuMI&list=PLYx38U7gxBf3pmsHVTUwRT_lGON6ZIBHi&index=13
                   String key = rootDatabaseref.push().getKey();
                   rootDatabaseref.child(key).child("ID").setValue(id);
                   rootDatabaseref.child(key).child("Name").setValue(name);
                   rootDatabaseref.child(key).child("Password").setValue(password);

                   openUserLoginActivity();
                }

            }
        });

            //Opens the UserLoginActivity when the Login button is clicked
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((inputID.getText().toString().equals("")) && (inputName.getText().toString().equals("")) && (pwdPassword.getText().toString().equals(""))) {
                    inputID.setError("FIELD CANNOT BE EMPTY");
                    inputName.setError("FIELD CANNOT BE EMPTY");
                }else if (inputID.getText().toString().equals("")){
                    inputID.setError("FIELD CANNOT BE EMPTY");
                }else if (inputName.getText().toString().equals("")){
                    inputName.setError("FIELD CANNOT BE EMPTY");
                }else if (pwdPassword.getText().toString().equals("")) {
                    pwdPassword.setError("FIELD CANNOT BE EMPTY");
                }else {
                    openUserLoginActivity();
                }
            }
        });
    }
            //Used to start the UserLoginActivity so as it can be accessed when the Login button is clicked.
        public void openUserLoginActivity(){
            System.out.println("User...");
            Intent intent = new Intent(this, UserLoginActivity.class);
            startActivity(intent);

        }

}