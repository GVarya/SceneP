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


    public Sender(String ipAdress) {
        this.ipAddress = ipAdress;
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

    public void run() {

        while (!interrupted()) {

            if (commands.size() != 0) {
                try {
                    if(connectionOk) {
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

                        Gson gson = new Gson();


                        request = new HttpPost("http://" + ipAddress + ":5000/add");
                        jLamp = gson.toJson(commands.element());
                        params.add(new BasicNameValuePair("params", jLamp));
                        Log.i("lamp", jLamp);
                        commands.poll();

                        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                        request.setEntity(entity);
                        HttpResponse response = httpclient.execute(request);
                        //request.wait(1000000);
                        params.clear();
                        in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        Log.i("log_tag", "its ok");
                    }

                } catch (Exception e) {
                    connectionOk = false;
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                    Errors.showConnectionErrorActivity();
                }
            }

            if (sceneCommands.size() != 0) {
                try {
                    if(connectionOk) {
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
                    }

                } catch (Exception e) {
                    connectionOk = false;
                    Errors.showConnectionErrorActivity();
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                }
            }



            if (filterCommands.size() != 0) {
                try {
                    if (connectionOk) {
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
                    }

                } catch (Exception e) {
                    connectionOk = false;
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                    Errors.showConnectionErrorActivity();
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


