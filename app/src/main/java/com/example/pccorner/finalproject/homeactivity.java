package com.example.pccorner.finalproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

public class homeactivity extends AppCompatActivity {
Button login;
EditText name,password;
CheckBox showPass;
ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeAllComponent();
        addEventTomMycomponent();
    }
    private void addEventTomMycomponent() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String nameStr=name.getText().toString();
                        String passwordStr=password.getText().toString();
                        backgroundWorker backgroundWorker=new backgroundWorker(homeactivity.this);
                        backgroundWorker.execute("LOGIN",nameStr,passwordStr);
                       /* if(isCorrect(nameStr,passwordStr)) {
                            Intent intent = new Intent(homeactivity.this, mainManagerLayout.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        }*/
                    }
                },2000);
            }
        });
        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPass.isChecked()) {
                    password.setInputType(1);
                }else{
                    password.setTransformationMethod(new PasswordTransformationMethod());
                  //  password.setInputType(1);
                }
            }
        });
    }
    private boolean isCorrect(String name,String pass){
        boolean bool=true;
        return bool;
    }
    private void initalizeAllComponent() {
        login=findViewById(R.id.login);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        showPass=findViewById(R.id.showPass);
        progress=findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
    }
}
