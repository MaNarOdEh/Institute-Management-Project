package com.example.pccorner.finalproject;


import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PeronalInfo extends AppCompatActivity {

    EditText edit_name,edit_phonefirst,edit_phoneSecond,edit_facebook,edit_linkedin,edit_homtail;
    Button btn_save;
    String persoanlarray[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peronal_info);
        initalizeAllComponent();
        makeNecessaryEevent();

    }

    private void makeNecessaryEevent() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edit_name.getText().toString();
                String phone_f=edit_phonefirst.getText().toString();
                String phone_s=edit_phoneSecond.getText().toString();
                String facebook=edit_facebook.getText().toString();
                String linked=edit_linkedin.getText().toString();
                String hotmail=edit_homtail.getText().toString();
                if(name.isEmpty()||phone_f.isEmpty()||facebook.isEmpty()||phone_s.isEmpty()||linked.isEmpty()||hotmail.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, "Input All Field Please", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    Toast.makeText(PeronalInfo.this,mainManagerLayout.username+name+"  "+phone_f+"  "+phone_s+"  "+hotmail+"  "+facebook+"  "+linked, Toast.LENGTH_SHORT).show();
                  persoanlarray[0]=name;
                  persoanlarray[1]=phone_f;
                  persoanlarray[2]=phone_s;
                  persoanlarray[4]=facebook;
                  persoanlarray[5]=linked;
                  persoanlarray[3]=hotmail;
                    backgroundWorker backgroundWorker=new backgroundWorker(PeronalInfo.this);
                    backgroundWorker.execute("UPDATEPERSONAL",mainManagerLayout.username,name,phone_f,phone_s,hotmail,facebook,linked);
                 //   DBConnection.updateEmplpyeeName(name,name,phone_f,phone_s,facebook,linked,hotmail);
                    Snackbar snackbar = Snackbar.make(v, "Updated Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

    }


    private void initalizeAllComponent() {
        edit_name=findViewById(R.id.edit_name);
        edit_phonefirst=findViewById(R.id.edit_phonefirst);
        edit_phoneSecond=findViewById(R.id.edit_phoneSecond);
        edit_facebook=findViewById(R.id.edit_facebook);
        edit_linkedin=findViewById(R.id.edit_linkedin);
        edit_homtail=findViewById(R.id.edit_homtail);
        btn_save=findViewById(R.id.btn_save);
        persoanlarray=mainManagerLayout.getArrayPersonalInfo();
        setAllComponent();
    }
    private void setAllComponent(){
        edit_name.setText(persoanlarray[0]);
        edit_phonefirst.setText(persoanlarray[1]);
        edit_phoneSecond.setText(persoanlarray[2]);
        edit_facebook.setText(persoanlarray[4]);
        edit_linkedin.setText(persoanlarray[5]);
        edit_homtail.setText(persoanlarray[3]);
    }
}
