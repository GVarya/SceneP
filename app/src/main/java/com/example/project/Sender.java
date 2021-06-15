package com.example.project;

import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class Sender extends Thread {


    private String ipAddress;
    private BufferedReader in = null;
    private Queue<Lamp> commands = new ArrayDeque<>();
    private Queue<Scene> sceneCommands = new ArrayDeque<>();
    private Queue<FilterCommand> filterCommands = new ArrayDeque<>();

    private boolean sendScene = false;
    private String jScene;
    private String jLamp;
    private String jFilterCommand;
    private HttpPost request;
    private List<NameValuePair> params = new ArrayList<NameValuePair>();



    public Sender(String ipAdress) {
        this.ipAddress = ipAdress;
    }

    public void send(Lamp lamp) {
        commands.add(lamp);
    }

    public void send(FilterCommand fCommand){
        filterCommands.add(fCommand);
    }

    public void send(Scene scene) {
        sceneCommands.add(scene);
        sendScene = true;

    }

    public void run() {

        while (!interrupted()) {

            if (commands.size() != 0) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();

                    Gson gson = new Gson();


                    request = new HttpPost("http://" + ipAddress + ":5000/add");
                    jLamp = gson.toJson(commands.element());
                    params.add(new BasicNameValuePair("params", jLamp));
                    Log.i("lamp", jLamp);
                    commands.poll();

                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                    request.setEntity(entity);
                    HttpResponse response = httpclient.execute(request);
                    params.clear();
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    Log.i("log_tag", "its ok");


                } catch (Exception e) {
                    Intent i = new Intent(MyApplication.getAppContext(), ErrorActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getAppContext().startActivity(i);
                }
            }

            if (sceneCommands.size() != 0) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();

                    Gson gson = new Gson();

                    Scene scene_to_send = sceneCommands.poll();
                    request = new HttpPost("http://" + ipAddress + ":5000/addScene");
                    jScene = gson.toJson(scene_to_send);
                    params.add(new BasicNameValuePair("params", jScene));
                    Log.i("scene", jScene);

                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                    request.setEntity(entity);
                    HttpResponse response = httpclient.execute(request);
                    params.clear();
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    Log.i("log_tag", "its ok");


                } catch (Exception e) {
                    Intent i = new Intent(MyApplication.getAppContext(), ErrorActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getAppContext().startActivity(i);
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                }
            }



            if (filterCommands.size() != 0) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();

                    Gson gson = new Gson();


                    request = new HttpPost("http://" + ipAddress + ":5000/addSuperEffect");
                    jFilterCommand = gson.toJson(filterCommands.element());
                    params.add(new BasicNameValuePair("params", jFilterCommand));

                    filterCommands.poll();

                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                    request.setEntity(entity);
                    HttpResponse response = httpclient.execute(request);
                    params.clear();
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    Log.i("log_tag", "its ok");


                } catch (Exception e) {
                    Intent i = new Intent(MyApplication.getAppContext(), ErrorActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getAppContext().startActivity(i);
                }
            }


        }
    }

    public String getIpAddress() {
        return ipAddress;
    }
}


