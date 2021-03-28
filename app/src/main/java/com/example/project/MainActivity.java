package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button toScenes;
    private Button to_spotlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toScenes = findViewById(R.id.to_scenes_button);
        to_spotlight = findViewById(R.id.to_spotlight_button);
        toScenes.setOnClickListener(this);
        to_spotlight.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.to_scenes_button) {
            Intent i = new Intent(MainActivity.this, SceneActivity.class);
            startActivity(i);

        }
        else if (v.getId() == R.id.to_spotlight_button) {
            Intent i1 = new Intent(MainActivity.this, SpotlightActivity.class);
            startActivity(i1);

        }
    }
}


