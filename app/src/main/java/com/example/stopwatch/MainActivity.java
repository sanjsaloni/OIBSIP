package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int sec=0;
    private boolean is_running;
    private boolean was_running;
//    int lapCount=0;
    private int hrs,min,secs;
//    TextView lap=findViewById(R.id.lap);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            sec=savedInstanceState.getInt("seconds");
            is_running=savedInstanceState.getBoolean("running",is_running);
            was_running=savedInstanceState.getBoolean("wasRunning",was_running);
        }
        running_Timer();

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("seconds",sec);
        outState.putBoolean("wasRunning",was_running);
        outState.putBoolean("running",is_running);
    }
    @Override
    protected void onPause(){
        super.onPause();
        was_running=is_running;
        Toast.makeText(this, "Timer on hold.", Toast.LENGTH_SHORT).show();
        is_running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(was_running){
            is_running=true;
        }
    }

    public void onClickStart(View view){
        is_running=true;
    }

    public void OnClickStop(View view){
        is_running=false;
        sec=0;
//        lapCount=0;
//        t_View.setText("0:00:00");
//        timelapse();
    }

    public void onClickHold(View view){
        is_running=false;
        Toast.makeText(this, "Timer is on hold.", Toast.LENGTH_SHORT).show();
//        sec=sec;
    }
    private void running_Timer() {
        final TextView t_View = findViewById(R.id.time_view);
        final Handler handle = new Handler();
        handle.post(new Runnable() {
            @Override
            public void run()
            {
                hrs = sec / 3600;
                min = (sec % 3600) / 60;
                secs = sec % 60;
                String time_t = String .format(Locale.getDefault(), "    %02d:%02d:%02d   ", hrs,min, secs);
                t_View.setText(time_t);
                if (is_running) {
                    sec++;
                }
                handle.postDelayed(this, 1000);
            }
        });
    }

//    public void timelapse(){
//        lapCount++;
//        String laps=String.format(Locale.getDefault(),"%02d:%02:%02d",hrs,min,secs);
//        if(lapCount>=10){
//        laps="Lap "+lapCount+" "+laps+" "+lap.getText();
//        }
//        Toast.makeText(this, "Lap "+lapCount, Toast.LENGTH_SHORT).show();
//        lap.setText(laps);
//    }
}