package com.example.pccorner.finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class profileStudent extends AppCompatActivity {
    FrameLayout myframelayout;
    TextView title,txt_studentFullName,txt_studentPone;
    public static int position;
    public static String student_id="";
    public static String student_name="";
    public static String student_phone="";
    public static String student_grade="";
    public static String student_teacher="";
    public static String  student_id_teacher="";
    public static ArrayList<AdditionalCourceDetails>additionalCourceDetails;
    public static HashMap<String,MonthlyCourceDetails>monthlycourceDetails;
    public static HashMap<String,AttendStu>attendStuHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);
        initailizeAllCompoennt();
    }
     public static void  setAllAditionalCource(String a){
         additionalCourceDetails=new ArrayList<>();
        String course[]=a.split("%%%");
        // Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        for(int i=0;i<course.length;i++){
           String  details[]=course[i].split("&&");
           AdditionalCourceDetails aa=new AdditionalCourceDetails(details[0],details[3],details[4],details[5]
                   ,details[2],details[1],details[6],details[7]);
                additionalCourceDetails.add(aa);

        }
    }
    public static ArrayList<AdditionalCourceDetails> getAllAitionaleCourse(){
        return additionalCourceDetails;
    }
    public static void setAllFinancialStudent(String s){
        monthlycourceDetails=new HashMap<>();
        String financial[]=s.split("%%%");
        for(int i=0;i<financial.length;i++){
            String  details[]=financial[i].split("&&");
            monthlycourceDetails.put(details[0],new MonthlyCourceDetails(
                    details[0],details[1],details[2],details[3],details[4],details[5],details[6],details[7],details[8]));

        }

    }
    public static HashMap<String,MonthlyCourceDetails>getMonthlycourceDetails(){
        return monthlycourceDetails;
    }
    private void initailizeAllCompoennt() {
        attendStuHashMap=new HashMap<>();
        monthlycourceDetails=new HashMap<>();
        additionalCourceDetails=new ArrayList<>();
        myframelayout=findViewById(R.id.myframelayout);
        title=findViewById(R.id.title);
        txt_studentFullName=findViewById(R.id.txt_studentFullName);
        txt_studentFullName.setText(student_name);
        txt_studentPone=findViewById(R.id.txt_studentPone);
        txt_studentPone.setText(student_phone);
        Fragment fragment=new personalStudentFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.myframelayout,fragment);
        ft.commit();
    }
    public static void setDate(String s){
        String arr[]=s.split("&&");
        student_id=arr[0];
        student_teacher=arr[1];
        student_id_teacher=arr[2];

    }
    public void   setName(String name,String phone){
        txt_studentFullName.setText(name);
        txt_studentPone.setText(phone);
    }
    public static void setAttendenceStudent( String s){
        attendStuHashMap=new HashMap<>();
        String arr[]=s.split("%%%");
        for(int i=0;i<arr.length;i++){
            String el[]=arr[i].split("&&");
            AttendStu a=new AttendStu(el[0],el[1],el[3],el[4],el[2]);
            attendStuHashMap.put(el[0],a);
        }



    }
    public static HashMap<String,AttendStu>getAttendStuHashMap(){
        return attendStuHashMap;
    }


    public void showFragment(View view) {
        Fragment fragment=new personalStudentFragment();
        int id=view.getId();
        if(id==R.id.personal){
             fragment=new personalStudentFragment();
            title.setText("Personal Info");
        }else if(id==R.id.attendence){
            backgroundWorker backgroundWorker=new backgroundWorker(profileStudent.this);
            backgroundWorker.execute("attendnce_student",profileStudent.student_id);
            fragment=new attendenceStudent();
            title.setText("Attendence");
        }else if(id==R.id.additional){
            fragment=new additional_student_profile();
            title.setText("Additional Course");
        }else{
            backgroundWorker backgroundWorker=new backgroundWorker(profileStudent.this);
            backgroundWorker.execute("get_all_study_info",profileStudent.student_id);
            fragment=new finanical_student_profile();
            title.setText("Financial");
        }
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.myframelayout,fragment);
        ft.commit();
    }
}
