package com.example.project;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;

public class Sender extends Thread{
    private String ipAddress;
    private BufferedReader in = null;
    private Queue<Lamp> commands = new ArrayDeque<>();


    public Sender(String ipAdress){
        this.ipAddress = ipAdress;
    }

    public void send(Lamp lamp){
        commands.add(lamp);
    }

    public void send(Scene scene){
        for(Lamp l: scene.getLamps()){
            commands.add(l);
        }

    }

    public void run() {

            while(!interrupted()) {

                if(commands.size() != 0){
                    try {
                        HttpClient httpclient = new DefaultHttpClient();

                        HttpGet request = new HttpGet();
                        URI website = new URI("http://" + ipAddress + ":5000/add?channel=" + commands.element().getCannal() + "&intensity=" + commands.element().getIntensity());
                        commands.poll();
                        request.setURI(website);
                        HttpResponse response = httpclient.execute(request);
                        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        Log.i("log_tag", "its ok");

                    } catch (Exception e) {
                        Log.e("log_tag", "Error in http connection " + e.toString());
                    }
                }
            }

    }
}


