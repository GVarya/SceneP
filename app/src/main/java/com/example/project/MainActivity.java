package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.project.R.id.to_scenes;
import static com.example.project.R.id.to_spotlight;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button to_scenes = (Button) findViewById(R.id.to_scenes);
        Button to_spotlight = (Button) findViewById(R.id.to_spotlight);
        to_scenes.setOnClickListener(this);
        to_spotlight.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == to_scenes) {
            Intent i = new Intent(MainActivity.this, SceneActivity.class);
            startActivity(i);

        }
        else if (v.getId() == to_spotlight) {
            Intent i1 = new Intent(MainActivity.this, SpotlightActivity.class);
            startActivity(i1);

        }
    }
}


