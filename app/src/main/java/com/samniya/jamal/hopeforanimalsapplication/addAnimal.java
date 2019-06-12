package com.samniya.jamal.hopeforanimalsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addAnimal extends showAllTasksActivity {
    private EditText etName, etColor, etPrice;
    private Spinner spnKind, spnAge;
    private Button btnAdd;
    private ImageButton btnImage;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        etName = (EditText) findViewById(R.id.etName);
        etColor = findViewById(R.id.etColor);
        etPrice = findViewById(R.id.etPrice);
        spnAge = findViewById(R.id.spinnerAge);
        spnKind = findViewById(R.id.spinnerKind);
        btnAdd = findViewById(R.id.btnAdd);
        btnImage = findViewById(R.id.selectPhoto1);
        imageView = findViewById(R.id.imageView11);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHadler();
            }
        });


    }

    private void dataHadler() {
        boolean isok = true;
        String name = etName.getText().toString();
        String color = etColor.getText().toString();
        String price = etPrice.getText().toString();
        String Kind = spnKind.getSelectedItem().toString();
        String Age = spnAge.getSelectedItem().toString();


        if (name.length() == 0) {
            etName.setError("you have to write a name ");
            isok = false;


        }
        if (color.length() == 0) {
            etColor.setError("you have to write a color");
            isok = false;
        }
        if (price.length() == 0) {
            etPrice.setError("you have to write a price");
            isok = false;
        }


        if (isok) {
            MyAnimal animal = new MyAnimal();

            animal.setName(name);
            animal.setColor(color);
            animal.setPrice(price);


            //FirebaseAuth auth=FirebaseAuth.getInstance();
            //profile.setOwner(auth.getCurrentUser().getEmail());

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            String key = reference.child("MyBook").push().getKey();
            animal.setKey(key);
            reference.child("MyBook").child(key).setValue(animal).addOnCompleteListener(new OnCompleteListener<Void>() {




                {

                }


                @Override
                public void onComplete(@NonNull Task<Void> task1) {
                    if (task1.isSuccessful()) {
                        Toast.makeText(addAnimal.this, "add successed", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(addAnimal.this, MyFragment.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(addAnimal.this, "add failed" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}
