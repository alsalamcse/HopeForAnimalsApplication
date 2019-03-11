package com.samniya.jamal.hopeforanimalsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button search;
    private Spinner AgeSpinner , KindSpinner ;
    private  String [] InfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Button search = (Button) findViewById(R.id.searchBtn);

        Spinner KindSpinner = (Spinner) findViewById(R.id.spinnerAge);
        Spinner AgeSpinner = (Spinner) findViewById(R.id.spinnerKind);
        // Spinner click listener
        AgeSpinner.setOnItemSelectedListener(this);
        KindSpinner.setOnItemSelectedListener(this);

        List<String> Kind = new ArrayList<String>();
        Kind.add("Dog");
        Kind.add("Cat");
        Kind.add("Bird");
        Kind.add("Rabbit");

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("0-2");
        categories.add("2-5");
        categories.add("5-10");
        categories.add("10+");

        // Creating adapter for AgeSpinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Kind);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to AgeSpinner and kindSpinner
        AgeSpinner.setAdapter(dataAdapter);
        KindSpinner.setAdapter(dataAdapter2);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext() , ResultActivity.class); /////
                startActivity(iii);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //String item = parent.getItemAtPosition(position).toString();
       // InfoList[0] = item;
        switch (parent.getId()){
            case R.id.spinnerKind:
                String item = parent.getItemAtPosition(position).toString();
               // InfoList[1] = item;
                Toast.makeText(this, ""+item, Toast.LENGTH_SHORT).show();
                break;

            case R.id.spinnerAge:
                String item2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(this, ""+item2, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}