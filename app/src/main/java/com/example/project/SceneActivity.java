package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import static com.example.project.BeforeYouStartActivity.scenes;




public class SceneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        for(Scene s: scenes) {
            Log.i("scenes", s.getName() + " " + Arrays.toString(s.getLamps().toArray()));
        }
        RecyclerView recyclerView = findViewById(R.id.scenes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(SceneActivity.this));
        SceneAdapter adapter = new SceneAdapter(scenes);
        recyclerView.setAdapter(adapter);


    }





}