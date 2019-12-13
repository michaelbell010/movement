package com.dbs.movement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText date,location,time,title,desc,organiser;
    private Spinner type;
    private Button createEvent;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner type_spinner = findViewById(R.id.type_spinner);
        type_spinner.setOnItemSelectedListener(this);
        getSupportActionBar().hide();

        databaseReference = FirebaseDatabase.getInstance().getReference("Events");

        date = (EditText)findViewById(R.id.date);
        location = (EditText)findViewById(R.id.location);
        time = (EditText)findViewById(R.id.time);
        type = (Spinner) findViewById(R.id.type_spinner);
        desc = (EditText)findViewById(R.id.desc);
        title = (EditText)findViewById(R.id.title);
        createEvent = (Button) findViewById(R.id.createEvent);
        organiser = (EditText) findViewById(R.id.organiser);


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
        String eventType = type.getAdapter().toString();
        String eventDesc = desc.getText().toString();
        String eventTitle = title.getText().toString();
        String eventOrganiser = organiser.getText().toString();


        if(!TextUtils.isEmpty(eventDate) && !TextUtils.isEmpty(eventLocation) && !TextUtils.isEmpty(eventTime)){

            String id = databaseReference.push().getKey();
            Event event = new Event(id,eventDate,eventLocation,eventTime,eventType,eventDesc,eventTitle,eventOrganiser);
            databaseReference.child(id).setValue(event);
            date.setText("");
            location.setText("");
            time.setText("");
            type.getSelectedItemPosition();
            desc.setText("");
            title.setText("");
            organiser.setText("");


        }
        else {
            Toast.makeText(MainActivity.this, "Please Type Event Information",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}