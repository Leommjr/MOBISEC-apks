package com.example.justlisten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FlagReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        br = new FlagReceiver();
        IntentFilter filter = new IntentFilter("com.mobisec.intent.action.FLAG_ANNOUNCEMENT");
        registerReceiver(br, filter);

    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(br);
        super.onDestroy();
    }
}