package com.example.cyclone.cyclone_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
    }

    public void daily_usage (View v){
        Intent redirect = new Intent(Interface.this, MainActivity.class);
        startActivity(redirect);
    }

    public void Home (View v){
        Intent redirect = new Intent(Interface.this, Home.class);
        startActivity(redirect);
    }
}
