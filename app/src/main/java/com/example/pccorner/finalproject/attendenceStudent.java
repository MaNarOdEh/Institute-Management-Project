package com.example.pccorner.finalproject;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class attendenceStudent extends Fragment {
    LinearLayout layout_attendencestuden;
    EditText edit_start_date,edit_end_date;
    Button btn_search;
    View view;
    boolean start;
    DatePickerDialog datePickerDialog;
    public  HashMap<String,AttendStu> attendStuHashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_attendence_student, container, false);
        initalizeAllcomponent();
        makeNecessaryEvent();
        makeNecessaryDatePicker();
        return view;
    }

    private void makeNecessaryDatePicker() {
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR),month=c.get(Calendar.MONTH),day=c.get(Calendar.MONTH);
        datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(start){
                    if(month+1<11) {
                        if(dayOfMonth<11) {
                            edit_start_date.setText(year + "-0" + (month + 1) + "-0" + dayOfMonth);
                        }else{
                            edit_start_date.setText(year + "-0" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                    else{
                        if(dayOfMonth<11) {
                            edit_start_date.setText(year + "-" + (month + 1) + "-0" + dayOfMonth);
                        }else{
                            edit_start_date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                }else{
                    if(month+1<11) {
                        if(dayOfMonth<11) {
                            edit_end_date.setText(year + "-0" + (month + 1) + "-0" + dayOfMonth);
                        }else{
                            edit_end_date.setText(year + "-0" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                    else{
                        if(dayOfMonth<11) {
                            edit_end_date.setText(year + "-" + (month + 1) + "-0" + dayOfMonth);
                        }else{
                            edit_end_date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        }
                    }
                  //  edit_end_date.setText(year+" - "+(month+1)+" - "+dayOfMonth);
                }
            }
        }, year, month, day);
    }

    private void initalizeAllcomponent(){
        layout_attendencestuden=view.findViewById(R.id.layout_attendencestuden);
        btn_search=view.findViewById(R.id.btn_search);
        edit_start_date=view.findViewById(R.id.edit_start_date);
        edit_end_date=view.findViewById(R.id.edit_end_date);

    }
    private void makeNecessaryEvent(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendStuHashMap=profileStudent.getAttendStuHashMap();
                if(attendStuHashMap!=null) {
                    layout_attendencestuden.removeAllViews();
                    String start_date, end_date;
                    start_date = edit_start_date.getText().toString();
                    end_date = edit_end_date.getText().toString();
                    if (start_date.isEmpty() &&end_date.isEmpty()) {
                        // backgroundWorker.execute("ADDITIONALCOURCTSTYDENT", start_date, end_date);
                        Snackbar snackbar = Snackbar.make(v, "Input Valid Date!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                    Iterator iterator = attendStuHashMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry me = (Map.Entry) iterator.next();
                        AttendStu a = (AttendStu) me.getValue();
                        String to_compare=a.getDate();
                        Toast.makeText(getActivity(), to_compare.compareTo(start_date)+"   "+to_compare.compareTo(end_date)
                                +"             "+to_compare, Toast.LENGTH_LONG).show();
                        if (to_compare.compareTo(start_date) >= 0 && to_compare.compareTo(end_date) <= 0) {
                            View vw = getLayoutInflater().inflate(R.layout.attendence_student_profile, null);
                            TextView txt_date = vw.findViewById(R.id.txt_date);
                            TextView txt_time = vw.findViewById(R.id.txt_time);
                            // TextView txt_material = vw.findViewById(R.id.txt_material);
                            TextView txt_employee = vw.findViewById(R.id.txt_employee);
                            txt_date.setText(a.getDate());
                            txt_time.setText("From " + a.getTime_start() + " To " + a.getTime_end());
                            // txt_material.setText("Materials:Java,PHP");
                            //  txt_employee.setText(mainManagerLayout.g);
                            layout_attendencestuden.addView(vw);
                        }
                    }
                }
                }
            }
        });
      edit_end_date.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              start=false;
              datePickerDialog.show();

          }
      });
      edit_start_date.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              start=true;
              datePickerDialog.show();

          }
      });
    }

}
