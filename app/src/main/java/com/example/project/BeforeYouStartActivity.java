package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BeforeYouStartActivity extends AppCompatActivity implements View.OnClickListener {
    public static String ipAddress = "0";
    public static Sender s;
    public static Reader r;
    public static Scene allOn;
    public static Scene blackOut;
    public static ArrayList<Scene> scenes = new ArrayList<>();
    private EditText ipAddressWidget;
    private Button send;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_you_start);
        ipAddressWidget = findViewById(R.id.IpAddress);
        send = findViewById(R.id.buttonSend);
        send.setOnClickListener(this);
        if(scenes.size() == 0) {
            allOn = new Scene("Включить всё", 12, 255);
            scenes.add(allOn);
            blackOut = new Scene("выключить всё", 12, 0);
            scenes.add(blackOut);
        }





    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSend){
            ipAddress = ipAddressWidget.getText().toString();
            s = new Sender(ipAddress);
            s.start();
            r = new Reader();
            r.start();
            r.getFilersName();
            checkConnection();
            Intent i = new Intent(BeforeYouStartActivity.this, MainActivity.class);
            startActivity(i);
        }

    }

    public void checkConnection(){
        s.send(new Lamp(0, 0));



    }

}