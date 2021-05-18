package com.example.project;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.project.BeforeYouStartActivity.s;
import static com.example.project.BeforeYouStartActivity.scenes;


public class SpotlightActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private ArrayList<Integer> seekBarsId = new ArrayList<>();
    private boolean save;
    private Button button_save;
    private Button button_save_as_scene;
    private final int SpotlightsCount = 12;
    private Dialog newSceneNameDialog;
    private Button button_save_scene_name;
    private String sceneName = "";
    private EditText sceneNameEditText;
    ArrayList<Lamp> lamps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotlight);
        for (int i = 1; i < SpotlightsCount + 1; i++){
            seekBarsId.add(getBaseContext().getResources().getIdentifier("seekBar"+i, "id", getBaseContext().getPackageName()));
        }
        for(int i = 1; i < SpotlightsCount + 1; i++){
            SeekBar sb = findViewById(getBaseContext().getResources().getIdentifier("seekBar"+i, "id", getBaseContext().getPackageName()));
            sb.setOnSeekBarChangeListener(this);
        }

        button_save = findViewById(R.id.save);
        button_save.setOnClickListener(this);
        button_save_as_scene = findViewById(R.id.save_as_scene);
        button_save_as_scene.setOnClickListener(this);

        newSceneNameDialog = new Dialog(SpotlightActivity.this);
        newSceneNameDialog.setContentView(R.layout.dialog_window);

        sceneNameEditText = newSceneNameDialog.findViewById(R.id.scene_name);
        button_save_scene_name = newSceneNameDialog.findViewById(R.id.button_save_scene_name);
        button_save_scene_name.setOnClickListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        s.send(new Lamp(seekBarsId.indexOf(seekBar.getId()), seekBar.getProgress()));

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.save){
            save = true;
        }
        else if(v.getId() == R.id.save_as_scene){
            lamps.clear();
            for (int i = 1; i < SpotlightsCount + 1; i++){
                SeekBar sb = findViewById(getBaseContext().getResources().getIdentifier("seekBar"+i, "id", getBaseContext().getPackageName()));
                lamps.add(new Lamp(i - 1, sb.getProgress()));
            }
            newSceneNameDialog.show();


        }
        else if(v.getId() == R.id.button_save_scene_name){
            Log.i("TAG", "onClick: was");
            sceneName = sceneNameEditText.getText().toString();
            scenes.add(new Scene(sceneName, lamps));
            newSceneNameDialog.dismiss();


        }

        
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(!save){
//            s.send("all", 0);
//
//        }
//
//    }
}