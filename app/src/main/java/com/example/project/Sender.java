package com.example.project;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

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
    private boolean connectionCheckRequired = false;


    private boolean sendScene = false;
    private String jScene;
    private String jLamp;
    private String jFilterCommand;
    private HttpPost request;
    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    public static boolean connectionOk;


    public Sender(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void send(Lamp lamp) {
        commands.add(lamp);
    }
    public void send() {
        connectionCheckRequired = true;
    }

    public void send(FilterCommand fCommand){
        filterCommands.add(fCommand);
    }

    public void send(Scene scene) {
        sceneCommands.add(scene);
        sendScene = true;

    }







    public void sendData(String data, String node){
        try{
            HttpClient httpclient = new DefaultHttpClient();
            request = new HttpPost("http://" + ipAddress + ":5000/" + node);
            UrlEncodedFormEntity entity = null;
            params.add(new BasicNameValuePair("params", data));
            Log.i("data_to_send", data);
            entity = new UrlEncodedFormEntity(params, "UTF-8");
            request.setEntity(entity);
            HttpResponse response = httpclient.execute(request);
            params.clear();
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Log.i("log_tag", "its ok");

            Log.i("data_to_send", data);


        } catch (Exception e) {
            connectionOk = false;
            Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
            Errors.showConnectionErrorActivity();
        }
    }







    public void run() {


        while (!interrupted()) {

            if (commands.size() != 0) {

                if(connectionOk) {
                    Gson gson = new Gson();
                    jLamp = gson.toJson(commands.element());
                    commands.poll();
                    sendData(jLamp, "add"); // есть проблемка, если отключить сервер во время использования не вылезет ошибки в приложение, этого не было в закомент. версии. (она в репозитории предыдущая)
                }

            }

            if (sceneCommands.size() != 0) {
                if(connectionOk) {

                    Gson gson = new Gson();
                    Scene scene_to_send = sceneCommands.poll();
                    jScene = gson.toJson(scene_to_send);
                    sendData(jScene, "addScene");

                }


            }


            if (filterCommands.size() != 0) {
                if (connectionOk) {
                    Gson gson = new Gson();
                    jFilterCommand = gson.toJson(filterCommands.element());
                    filterCommands.poll();
                    sendData(jFilterCommand, "addSuperEffect");
                }


            }

            if (connectionCheckRequired) {
                try {
                    connectionCheckRequired = false;
                    HttpParams httpParameters = new BasicHttpParams();
                    // Set the timeout in milliseconds until a connection is established.
                    // The default value is zero, that means the timeout is not used.
                    int timeoutConnection = 500;
                    HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                    // Set the default socket timeout (SO_TIMEOUT)
                    // in milliseconds which is the timeout for waiting for data.
                    int timeoutSocket = 500;
                    HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

                    HttpClient httpclient = new DefaultHttpClient(httpParameters);
                    HttpGet request = new HttpGet("http://" + ipAddress + ":5000/add");
                    HttpResponse response = httpclient.execute(request);
                    //request.wait(1000000);
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    Log.i("log_tag", "its ok");
                    connectionOk = true;

                } catch (Exception e) {
                    connectionOk = false;
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                    Errors.showConnectionErrorActivity();
                }
            }


        }

    }

    public String getIpAddress() {
        return ipAddress;
    }
}


