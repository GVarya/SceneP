package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class SceneActivity extends AppCompatActivity implements View.OnClickListener {
    private Button allOn;
    private Button allOf;
    private TextView tv;
    private Sender t;
    private Button button_save;
    private boolean save = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        allOn = findViewById(R.id.fullOn);
        tv = findViewById(R.id.textView);
        allOn.setOnClickListener(this);
        allOf = findViewById(R.id.BlackOut);
        allOf.setOnClickListener(this);
        button_save = findViewById(R.id.save);
        button_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.fullOn){
           // tv.setText("Yes!");
            t = new Sender("all", 225);
            t.start();
        }
        else if(v.getId() == R.id.BlackOut){
            //tv.setText("Yes!");
            t = new Sender("all", 0);
            t.start();
        }
        else if(v.getId() == R.id.save){
            save = true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!save){
            t = new Sender("all", 0);
            t.start();
        }

    }

}