package com.example.pccorner.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class mainManagerLayout extends AppCompatActivity {
         static HashMap<String,String[]>allMaterials;
         static ArrayList<String>nameMaterials;
         static ArrayList<String>materialsNumber;
         static ArrayList<String>id_materials;
         static String arrayPersonalInfo[];
         static ArrayList<Student>allStudent;
         static HashMap<String,Student>id_Student;
         static ArrayList<EmployeeInfo>allEmployee;
         static String username;
         static String id;
         static boolean log_in=false;
         TextView txt_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager_layout);
        inisalizeALLComponent();
        backEnd backgroundWorker=new backEnd(mainManagerLayout.this);
        backgroundWorker.execute("allStudent_allEmployee_allMaterials");
    }
    private void  inisalizeALLComponent(){
       txt_username=findViewById(R.id.txt_username);
       txt_username.setText(username);

    }
    public static  void setMaterials(String materials){
        String arr[]=materials.split("%%%");
        allMaterials=new HashMap<>();
        nameMaterials=new ArrayList();
        materialsNumber=new ArrayList();
        for(int i=0;i<arr.length;i++){
            String a[]=arr[i].split("&&");
            nameMaterials.add(a[1]);
            materialsNumber.add(a[2]);
            allMaterials.put(a[0],new String[]{a[1],a[2]});
        }
    }


    public static void setAllStudent(String stu){
        String arr[]=stu.split("%%%");
        allStudent=new ArrayList<>();
        id_Student=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            String in[]=arr[i].split("&&");
            Student s=new Student(in[1],in[2],in[3]);
            id_Student.put(in[0],s);
            s.setId(in[0]);
            allStudent.add(s);
        }
    }
    public static ArrayList<String>getStudentName(){
        ArrayList<String>nameStudent=new ArrayList<>();
        for(int i=0;i<allStudent.size();i++){
            nameStudent.add(allStudent.get(i).getName());
        }
        return nameStudent;
    }
    public static HashMap<String,String> getStudentId(){
        HashMap<String,String>id_student=new HashMap<>();
        for(int i=0;i<allStudent.size();i++){
            id_student.put(allStudent.get(i).getName(),allStudent.get(i).getId());

        }return id_student;
    }


    public static void setALLEmployee(String emp){
        String arr[]=emp.split("%%%");
        allEmployee=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            String in[]=arr[i].split("&&");
            EmployeeInfo e=new EmployeeInfo(in[1],in[2],in[0]);
            allEmployee.add(e);
        }
    }
    public static ArrayList<String>getEmployeeName(){
        ArrayList<String>nameEmployee=new ArrayList<>();
        for(int i=0;i<allEmployee.size();i++){
            nameEmployee.add(allEmployee.get(i).getName());
        }
        return nameEmployee;
    }

   public static  HashMap<String,String> getEmployeeNameId(){
       HashMap<String,String> employeeNameId=new HashMap<>();
       for(int i=0;i<allEmployee.size();i++){
           employeeNameId.put(allEmployee.get(i).getName(),allEmployee.get(i).getId());
       }
       return employeeNameId;
   }



    public static ArrayList<EmployeeInfo> getAllEmployee(){
        return allEmployee;
    }
    public static ArrayList<String> getMaterialName(){
        return nameMaterials;
    }
    public static ArrayList<String>getMaterialNumber(){
        return materialsNumber;
    }
    public static ArrayList<String>getMaterialsId(){
        return id_materials;
    }



    public void go(View view) {
        if(!log_in){
            Intent intent=new Intent(mainManagerLayout.this,homeactivity.class);
            startActivity(intent);
        }
        Intent intent=null;
       if(view.getId()==R.id.student) {
           if(allStudent==null){
               Toast.makeText(this, "Null Student Array", Toast.LENGTH_SHORT).show();
               backgroundWorker backgroundWorker=new backgroundWorker(mainManagerLayout.this);
               backgroundWorker.execute("GETALLSTUDENT");
           }else {
               intent = new Intent(mainManagerLayout.this, student_layout.class);
               startActivity(intent);
           }

        }else if(view.getId()==R.id.employee){
             if(allEmployee==null){
                 backgroundWorker backgroundWorker=new backgroundWorker(mainManagerLayout.this);
                 backgroundWorker.execute("GETALLEMPLOYE");
             }else {
                 intent = new Intent(mainManagerLayout.this, Employee.class);
                 startActivity(intent);
             }
        }
        else if(view.getId()==R.id.materials){
           if(allMaterials==null){
               Toast.makeText(this, "Null Materials Array", Toast.LENGTH_SHORT).show();
               backgroundWorker backgroundWorker=new backgroundWorker(mainManagerLayout.this);
               backgroundWorker.execute("ALLMATERIALS");
           }else{
               intent=new Intent(mainManagerLayout.this,Materials.class);
               startActivity(intent);}
        }else if(view.getId()==R.id.attendencd){
            intent=new Intent(mainManagerLayout.this,Attendence.class);
           startActivity(intent);

        }else if(view.getId()==R.id.financial){
             intent=new Intent(mainManagerLayout.this,financial.class);
           startActivity(intent);
        }else if(view.getId()==R.id.additional){
            intent=new Intent(mainManagerLayout.this,additionalcource.class);
           startActivity(intent);

        }else if(view.getId()==R.id.personalInfo){
           if(arrayPersonalInfo==null){
               Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
               backgroundWorker backgroundWorker=new backgroundWorker(mainManagerLayout.this);
                backgroundWorker.execute("PERSONALINFO",username);
           }
            else{
               intent=new Intent(mainManagerLayout.this,PeronalInfo.class);
               intent.putExtra("personal",arrayPersonalInfo);
               startActivity(intent);
           }

        }else if(view.getId()==R.id.logOut){
           log_in=false;
           intent=new Intent(mainManagerLayout.this,homeactivity.class);
           startActivity(intent);

        }

    }
    public static void  setPersonaInfo(String s){
        arrayPersonalInfo=s.split("&&");
    }
    public static String[] getArrayPersonalInfo(){
        return  arrayPersonalInfo;
    }
}
