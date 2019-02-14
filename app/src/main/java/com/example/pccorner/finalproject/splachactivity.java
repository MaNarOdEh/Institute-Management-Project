package com.example.pccorner.finalproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splachactivity extends AppCompatActivity {
    private static int SPLACH_TIME_OUT=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splachactivity);


       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(splachactivity.this,homeactivity.class);
                startActivity(intent);
            }
        },SPLACH_TIME_OUT);
    }
}
