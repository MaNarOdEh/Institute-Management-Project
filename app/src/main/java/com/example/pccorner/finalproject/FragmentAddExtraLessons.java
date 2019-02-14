package com.example.pccorner.finalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class FragmentAddExtraLessons extends Fragment {

    View view;
    AutoCompleteTextView auto_studentName,auto_employeeName;
    MultiAutoCompleteTextView multi_materials;
    EditText edit_date,edit_time_start,edit_time_end,edit_money;
    Button btn_save;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    TextInputLayout inputLayout_studentName,inputLayout_employeeName,inputLayout_date,
            inputLayout_timeStart,inputLayout_timeEnd,inputLayout_materials,
            inputLayout_money;
    RadioButton radio_paid;

    ArrayList<String>name_sudent;
    ArrayList<String>name_employee;
    ArrayList<String>name_materials;
    HashMap<String,String>id_name_student;
    HashMap<String,String>id_name_employee;
    HashMap<String,String>id_name_materials;
    boolean isEnd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fragment_add_extra_lessons,container,false);
        initalizeAllComponent();
        makeNecessaryEvent();
        makeNecessaryDialog();
        return view;
    }

    private void initalizeAllComponent() {
        auto_studentName=view.findViewById(R.id.auto_studentName);
        auto_employeeName=view.findViewById(R.id.auto_employeeName);
        multi_materials=view.findViewById(R.id.multi_materials);
        edit_date=view.findViewById(R.id.edit_date);
        edit_time_start=view.findViewById(R.id.edit_time_start);
        edit_time_end=view.findViewById(R.id.edit_time_end);
        edit_money=view.findViewById(R.id.edit_money);
        btn_save=view.findViewById(R.id.btn_save);
        radio_paid=view.findViewById(R.id.radio_paid);

        inputLayout_studentName=view.findViewById(R.id.inputLayout_studentName);
        inputLayout_employeeName=view.findViewById(R.id.inputLayout_employeeName);
        inputLayout_date=view.findViewById(R.id.inputLayout_date);
        inputLayout_timeStart=view.findViewById(R.id.inputLayout_timeStart);
        inputLayout_timeEnd=view.findViewById(R.id.inputLayout_timeEnd);
        inputLayout_materials=view.findViewById(R.id.inputLayout_materials);
        inputLayout_money=view.findViewById(R.id.inputLayout_money);

        setAdapterForStudentName();
        setAdapterForEmployeeName();
        setMaterials();
    }
    private void setAdapterForStudentName(){
        name_sudent=mainManagerLayout.getStudentName();
        id_name_student=mainManagerLayout.getStudentId();
        ArrayAdapter<String> a=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,name_sudent);
        auto_studentName.setAdapter(a);
        auto_studentName.setThreshold(1);

    }
    private void setAdapterForEmployeeName(){
        name_employee=mainManagerLayout.getEmployeeName();
        id_name_employee=mainManagerLayout.getEmployeeNameId();
        name_employee.add(mainManagerLayout.username);
        id_name_employee.put(mainManagerLayout.username,mainManagerLayout.id);
        ArrayAdapter<String>a=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,name_employee);
        auto_employeeName.setAdapter(a);
        auto_employeeName.setThreshold(1);
    }
    private void setMaterials(){
        name_materials=mainManagerLayout.getMaterialName();
      //  id_materials=mainManagerLayout.getMaterialsId();
        ArrayAdapter<String>a=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,name_materials);
        multi_materials.setAdapter(a);
        multi_materials.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multi_materials.setThreshold(1);
    }
    private void makeNecessaryEvent() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameOfStudent,employeeName,materials,date,time_start,time_end,money, padi_not;
                nameOfStudent=auto_studentName.getText().toString();
                employeeName=auto_employeeName.getText().toString();
                materials=multi_materials.getText().toString();
                date=edit_date.getText().toString();
                time_start=edit_time_start.getText().toString();
                time_end=edit_time_end.getText().toString();
                money=edit_money.getText().toString();
                padi_not=radio_paid.isChecked()?"1":"0";

                if(!isCorrect(nameOfStudent,employeeName,materials,date,money,time_start,time_end)){
                    Snackbar snackbar = Snackbar.make(v, "Wrong Input!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    backgroundWorker backgroundWorker=new backgroundWorker(getActivity());
                    nameOfStudent=id_name_student.get(nameOfStudent);
                    employeeName=id_name_employee.get(employeeName);
                    materials=materials.trim();
                    if(materials.charAt(materials.length()-1)==','){
                        materials=materials.substring(0,materials.length()-1);

                    }
                    Toast.makeText(getActivity(), nameOfStudent+"   "+employeeName+materials+" "+date+"  "+time_start+"  "+
                            time_end+"  "+money+" "+padi_not, Toast.LENGTH_SHORT).show();
                    backgroundWorker.execute("ADDEXTRALESSONS",nameOfStudent,employeeName,materials,date,time_start,time_end,money,padi_not);
                    auto_employeeName.setText("");
                    auto_studentName.setText("");
                    multi_materials.setText("");
                    edit_date.setText("");
                    edit_money.setText("");
                    edit_time_end.setText("");
                    edit_time_end.setText("");
                    radio_paid.setChecked(false);
                    Snackbar snackbar = Snackbar.make(v, "Added Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        edit_time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnd=true;
                timePickerDialog.show();
            }
        });
        edit_time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnd=false;
                timePickerDialog.show();
            }
        });
    }
    private boolean isCorrect(String nameStudent,String employeeName,String materials,String date,String monye,String time_start,String time_end){
        boolean is_correct=true;
        if(nameStudent.isEmpty()){
            inputLayout_studentName.setError("* Required");
            inputLayout_studentName.setErrorEnabled(true);
            is_correct=false;
        }else{
            if(name_sudent.indexOf(nameStudent)<0){
                inputLayout_studentName.setError("Wrong Student Name");
                inputLayout_studentName.setErrorEnabled(true);
                is_correct=false;
            }else {
                inputLayout_studentName.setErrorEnabled(false);
            }
        }
        if(employeeName.isEmpty()){
            inputLayout_employeeName.setError("* Required");
            inputLayout_employeeName.setErrorEnabled(true);
            is_correct=false;
        }else{
            if(name_employee.indexOf(employeeName)<0){
                inputLayout_employeeName.setError("Wrong Employee Name");
                inputLayout_employeeName.setErrorEnabled(true);
                is_correct=false;
            }else{
                inputLayout_employeeName.setErrorEnabled(false);
            }

        }if(materials.isEmpty()){
            inputLayout_materials.setError("* Required");
            inputLayout_materials.setErrorEnabled(true);
            is_correct=false;
        }else{
            if(!get_valid_materials(materials)){
                inputLayout_materials.setError("Wrong Materials");
                inputLayout_materials.setErrorEnabled(true);
                is_correct=false;
            }else{
                inputLayout_materials.setErrorEnabled(false);
            }
        }if(date.isEmpty()){
            inputLayout_date.setError(" * Required");
            inputLayout_date.setErrorEnabled(true);
            is_correct=false;
        }else{
            inputLayout_date.setErrorEnabled(false);
        }if(time_end.isEmpty()){
            inputLayout_timeEnd.setError(" * Required");
            inputLayout_timeEnd.setErrorEnabled(true);
        }else{
            inputLayout_timeEnd.setErrorEnabled(false);
        }if(time_start.isEmpty()){
            inputLayout_timeStart.setError(" * Required");
            inputLayout_timeStart.setErrorEnabled(true);
        }else{
            inputLayout_timeStart.setErrorEnabled(false);
        }if(monye.isEmpty()){
            inputLayout_money.setError(" * Required");
            inputLayout_money.setErrorEnabled(true);
        }else{
            Integer integer=Integer.parseInt(monye);
            if(integer<=0){
                inputLayout_money.setError("Wrong Input!");
                inputLayout_money.setErrorEnabled(true);
            }else {
                inputLayout_money.setErrorEnabled(false);
            }
        }
        return is_correct;
    }


    private void makeNecessaryDialog(){
        Calendar c=Calendar.getInstance();
        final int year=c.get(Calendar.YEAR),month=c.get(Calendar.MONTH),day=c.get(Calendar.DAY_OF_MONTH);
         datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edit_date.setText(year+"-"+(month+1)+"-"+dayOfMonth);
            }
        }, year, month, day);
         int hour=c.get(Calendar.HOUR),minutes=c.get(Calendar.MINUTE);
         timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(isEnd){
                    edit_time_end.setText(hourOfDay+":"+minute+":"+"00");
                }else{
                    edit_time_start.setText(hourOfDay+":"+minute+":"+"00");
                }

            }
        }, hour, minutes,false);
    }
    private boolean get_valid_materials(String materials){
        String m[]=materials.split(", ");
        for(int i=0;i<m.length;i++){
            Toast.makeText(getActivity(), m[i], Toast.LENGTH_SHORT).show();
            if(name_materials.indexOf(m[i])<0){
                return false;
            }
        }return true;
    }


}
