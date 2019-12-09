package com.dbs.movement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private DrawerLayout drawer;
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStatemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movement");


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new KnowYourRightsFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_kyr);
        }

        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intToMain);
            }
        });

        Toolbar mToolbar;
        RecyclerView mRecyclerView;
        List<EventData> mEventlist;
        EventData mEventData;


        mToolbar = findViewById(R.id.toolbar);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(HomeActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mEventlist = new ArrayList<>();
        mEventData = new EventData("Politics", getString(R.string.description_event_Politics), R.drawable.politics);
        mEventlist.add(mEventData);
        mEventData = new EventData("Laws", getString(R.string.description_event_Laws), R.drawable.law);
        mEventlist.add(mEventData);
        mEventData = new EventData("Protest", getString(R.string.description_event_Protest), R.drawable.law);
        mEventlist.add(mEventData);
        mEventData = new EventData("Politics", getString(R.string.description_event_Protest), R.drawable.politics);
        mEventlist.add(mEventData);
        mEventData = new EventData("Politics", getString(R.string.description_event_Protest), R.drawable.law);
        mEventlist.add(mEventData);
        mEventData = new EventData("Politics", getString(R.string.description_event_Protest), R.drawable.politics);
        mEventlist.add(mEventData);
        mEventData = new EventData("Politics", getString(R.string.description_event_Protest), R.drawable.protest);
        mEventlist.add(mEventData);
        mEventData = new EventData("Politics", getString(R.string.description_event_Protest), R.drawable.politics);
        mEventlist.add(mEventData);

        MyAdapter myAdapter = new MyAdapter(HomeActivity.this, mEventlist);
        mRecyclerView.setAdapter(myAdapter);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_kyr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KnowYourRightsFragment()).commit();
                break;
            case R.id.nav_event:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EventFragment()).commit();
                break;
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InformationFragment()).commit();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
               drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

