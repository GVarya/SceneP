package com.example.project;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.project.BeforeYouStartActivity.s;
import static com.example.project.Sender.connectionOk;

public class Reader extends Thread{
    private String ipAddress = s.getIpAddress();

    private boolean getNames = false;
    public static ArrayList<String> names = new ArrayList<>();
    private JSONObject json;
    private JSONArray jNames;


    public void getFilersName(){
        getNames = true;
    }

    public void run(){
        while(!interrupted()){

            if(getNames){
                try {
                    if (!connectionOk){
                        Thread.sleep(1000);
                    }
                    if (connectionOk) {
                        names.clear();
                        HttpParams httpParameters = new BasicHttpParams();
                        // Set the timeout in milliseconds until a connection is established.
                        // The default value is zero, that means the timeout is not used.
                        int timeoutConnection = 500;
                        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
                        // Set the default socket timeout (SO_TIMEOUT)
                        // in milliseconds which is the timeout for waiting for data.
                        int timeoutSocket = 500;
                        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
                        HttpPost request = new HttpPost("http://" + ipAddress + ":5000/getFiltersName");
                        HttpClient httpclient = new DefaultHttpClient(httpParameters);
                        HttpResponse response = httpclient.execute(request);
                        String sNames = EntityUtils.toString(response.getEntity());
                        json = new JSONObject(sNames);
                        jNames = json.getJSONArray("names");
                        if (jNames != null) {
                            for (int i = 0; i < jNames.length(); i++) {
                                names.add(jNames.getString(i));
                            }
                        }
                        Log.i("response", String.valueOf(names));
                    }
                } catch (IOException | JSONException e) {
                    connectionOk = false;
                    Log.e("log_tag", "Error in http connection " + e.toString() + Arrays.toString(e.getStackTrace()));
                    Errors.showConnectionErrorActivity();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getNames = false;
        }
    }

}
