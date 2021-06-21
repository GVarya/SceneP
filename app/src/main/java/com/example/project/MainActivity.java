package com.example.project;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.project.BeforeYouStartActivity.allOn;
import static com.example.project.BeforeYouStartActivity.blackOut;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int spCount = 12;
    public static Scene currentPosition;


    private Button toScenes;
    private Button to_spotlight;
    private Button to_filter;

    private Dialog newSpCountDialog;
    private Button button_save;
    private TextView dialog_tv;
    private EditText spCountInput;
    private Button changeSpCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toScenes = findViewById(R.id.to_scenes_button);
        to_spotlight = findViewById(R.id.to_spotlight_button);
        changeSpCount = findViewById(R.id.changeSpCount);
        to_filter = findViewById(R.id.to_filter_button);
        toScenes.setOnClickListener(this);
        to_spotlight.setOnClickListener(this);
        to_filter.setOnClickListener(this);
        changeSpCount.setOnClickListener(this);

        currentPosition = new Scene("currentPosition", spCount, 0);




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
        else if (v.getId() == R.id.to_filter_button) {
            Intent i2 = new Intent(MainActivity.this, FilterActivity.class);
            startActivity(i2);

        }
        else if (v.getId() == R.id.save_button){


            if(spCountInput.getText().toString().length() > 0){
                try {
                    if(parseInt(spCountInput.getText().toString()) > 512){
                        spCount = 512;
                    }
                    else {
                        spCount = parseInt(spCountInput.getText().toString());
                        currentPosition.changeLampsCount(spCount);
                        allOn.changeLampsCount(spCount);
                        blackOut.changeLampsCount(spCount);
                    }
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }


            }


            newSpCountDialog.dismiss();
        }
        else if(v.getId() == R.id.changeSpCount){
            newSpCountDialog = new Dialog(MainActivity.this);
            newSpCountDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            newSpCountDialog.setContentView(R.layout.dialog_window_question);
            button_save = newSpCountDialog.findViewById(R.id.save_button);
            button_save.setOnClickListener(this);
            dialog_tv = newSpCountDialog.findViewById(R.id.textView);
            dialog_tv.setText("Введите количество прожекторов");
            spCountInput = newSpCountDialog.findViewById(R.id.input);
            newSpCountDialog.show();
        }
    }
}


