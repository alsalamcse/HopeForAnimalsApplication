package com.samniya.jamal.hopeforanimalsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showAllTasksActivity extends AppCompatActivity {

    private ListView lvTask;
    private TaskAdapter taskAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_tasks);
        lvTask = findViewById(R.id.lstvTask);


    }
    private void readTask()
    {
        //refernce to the database root
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        reference.child("MyTask").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getBaseContext(),"data changed",Toast.LENGTH_SHORT).show();
                taskAdapter.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    MyTask task=d.getValue(MyTask.class);
                    taskAdapter.add(task);
                }
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),"data cancelled",Toast.LENGTH_SHORT).show();


            }
        });





    }
}
