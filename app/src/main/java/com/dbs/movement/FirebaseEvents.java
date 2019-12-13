package com.dbs.movement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseEvents extends AppCompatActivity {

    private RecyclerView mEventList;
    DatabaseReference mDatabase;
    LinearLayoutManager mLinearLayoutManager;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseRecyclerAdapter<Event,FirebaseViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Event> options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_events);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        mEventList = findViewById(R.id.eventsList);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference("Data");

        showData();

    }
    private void showData() {
        options = new FirebaseRecyclerOptions.Builder<Event>().setQuery(mDatabase,Event.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Event, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull Event event) {
             holder.setDetails(getApplicationContext(),event.getImage(),event.getTitle(),event.getType(),event.getDate(), event.getDesc());
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_view,parent, false);

                 FirebaseViewHolder firebaseViewHolder = new FirebaseViewHolder(itemView);
                 firebaseViewHolder.setClickListener(new FirebaseViewHolder.ClickListener() {
                  @Override
                  public void onItemClick(View view, int position) {

                      Toast.makeText(FirebaseEvents.this, "HELLO", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void onItemLongClick(View view, int position) {

                      Toast.makeText(FirebaseEvents.this, "Long Click", Toast.LENGTH_LONG).show();

                  }
              });

                return firebaseViewHolder;
            }
        };

        mEventList.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mEventList.setAdapter(firebaseRecyclerAdapter);
    }

    protected void onStart(){

        super.onStart();

        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }
    }
}
