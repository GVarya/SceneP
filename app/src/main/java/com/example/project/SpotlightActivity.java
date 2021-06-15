package com.example.project;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.project.BeforeYouStartActivity.scenes;
import static com.example.project.MainActivity.currentPosition;
import static com.example.project.MainActivity.spCount;


public class SpotlightActivity extends AppCompatActivity implements  View.OnClickListener {
    private Button button_save_as_scene;
    private Dialog newSceneNameDialog;
    TextView dialog_tv;
    private Button button_save_scene_name;
    private String sceneName = "";
    private EditText sceneNameEditText;
    ArrayList<Lamp> lamps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotlight);


        button_save_as_scene = findViewById(R.id.save_as_scene);
        button_save_as_scene.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.spotlights_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(SpotlightActivity.this));
        SpotlightAdapter adapter = new SpotlightAdapter(spCount);
        recyclerView.setAdapter(adapter);

        newSceneNameDialog = new Dialog(SpotlightActivity.this);
        newSceneNameDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        newSceneNameDialog.setContentView(R.layout.dialog_window_question);

        sceneNameEditText = newSceneNameDialog.findViewById(R.id.input);
        button_save_scene_name = newSceneNameDialog.findViewById(R.id.save_button);
        button_save_scene_name.setOnClickListener(this);
        dialog_tv = newSceneNameDialog.findViewById(R.id.textView);
        dialog_tv.setText("Введите название сцены");

    }



    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.save_as_scene){
            newSceneNameDialog.show();


        }
        else if(v.getId() == R.id.save_button){
            //Log.i("TAG", "onClick: was");
            sceneName = sceneNameEditText.getText().toString();
            scenes.add(new Scene(sceneName, Scene.cloneLamps(currentPosition.getLamps())));
            Log.i("scenes", scenes.get(scenes.size() - 1).getName() + " " + Arrays.toString(scenes.get(scenes.size() - 1).getLamps().toArray()) + "");
            newSceneNameDialog.dismiss();


        }

        
    }


}
