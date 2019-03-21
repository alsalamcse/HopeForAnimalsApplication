package com.samniya.jamal.hopeforanimalsapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button add;
    EditText etName1, etColor1, etPrice1;
    private Spinner AgeSpinner1, KindSpinner1;
    private String[] InfoList;
    public static final int SPECIAL_NUMBER = 1;

    ImageButton selectPhoto1;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
         etName1 = findViewById(R.id.etName);///////////
        etPrice1 =  findViewById(R.id.etPrice);////////////
         etColor1 =  findViewById(R.id.etColor);/////////
        imageView1=findViewById(R.id.imageView11);

         add =  findViewById(R.id.btnAdd);
        AgeSpinner1 = (Spinner) findViewById(R.id.spinnerAge1);////////////
        KindSpinner1 = (Spinner) findViewById(R.id.spinnerKind1);////////////
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
        selectPhoto1 = (ImageButton) findViewById(R.id.selectPhoto1);///////////

        selectPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SPECIAL_NUMBER);
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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





            private void dataHandler(){

                boolean isOk =true;// if all the fields well
                String Name =etName1.getText().toString();
                String price=etPrice1.getText().toString();
                int finalPrice=Integer.parseInt(price);
                String Kind = KindSpinner1.getSelectedItem().toString();
                String Age = AgeSpinner1.getSelectedItem().toString();



                if (price.length()==0){
                    etPrice1.setError("please enter valued number");
                    isOk=false;
                }
                if (Name.length()==0){
                    etName1.setError("text can not be empty");
                    isOk=false;
                }
                if (imageView1 == null){
                    Toast.makeText(getApplicationContext() , "please choose an Image" , Toast.LENGTH_LONG).show();
                    isOk = false;
                }
                if (Kind.length()==0){
                    TextView errorText = (TextView)KindSpinner1.getSelectedView();
                    errorText.setError("Select an Item");
                    isOk = false;
                }
                if (Age.length()==0){
                    TextView errorText1=(TextView)AgeSpinner1.getSelectedView();
                    errorText1.setError("Select an Item");
                    isOk =false;
                }



                if (isOk)
                {
                    MyTask task1=new MyTask(Name, price, Kind, Age);
                    task1.getName(Name);
                    task1.getPrice(price);
                    task1.getKind(Kind);
                    task1.getAge(Age);


                }











            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if (requestCode == SPECIAL_NUMBER){
            Uri selectedPhoto = data.getData();
            imageView1.setImageURI(selectedPhoto);
        }
    }
}








