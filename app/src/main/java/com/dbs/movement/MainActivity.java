package com.dbs.movement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText date,location,time,type,image,title,desc;
    private Button createEvent;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference("Events");

        date = (EditText)findViewById(R.id.date);
        location = (EditText)findViewById(R.id.location);
        time = (EditText)findViewById(R.id.time);
        type = (EditText)findViewById(R.id.type);
        createEvent = (Button) findViewById(R.id.createEvent);


        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEvents();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.getString("Some") !=null){
            Toast.makeText(getApplicationContext(),"DATA:" + bundle.getString("Some"),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void AddEvents(){
        String eventDate = date.getText().toString();
        String eventLocation = location.getText().toString();
        String eventTime = time.getText().toString();
        String eventType = type.getText().toString();
        String eventImage = image.getText().toString();
        String eventTitle = title.getText().toString();
        String eventDesc = desc.getText().toString();

        if(!TextUtils.isEmpty(eventDate) && !TextUtils.isEmpty(eventLocation) && !TextUtils.isEmpty(eventTime) && !TextUtils.isEmpty(eventType)){

            String id = databaseReference.push().getKey();
            Event event = new Event(eventDate,eventLocation,eventTime,eventType, id, eventImage,eventDesc, eventTitle);
            databaseReference.child(id).setValue(event);
            date.setText("");
            location.setText("");
            time.setText("");
            type.setText("");
        }
        else {
            Toast.makeText(MainActivity.this, "Please Type Event Information",Toast.LENGTH_LONG).show();
        }
    }
}
