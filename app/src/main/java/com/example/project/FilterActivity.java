package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.project.Reader.names;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        RecyclerView recyclerView = findViewById(R.id.filter_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(FilterActivity.this));
        FilterAdapter adapter = new FilterAdapter(names);
        recyclerView.setAdapter(adapter);
    }
}