package com.samniya.jamal.hopeforanimalsapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyAnimalAdapter extends ArrayAdapter<MyAnimal> {


    public MyAnimalAdapter(Context context, int resource) {

        super(context, resource);

    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.animal_item, parent, false);


            final MyAnimal myAnimal = getItem(position); // return data object number"pos"
//
//            TextView id = convertView.findViewById(R.id.id);
            TextView name = convertView.findViewById(R.id.etName);
            TextView color = convertView.findViewById(R.id.etColor);
//
//
//            ImageButton edit = convertView.findViewById(R.id.edit);
//
//            ImageButton medicines = convertView.findViewById(R.id.medicines);
//
//
//            id.setText(mypatient.getId());
            name.setText(myAnimal.getName());
            color.setText(myAnimal.getColor());

//

        }


        return convertView;
    }

}


