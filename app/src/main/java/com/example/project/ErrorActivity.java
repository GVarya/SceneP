package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity implements View.OnClickListener{
    Button ok;
    TextView error_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ok = findViewById(R.id.button_agree);
        error_message = findViewById(R.id.error_message);
        error_message.setText("Ой, ой проблемы с соединением. \n Проверьте IP-адрес");
        ok.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(ErrorActivity.this, BeforeYouStartActivity.class);
        startActivity(i);
    }
}