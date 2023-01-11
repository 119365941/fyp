package com.example.environment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CholesterolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textView, txtCholesterol, txtNotes;
    private Button button;
    private Spinner spinnerMedication, spinnerDosage, spinnerAmount, spinnerTime;

    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cholesterol);

        textView = findViewById(R.id.textView);
        txtCholesterol = findViewById(R.id.txtCholesterol);
        button = findViewById(R.id.button);
        spinnerMedication = findViewById(R.id.spinnerMedication);
        spinnerDosage = findViewById(R.id.spinnerDosage);
        spinnerAmount = findViewById(R.id.spinnerAmount);
        spinnerTime = findViewById(R.id.spinnerTime);
        txtNotes = findViewById(R.id.txtNotes);

        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("Cholesterol");

        //https://www.youtube.com/watch?v=on_OrrX7Nw4
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medicationCholesterol, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedication.setAdapter(adapter);

        spinnerMedication.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterDosage = ArrayAdapter.createFromResource(this, R.array.dosageCholesterol, android.R.layout.simple_spinner_item);
        adapterDosage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDosage.setAdapter(adapterDosage);

        spinnerDosage.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterAmount = ArrayAdapter.createFromResource(this, R.array.amountCholesterol, android.R.layout.simple_spinner_item);
        adapterAmount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAmount.setAdapter(adapterAmount);

        spinnerAmount.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapterTime= ArrayAdapter.createFromResource(this, R.array.timeCholesterol, android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(adapterTime);

        spinnerTime.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String medication = spinnerMedication.getSelectedItem().toString();
                String dosage = spinnerDosage.getSelectedItem().toString();
                String amount = spinnerAmount.getSelectedItem().toString();
                String time = spinnerTime.getSelectedItem().toString();
                String instructions = txtNotes.getText().toString();


                String key = rootDatabaseref.push().getKey();
                rootDatabaseref.child(key).child("Medication").setValue(medication);
                rootDatabaseref.child(key).child("Dosage").setValue(dosage);
                rootDatabaseref.child(key).child("Amount").setValue(amount);
                rootDatabaseref.child(key).child("Time").setValue(time);
                rootDatabaseref.child(key).child("Notes").setValue(instructions);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
