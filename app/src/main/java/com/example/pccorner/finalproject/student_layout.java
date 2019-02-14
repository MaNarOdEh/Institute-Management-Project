package com.example.pccorner.finalproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class student_layout extends AppCompatActivity {
     ListView studentList;
     public static ArrayList<Student> student;
     studentAdapter adapter;
     TextView emptyView;
     Toolbar toolbar;
    FloatingActionButton fab;
    static ArrayList<String>materialsName;
    static ArrayList<String>materialsID;
    static ArrayList<String>allEmployee;
    static ArrayList<String>allEmployeeID;
    static HashMap<String,String>materialNameId;
    static HashMap<String,String>employeeNameId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlayout);
        initalizeAllComponet();
        makeEvent();
    }


    private void makeEvent() {
        studentList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent=new Intent(student_layout.this,profileStudent.class);
                TextView txt_name=view.findViewById(R.id.name);
                TextView txt_phone=view.findViewById(R.id.phone);
                TextView txt_grade=view.findViewById(R.id.grade);
                String name=txt_name.getText().toString();
                String  phone=txt_phone.getText().toString();
                String  grade=txt_grade.getText().toString();
                profileStudent.student_name=name;
                profileStudent.student_grade=grade;
                profileStudent.student_phone=phone;
                profileStudent.position=position;

                backgroundWorker backgroundWorker=new backgroundWorker(student_layout.this);
                backgroundWorker.execute("STUDENTIDPROFILE",name);
               // Toast.makeText(student_layout.this, name.getText(), Toast.LENGTH_SHORT).show();
                //intent.putExtra("ID",1);
                //startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundWorker backgroundWorker=new backgroundWorker(student_layout.this);
                backgroundWorker.execute("GETALLMATERIALSEMPLOUEENAME");
            }
        });
    }
    public static void setEmployeeMaterials(String s){
        String st[] = s.split("%%%");
        for (int i = 0; i < st.length; i++) {
            String material_employee[] = st[i].split("&&");
            for (int j = 0; j < material_employee.length; j++) {
                String name_id[] = material_employee[j].split("!!");
                for (int k = 0; k < 1; k++) {
                    if (i == 0) {
                        materialNameId.put(name_id[1],name_id[0]);
                        materialsID.add(name_id[0]);
                        materialsName.add(name_id[1]);
                    } else {
                        employeeNameId.put(name_id[1],name_id[0]);
                        allEmployeeID.add(name_id[0]);
                        allEmployee.add(name_id[1]);

                    }
                }
            }
        }

    }
    private void setToolbarProperities(){
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        toolbar.setElevation(12.f);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem mSearch = menu.findItem(R.id.search_src_text);
        SearchView searchView=(SearchView) mSearch.getActionView();
        searchView.setQueryHint("Search..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Student>students=new ArrayList<>();
                for(int i=0;i<student.size();i++){
                    if(student.get(i).getName().length()>=s.length()) {
                        if ((student.get(i).getName().subSequence(0, s.length())).equals(s)) {
                            students.add(student.get(i));
                        }
                    }
                }
                studentAdapter studentAdapter=new studentAdapter(student_layout.this,students);
                studentList.setAdapter(studentAdapter);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(student_layout.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(student_layout.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(student_layout.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(student_layout.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                 intent=new Intent(student_layout.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(student_layout.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                 intent=new Intent(student_layout.this,mainManagerLayout.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void initalizeAllComponet() {
        fab =  findViewById(R.id.fab);
        studentList=findViewById(R.id.studentList);
        getAllStudent();
        adapter=new studentAdapter(this,student);
        studentList.setAdapter(adapter);
        toolbar=findViewById(R.id.toolbar);
        materialsID=new ArrayList<>();
        materialsName=new ArrayList<>();
        allEmployee=new ArrayList<>();
        allEmployeeID=new ArrayList<>();
        materialNameId=new HashMap<>();
        employeeNameId=new HashMap<>();
        emptyView=findViewById(R.id.emptyView);
        studentList.setEmptyView(emptyView);
        studentList.setTextFilterEnabled(true);
        setToolbarProperities();

    }
    private void getAllStudent(){
        student=mainManagerLayout.allStudent;
    }
}
