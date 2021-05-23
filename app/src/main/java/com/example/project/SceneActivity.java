package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.project.BeforeYouStartActivity.scenes;




public class SceneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);


        RecyclerView recyclerView = findViewById(R.id.scenes_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(SceneActivity.this));
        SceneAdapter adapter = new SceneAdapter(scenes);
        recyclerView.setAdapter(adapter);


    }





}