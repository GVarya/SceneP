package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;

public class SceneActivity extends AppCompatActivity implements View.OnClickListener {
    Button allOn;
    Button allOf;
    String URL_WEBSERVICE = "http://192.168.1.129:5000/add?channel=all&intensity=225";
    URL url;
    TextView tv;
    BufferedReader in = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        allOn = findViewById(R.id.button);
        tv = findViewById(R.id.textView);
        allOn.setOnClickListener(this);
        allOf = findViewById(R.id.alloff);
        allOf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.button){
            tv.setText("Yes!");
            NewThread t = new NewThread("all", 200);
            t.start();
        }
        if(v.getId() == R.id.alloff){
            tv.setText("Yes!");
            NewThread t = new NewThread("blackout", 0);
            t.start();
        }
    }

    public class NewThread extends Thread{
        String command;
        String blackout;
        int intensity;
        public NewThread(String command,int intensity){
            this.command = command;
            this.intensity = intensity;
        }
        public void run() {

//                StringBuilder urlParametros = new StringBuilder();
//                urlParametros.append("channel").append(all);
//                urlParametros.append("intensity").append(intensity);
//
//                try {
//                    url = new URL(URL_WEBSERVICE);
//
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.setDoOutput(true);
//
//                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
//                    wr.writeBytes(urlParametros.toString());
//                    wr.flush();
//                    wr.close();
//
//
//                } catch (ProtocolException e) {
//                    e.printStackTrace();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//        try {
//            URL url = null;
//            String response = null;
//            String parameters = "channel=all&intensity=100";
//            url = new URL("http://192.168.1.129:5000/add");
//            //url = new URL("http://time100.ru/api.php");
//            //create the connection
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
//            //set the request method to GET
//            connection.setRequestMethod("GET");
//            //get the output stream from the connection you created
//            OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
//            //write your data to the ouputstream
//            request.write(parameters);
//            request.flush();
//            request.close();
//            String line = "";
//            //create your inputsream
//            InputStreamReader isr = new InputStreamReader(
//                    connection.getInputStream());
//            //read in the data from input stream, this can be done a variety of ways
//            BufferedReader reader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            while ((line = reader.readLine()) != null) {
//               sb.append(line + "\n");
//            }
//            //get the string version of the response data
//            response = sb.toString();
//            //do what you want with the data now
//
//            //always remember to close your input and output streams
//            isr.close();
//            reader.close();
//        } catch (IOException e) {
//            Log.e("HTTP GET:", e.toString());
//        }


//            HttpClient httpclient = new DefaultHttpClient();
//            HttpResponse response;
//            String responseString = null;
//            try {
//                response = httpclient.execute(new HttpGet("http://192.168.129:5000"));
//                StatusLine statusLine = response.getStatusLine();
//                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    response.getEntity().writeTo(out);
//                    responseString = out.toString();
//                    out.close();
//                } else{
//                    //Closes the connection.
//                    response.getEntity().getContent().close();
//                    throw new IOException(statusLine.getReasonPhrase());
//                }
//            } catch (ClientProtocolException e) {
//                //TODO Handle problems..
//            } catch (IOException e) {
//                //TODO Handle problems..


            try{
                HttpClient httpclient = new DefaultHttpClient();

                HttpGet request = new HttpGet();
                URI website = new URI("http://192.168.1.129:5000/add?channel=" + command + "&intensity=" + intensity);
                request.setURI(website);
                HttpResponse response = httpclient.execute(request);
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                Log.i("log_tag", "its ok");

            }catch(Exception e){
                Log.e("log_tag", "Error in http connection "+e.toString());
            }
            }
        }


}