package com.example.pccorner.finalproject;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class financial extends AppCompatActivity {

    Toolbar toolbar_layout;
    NavigationView navigation_header_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);
        initalizeAllComponent();
    }

    private void initalizeAllComponent() {
        toolbar_layout=findViewById(R.id.toolbar);
        navigation_header_container=findViewById(R.id.navigation_header_container);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(financial.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(financial.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(financial.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(financial.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                intent=new Intent(financial.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(financial.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                intent=new Intent(financial.this,mainManagerLayout.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
