package com.samniya.jamal.hopeforanimalsapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Button add;
    EditText etName1,etColor1,etPrice1;
    private Spinner AgeSpinner1, KindSpinner1;
    private String[] InfoList;
    ImageButton selectPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText etName1 =(EditText)findViewById(R.id.etName);
        EditText etPrice1 = (EditText)findViewById(R.id.etPrice);
        EditText etColor1 =(EditText)findViewById(R.id.etColor);
        Button add =(Button)findViewById(R.id.btnAdd);
         AgeSpinner1 = (Spinner) findViewById(R.id.spinnerAge1);
         KindSpinner1 = (Spinner) findViewById(R.id.spinnerKind1);
        AgeSpinner1.setOnItemSelectedListener(this);
        KindSpinner1.setOnItemSelectedListener(this);

        List<String> Kind = new ArrayList<String>();
        Kind.add("Dog");
        Kind.add("Cat");
        Kind.add("Bird");
        Kind.add("Rabbit");

        // Spinner Drop down elements
        List<String> Age = new ArrayList<String>();
        Age.add("0-2");
        Age.add("2-5");
        Age.add("5-10");
        Age.add("10+");


        // Creating adapter for AgeSpinner
        ArrayAdapter<String> dataAdapter22 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Kind);

        ArrayAdapter<String> dataAdapter11 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Age);

        // Drop down layout style - list view with radio button
        dataAdapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to AgeSpinner and kindSpinner
        AgeSpinner1.setAdapter(dataAdapter11);
        KindSpinner1.setAdapter(dataAdapter22);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getApplicationContext(), MainAllActivity.class); /////
                startActivity(i2);


                selectPhoto = (ImageButton) findViewById(R.id.selectPhoto1);

                selectPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1234);

                    }
                });
            }

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.spinnerKind1:
                        String item11 = parent.getItemAtPosition(position).toString();
                        break;
                    case R.id.spinnerAge1:
                        String item22 = parent.getItemAtPosition(position).toString();
                        break;

                }


            }

            public void onNothingSelected(AdapterView<?> parent) {

            }


            public void onActivityResult(int requestCode, int resultCode, Intent intent) {
                AddActivity.super.onActivityResult(requestCode, resultCode, intent);
                if (requestCode == 1234) {
                    if (resultCode == Activity.RESULT_OK) {
                        if (intent != null) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), intent.getData());
                                selectPhoto.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


                    @Override
                    public void onItemSelected (AdapterView < ? > parent, View view,int position,
                    long id){

                    }

                    @Override
                    public void onNothingSelected (AdapterView < ? > parent){

                    }
                }








