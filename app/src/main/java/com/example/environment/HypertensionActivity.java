package com.example.environment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HypertensionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textView, txtHypertension, txtNotes;
    private Button button;
    private Spinner spinner, spinner2, spinner3, spinner4;

    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hypertension);

        //Adding elements to view
        textView = findViewById(R.id.textView);
        txtHypertension = findViewById(R.id.txtHypertension);
        button = findViewById(R.id.button);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        txtNotes = findViewById(R.id.txtNotes);

        //Database connection
        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("Hypertension");

        //Populating spinner/dropdown box with data from the medicationHypertension array.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.medicationHypertension, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        //Populating spinner/dropdown box with data from the dosageHypertension array.
        ArrayAdapter<CharSequence> adapterDosage = ArrayAdapter.createFromResource(this, R.array.dosageHypertension, android.R.layout.simple_spinner_item);
        adapterDosage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterDosage);

        spinner2.setOnItemSelectedListener(this);

        //Populating spinner/dropdown box with data from the amountHypertension array.
        ArrayAdapter<CharSequence> adapterAmount = ArrayAdapter.createFromResource(this, R.array.amountHypertension, android.R.layout.simple_spinner_item);
        adapterAmount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterAmount);

        spinner3.setOnItemSelectedListener(this);

        //Populating spinner/dropdown box with data from the timeHypertension array.
        ArrayAdapter<CharSequence> adapterTime = ArrayAdapter.createFromResource(this, R.array.timeHypertension, android.R.layout.simple_spinner_item);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapterTime);

        spinner4.setOnItemSelectedListener(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String medication = spinner.getSelectedItem().toString();
                String dosage = spinner2.getSelectedItem().toString();
                String amount = spinner3.getSelectedItem().toString();
                String time = spinner4.getSelectedItem().toString();
                String instructions = txtNotes.getText().toString();

                //Adding items selected in spinners to the database
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
