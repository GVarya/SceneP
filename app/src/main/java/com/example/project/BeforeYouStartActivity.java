package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BeforeYouStartActivity extends AppCompatActivity implements View.OnClickListener {
    public static String ipAddress = "";
    public static Sender s;
    public static ArrayList<Scene> scenes = new ArrayList<>();
    public static Scene currentPosition;

    private EditText ipAddressWidget;
    private Button send;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_you_start);
        ipAddressWidget = findViewById(R.id.IpAddress);
        send = findViewById(R.id.buttonSend);
        send.setOnClickListener(this);
        Scene allOn = new Scene("Включить всё", 24, 255);
        scenes.add(allOn);
        Scene blackOut = new Scene("выключить всё", 24, 0);
        scenes.add(blackOut);
        currentPosition = new Scene("currentPosition", 24, 0);




    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSend){
            ipAddress = ipAddressWidget.getText().toString();
            s = new Sender(ipAddress);
            s.start();
            Intent i = new Intent(BeforeYouStartActivity.this, MainActivity.class);
            startActivity(i);
        }
    }


}