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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Employee extends AppCompatActivity {
    ExpandableListView mylistEmployee;
    ExpandableListAdapter expandableListAdapter;
    ArrayList<EmployeeInfo>allEmployee;
    FloatingActionButton fab;
    Toolbar toolbar;
    List<String>name;
    int lastExpandListGroup=-1;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        initializeAllComponent();
        addEventToComponent();

    }

    private void addEventToComponent() {
        mylistEmployee.setAdapter(expandableListAdapter);
        mylistEmployee.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandListGroup!=-1&&groupPosition!=lastExpandListGroup){
                    mylistEmployee.collapseGroup(lastExpandListGroup);
                }
                lastExpandListGroup=groupPosition;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Employee.this,addEmployee.class);
                startActivity(intent);
            }
        });
    }

    private void initializeAllComponent() {
        TextView emptyView=findViewById(R.id.emptyView);
        frameLayout=findViewById(R.id.frameEmployee);
        toolbar=findViewById(R.id.toolbar);
        mylistEmployee=findViewById(R.id.mylistEmployee);
        setAttributeTolBar();
        allEmployee=mainManagerLayout.getAllEmployee();
        fab=findViewById(R.id.fab);
        mylistEmployee.setTextFilterEnabled(true);
        mylistEmployee.setEmptyView(emptyView);
        expandableListAdapter=new EmployeeExtedndableListAdapter(Employee.this,allEmployee,new HashMap<String, String>(),Employee.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(Employee.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(Employee.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(Employee.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(Employee.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                intent=new Intent(Employee.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(Employee.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                intent=new Intent(Employee.this,mainManagerLayout.class);
                startActivity(intent);
                break;
        }
        return true;
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
                ArrayList<EmployeeInfo>employeeInfos=new ArrayList<>();
                for(int i=0;i<allEmployee.size();i++){
                    if(allEmployee.get(i).getName().length()>=s.length()) {
                        if ((allEmployee.get(i).getName().subSequence(0, s.length())).equals(s)) {
                            employeeInfos.add(allEmployee.get(i));
                        }
                    }
                }
                EmployeeExtedndableListAdapter studentAdapter=new EmployeeExtedndableListAdapter(Employee.this,employeeInfos,new HashMap<String, String>(),Employee.this);
                mylistEmployee.setAdapter(studentAdapter);
                return true;
            }
        });

        return true;
    }

    private void setAttributeTolBar(){
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        setSupportActionBar(toolbar);
        toolbar.setElevation(12.f);
     //   mytollbar.setSubtitle("For Good Learning ");
    }


}
