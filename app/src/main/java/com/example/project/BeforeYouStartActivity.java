package com.example.project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BeforeYouStartActivity extends AppCompatActivity implements View.OnClickListener {
    public static String ipAddress = "";
    public static Sender s;
    public static Scene allOn;
    public static Scene blackOut;
    public static ArrayList<Scene> scenes = new ArrayList<>();
    private EditText ipAddressWidget;
    private Button send;



    public Dialog wrongConnectionDialog;
    public static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_you_start);
        ipAddressWidget = findViewById(R.id.IpAddress);
        send = findViewById(R.id.buttonSend);
        send.setOnClickListener(this);
        allOn = new Scene("Включить всё", 512, 255);
        scenes.add(allOn);
        blackOut = new Scene("выключить всё", 512, 0);
        scenes.add(blackOut);


       mHandler = new Handler();




    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSend){
            ipAddress = ipAddressWidget.getText().toString();
            s = new Sender(ipAddress);
            s.start();
            checkConnection();
            Intent i = new Intent(BeforeYouStartActivity.this, MainActivity.class);
            startActivity(i);
        }

    }

    public void checkConnection(){
        try{
            s.send(new Lamp(0, 0));
        }
        catch (Exception e){
//            wrongConnectionDialog = new Dialog(BeforeYouStartActivity.this);
//            wrongConnectionDialog.setContentView(R.layout.dialog_window);
//
//            Button button_ok = wrongConnectionDialog.findViewById(R.id.button_ok);
//            button_ok.setOnClickListener(this);
//            TextView dialog_tv = wrongConnectionDialog.findViewById(R.id.dialogTextView);
//            dialog_tv.setText("Что-то не так с соединением. \n Проверьте IP-адрес");
        }

    }

}