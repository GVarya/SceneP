package com.example.project;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class Sender extends Thread{
    private String command;
    private int intensity;
    private String IpAddress = new BeforeYouStartActivity().getIpAddress();
    private BufferedReader in = null;

    public Sender(String command,int intensity){
        this.command = command;
        this.intensity = intensity;
    }


    public void run() {
        try{
            HttpClient httpclient = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            URI website = new URI("http://"+ IpAddress +":5000/add?channel=" + command + "&intensity=" + intensity);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Log.i("log_tag", "its ok");

        }catch(Exception e){
            Log.e("log_tag", "Error in http connection "+e.toString());
        }
    }
}


