package com.dbs.movement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mEvent;
    TextView mDescription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        mToolbar = findViewById(R.id.toolbar);
        mEvent = findViewById(R.id.ivImage);
        mDescription = findViewById(R.id.tvDescription);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle !=null) {
            mToolbar.setTitle(mBundle.getString("Title"));
            mEvent.setImageResource(mBundle.getInt("Image"));
            mDescription.setText(mBundle.getString("Description"));
        }
    }
}
