package com.example.reachingout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Requesting data
        String url = "http://10.0.2.2:31337/flag";
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = getConnection(url);
            urlConnection = getConnection(url);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("answer=9&val1=3&oper=%2B&val2=6");
            writer.flush();
            writer.close();
            os.close();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String response = readStream(in);
            Log.e("MOBISEC", response);
        }catch (MalformedURLException e) {
            //never happens
        }catch (IOException e){
            Log.e("PROBLEMS", Log.getStackTraceString(e));
        }
        finally {
            urlConnection.disconnect();
        }
    }
    private HttpURLConnection getConnection(String urll) throws MalformedURLException, IOException {
        URL url = new URL(urll);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        return urlConnection;
    }
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}