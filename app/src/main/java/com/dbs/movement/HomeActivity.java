package com.dbs.movement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStatemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, SignupActivity.class);
                startActivity(intToMain);
            }
        });

        Toolbar mToolbar;
        RecyclerView mRecyclerView;
        List<EventData> mEventlist;
        EventData mEventData;


        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getResources().getString(R.string.app_name));
        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(HomeActivity.this, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mEventlist = new ArrayList<>();
        mEventData = new EventData("Politics", getString(R.string.description_event_Politics), R.drawable.politics);
        mEventlist.add(mEventData);
        mEventData = new EventData("Laws", getString(R.string.description_event_Laws), R.drawable.law);
        mEventlist.add(mEventData);

        MyAdapter myAdapter = new MyAdapter(HomeActivity.this, mEventlist);
        mRecyclerView.setAdapter(myAdapter);

    }




}

