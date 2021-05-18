package com.example.project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.project.BeforeYouStartActivity.scenes;




public class SceneActivity extends AppCompatActivity {
    private Button allOn;
    private Button allOf;
    private TextView tv;
    private Button button_save;
    private boolean save = false;
    private ConstraintLayout constraintLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(SceneActivity.this));
        SceneAdapter adapter = new SceneAdapter(scenes);
        recyclerView.setAdapter(adapter);

//        allOn = findViewById(R.id.fullOn);
//        tv = findViewById(R.id.textView);
//        allOn.setOnClickListener(this);
//        allOf = findViewById(R.id.BlackOut);
//        allOf.setOnClickListener(this);
//        button_save = findViewById(R.id.save);
//        button_save.setOnClickListener(this);
//        constraintLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);
//        for (int i = 0; i < scenes.size(); i++){
//            Button b = new Button(getApplicationContext());
//            b.setText(scenes.get(i).getName());
//            b.setLayoutParams(
//                    new ConstraintLayout.LayoutParams(
//                            ConstraintLayout.LayoutParams.MATCH_PARENT,
//                            ConstraintLayout.LayoutParams.WRAP_CONTENT)
//            );
//            b.setId(R.id.scenes.get(i).getName());
//            b.setOnClickListener(this);
//            constraintLayout.addView(b);
//            b.setOnClickListener(this);
//
//        }


    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!save){
           // s.send("all", 0);
        }

    }

}