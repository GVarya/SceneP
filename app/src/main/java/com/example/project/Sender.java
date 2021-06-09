package com.example.project;

import android.app.Dialog;
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
import java.net.URI;
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
    private boolean sendScene = false;
    private URI website;
    private String jScene;
    private String jLamp;
    private HttpPost request;
    private List<NameValuePair> params = new ArrayList<NameValuePair>();


    public Dialog wrongConnectionDialog;

    public Sender(String ipAdress) {
        this.ipAddress = ipAdress;
    }

    public void send(Lamp lamp) {
        commands.add(lamp);
    }

    public void send(Scene scene) {
        sceneCommands.add(scene);
        sendScene = true;

    }

    public void run() {

        while (!interrupted()) {

            if (sceneCommands.size() != 0) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();

                    Gson gson = new Gson();

                    Scene scene_to_send = sceneCommands.poll();
                    request = new HttpPost("http://" + ipAddress + ":5000/addScene");
                    //jScene = gson.toJson(sceneCommands.element());
                    jScene = gson.toJson(scene_to_send);
                    params.add(new BasicNameValuePair("params", jScene));
                    Log.i("scene", jScene);
                    //params.add(new BasicNameValuePair("params", "QQQ"));

                    //website = new URI("http://" + ipAddress + ":5000/addScene?params=" + jScene);
                    //sendScene = false;
                    //sceneCommands.poll();

                    //request.setURI(website);
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                    request.setEntity(entity);
                    HttpResponse response = httpclient.execute(request);
                    params.clear();
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    Log.i("log_tag", "its ok");


                } catch (Exception e) {
//                        Errors er = new Errors();
//                        er.showConnectionError();
//                        handler.sendEmptyMessage(1);
                    Intent i = new Intent(MyApplication.getAppContext(), ErrorActivity.class);
                    MyApplication.getAppContext().startActivity(i);
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                }
            }

            if (commands.size() != 0) {
                try {
                    HttpClient httpclient = new DefaultHttpClient();

                    Gson gson = new Gson();


                    request = new HttpPost("http://" + ipAddress + ":5000/add");
                    jLamp = gson.toJson(commands.element());
                    params.add(new BasicNameValuePair("params", jLamp));
                    //params.add(new BasicNameValuePair("params", "aaaa"));

                    //website = new URI("http://" + ipAddress + ":5000/add?params=" + jLamp);
                    commands.poll();

                    //request.setURI(website);
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                    request.setEntity(entity);
                    HttpResponse response = httpclient.execute(request);
                    params.clear();
                    in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    Log.i("log_tag", "its ok");


                } catch (Exception e) {
//                        Errors er = new Errors();
//                        er.showConnectionError();
//                        Message msg = new Message();
//                        msg.what = 1;
//                        handler.sendMessage(msg);
//                        handler.sendEmptyMessage(1);

//                        mHandler.post(new Runnable() {
//                            public void run(){
//                                //Be sure to pass your Activity class, not the Thread
//                                wrongConnectionDialog = new Dialog(MyApplication.getAppContext());
//                                wrongConnectionDialog.setContentView(R.layout.dialog_window);
//
//                                Button button_ok = wrongConnectionDialog.findViewById(R.id.button_ok);
//                                //button_ok.setOnClickListener(this);
//                                TextView dialog_tv = wrongConnectionDialog.findViewById(R.id.dialogTextView);
//                                dialog_tv.setText("Что-то не так с соединением. \n Проверьте IP-адрес");
//                                wrongConnectionDialog.show();
//                                //... setup dialog and show
//                            }
//                        });MyApplicatin.}
                    Intent i = new Intent(MyApplication.getAppContext(), ErrorActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getAppContext().startActivity(i);
                }
            }

        }
    }
}


