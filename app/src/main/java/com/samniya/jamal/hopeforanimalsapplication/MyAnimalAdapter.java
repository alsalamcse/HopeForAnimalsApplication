package com.samniya.jamal.hopeforanimalsapplication;

public class MyAnimalAdapter {

    public class MyAnimalAdapter extends ArrayAdapter<Animal> {



        public MypatientAdapter(Context context, int resource) {

            super(context, resource);

        }



        @Override

        public View getView(int position, View convertView, ViewGroup parent) {





            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.patient_item, parent, false);





                final Mypatient mypatient = getItem(position); // return data object number"pos"

                TextView id = convertView.findViewById(R.id.id);



                ImageButton edit = convertView.findViewById(R.id.edit);

                ImageButton medicines = convertView.findViewById(R.id.medicines);



                id.setText(mypatient.getId());


            }
