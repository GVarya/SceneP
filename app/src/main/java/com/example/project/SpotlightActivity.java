package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;

public class SpotlightActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private ArrayList<Integer> seekBarsId = new ArrayList<>();
    private Sender s;
    private Sender destroy;
    private boolean save;
    private Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotlight);
        for (int i = 1; i < 13; i++){
            seekBarsId.add(getBaseContext().getResources().getIdentifier("seekBar"+i, "id", getBaseContext().getPackageName()));
        }
        for(int i = 1; i < 13; i++){
            SeekBar sb = findViewById(getBaseContext().getResources().getIdentifier("seekBar"+i, "id", getBaseContext().getPackageName()));
            sb.setOnSeekBarChangeListener(this);
        }
        button_save = findViewById(R.id.save);
        button_save.setOnClickListener(this);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        s = new Sender(seekBarsId.indexOf(seekBar.getId()) + "", seekBar.getProgress());
        s.start();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.save){
            save = true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!save){
            destroy = new Sender("all", 0);
            destroy.start();
        }

    }
}