package com.samniya.jamal.hopeforanimalsapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button add;
    EditText etName1, etColor1, etPrice1;
    private Spinner AgeSpinner1, KindSpinner1;
    private String[] InfoList;
    public static final int SPECIAL_NUMBER = 1;
    private DatabaseReference databaseReference1;
 ;

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

        databaseReference1= FirebaseDatabase.getInstance().getReference().child("Animal");
         add = (Button)  findViewById(R.id.btnAdd);
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
                dataHandler();
                DatabaseReference AnimalInfo = databaseReference1.child(etName1.getText().toString());
                AnimalInfo.child(KindSpinner1.getSelectedItem().toString());
                AnimalInfo.child(AgeSpinner1.getSelectedItem().toString());
                AnimalInfo.child(etColor1.getText().toString());
                AnimalInfo.child(etPrice1.getText().toString());
                Intent intent1 = new Intent(getApplicationContext(), MainAllActivity.class);
                startActivity(intent1);
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


            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            private void dataHandler() {

                boolean isOk = true;// if all the fields well

                String name = etName1.getText().toString();
                String color = etColor1.getText().toString();
                String price = etPrice1.getText().toString();
                int finalPrice = Integer.parseInt(price);
                String kind = KindSpinner1.getSelectedItem().toString();
                String age = AgeSpinner1.getSelectedItem().toString();




                if (price.length() == 0) {
                    etPrice1.setError("please enter valued number");
                    isOk = false;
                }
                if (name.length() == 0) {
                    etName1.setError("please enter valued number");
                    isOk = false;
                }
                if (color.length() == 0) {
                    etColor1.setError("text can not be empty");
                    isOk = false;
                }
//                if (imageView1 == null) {
//                    Toast.makeText(getApplicationContext(), "please choose an Image", Toast.LENGTH_LONG).show();
//                    isOk = false;
//                }
//                if (kind.length() == 0) {
//                    TextView errorText = (TextView) KindSpinner1.getSelectedView();
//                    errorText.setError("Select an Item");
//                    isOk = false;
//                }
//                if (age.length() == 0) {
//                    TextView errorText1 = (TextView) AgeSpinner1.getSelectedView();
//                    errorText1.setError("Select an Item");
//                    isOk = false;
//                }


                if (isOk) {
                    final MyAnimal myAnimal = new MyAnimal();
                    myAnimal.getAge();
                    myAnimal.getColor();
                    myAnimal.getKind();
                    myAnimal.getPrice();
                    myAnimal.getName();


                    //get user email to set is as the owner of this task

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    myAnimal.setOwner(auth.getCurrentUser().getUid());

                    String key = databaseReference1.child("MyAnimal").push().getKey();
                    myAnimal.setKey(key);

                    databaseReference1.child("MyAnimal").child(key).setValue(myAnimal).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(AddActivity.this, "Add Successful", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getBaseContext(), AllFragment.class);

                                startActivity(i);

                            } else

                                Toast.makeText(AddActivity.this, "Add Failed", Toast.LENGTH_SHORT).show();

                        }

                    });
                }
            }
//            String name = etName1.getText().toString();
//            String color = etColor1.getText().toString();
//            String price = etPrice1.getText().toString();
//            int finalPrice = Integer.parseInt(price);
//            String kind = KindSpinner1.getSelectedItem().toString();
//            String age = AgeSpinner1.getSelectedItem().toString();






        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPECIAL_NUMBER) {
            Uri selectedPhoto = data.getData();
            imageView1.setImageURI(selectedPhoto);
        }
    }
}








