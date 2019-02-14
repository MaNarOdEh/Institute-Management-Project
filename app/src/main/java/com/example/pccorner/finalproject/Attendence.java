package com.example.pccorner.finalproject;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Attendence extends AppCompatActivity {

    CalendarView calender_view;
    CardView card;
    TextView txt_date;
    Toolbar toolbar;
    Button btn_next,btn_save;
    LinearLayout linear_attend;
    EditText edit_time_start,edit_time_end;
    AutoCompleteTextView namestudent;
    ArrayList<String>nameSt;
    TimePickerDialog timePickerDialog;
    boolean end;
    TextInputLayout inputLayout_name,inputLayout_timeStart,inputLayout_time_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        intalizeAllComponent();
        makeNecessaryEvent();
    }

    private void makeNecessaryEvent() {
        calender_view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                   txt_date.setText(year+"-"+month+"-"+dayOfMonth);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((btn_next.getText().toString()).equalsIgnoreCase("next")) {
                    if(!(txt_date.getText().toString()).isEmpty()) {
                        linear_attend.setVisibility(View.VISIBLE);
                        card.setVisibility(View.GONE);
                        btn_next.setText("back");
                    }else{
                        Toast.makeText(Attendence.this,"Choose Date First!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    linear_attend.setVisibility(View.GONE);
                    card.setVisibility(View.VISIBLE);
                    btn_next.setText("next");
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_student=namestudent.getText().toString(),tfirst=edit_time_start.getText().toString(),
                        tSecond=edit_time_end.getText().toString();
                if(isCorrect(name_student,tfirst,tSecond)){
                    backEnd backgroundWorker=new backEnd(Attendence.this);
                    backgroundWorker.execute("add_attendence",name_student,txt_date.getText().toString(),tfirst,tSecond,mainManagerLayout.id);
                    Snackbar snackbar = Snackbar.make(v, "Added Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    namestudent.setText("");
                    edit_time_end.setText("");
                    edit_time_start.setText("");

                }else{
                    Snackbar snackbar = Snackbar.make(v, "Wrong Input", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });
        edit_time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=true;
                timePickerDialog.show();

            }
        });edit_time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=false;
                timePickerDialog.show();

            }
        });
    }

    private void intalizeAllComponent() {
        card=findViewById(R.id.card);
        btn_save=findViewById(R.id.btn_save);
        inputLayout_name=findViewById(R.id.inputLayout_name);
        inputLayout_timeStart=findViewById(R.id.inputLayout_timeStart);
        inputLayout_time_end=findViewById(R.id.inputLayout_time_end);
        edit_time_start=findViewById(R.id.edit_time_start);
        edit_time_end=findViewById(R.id.edit_time_end);
        namestudent=findViewById(R.id.namestudent);
        nameSt=mainManagerLayout.getStudentName();
        calender_view=findViewById(R.id.calender_view);
        txt_date=findViewById(R.id.txt_date);
        toolbar=findViewById(R.id.toolbar);
        btn_next=findViewById(R.id.btn_next);
        linear_attend=findViewById(R.id.linear_attend);
        setAttributeTolBar();
        setAdapteSt();
        Calendar c=Calendar.getInstance();
        final int hour=c.get(Calendar.HOUR),minutes=c.get(Calendar.MINUTE);
        timePickerDialog=new TimePickerDialog(Attendence.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(end){
                    edit_time_end.setText(hour+":"+minute);
                }else{
                    edit_time_start.setText(hour+":"+minute);
                }

            }
        },hour,minutes,false);
    }
    private void setAdapteSt(){
        ArrayAdapter adapter=new ArrayAdapter(Attendence.this,android.R.layout.simple_dropdown_item_1line,nameSt);
        namestudent.setAdapter(adapter);
        namestudent.setThreshold(1);
    }
    private void setAttributeTolBar(){
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        setSupportActionBar(toolbar);
        toolbar.setElevation(12.f);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(Attendence.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(Attendence.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(Attendence.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(Attendence.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                intent=new Intent(Attendence.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(Attendence.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                intent=new Intent(Attendence.this,mainManagerLayout.class);
                startActivity(intent);
                break;
        }
        return true;
    }
    private boolean isCorrect(String namS,String tim,String timE){
        boolean isCorrect=true;
        inputLayout_name.setErrorEnabled(false);
        inputLayout_time_end.setErrorEnabled(false);
        inputLayout_timeStart.setErrorEnabled(false);
        if(namS.isEmpty()){
            isCorrect=false;
            inputLayout_name.setErrorEnabled(true);
            inputLayout_name.setError("* Required");
        }else if(nameSt.indexOf(namS)<0){
            isCorrect=false;
            inputLayout_name.setErrorEnabled(true);
            inputLayout_name.setError("Wrong Name");
        }if(tim.isEmpty()){
            isCorrect=false;
            inputLayout_timeStart.setErrorEnabled(true);
            inputLayout_timeStart.setError("* Required");
        }if(timE.isEmpty()){
            isCorrect=false;
            inputLayout_time_end.setErrorEnabled(true);
            inputLayout_time_end.setError("* Required");
        }
        return isCorrect;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
