package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class BeforeYouStartActivity extends AppCompatActivity implements View.OnClickListener {
    private static String ipAddress;
    private EditText ipAddressWidget;
    private Button send;

    public String getIpAddress() {
        return ipAddress;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_you_start);
        ipAddressWidget = findViewById(R.id.IpAddress);
        send = findViewById(R.id.buttonSend);
        send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSend){
            ipAddress = ipAddressWidget.getText().toString();
            Intent i = new Intent(BeforeYouStartActivity.this, MainActivity.class);
            startActivity(i);
        }
    }


}