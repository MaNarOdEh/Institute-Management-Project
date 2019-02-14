package com.example.pccorner.finalproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class additionalcource extends AppCompatActivity {

    TabLayout tablayout;
    ViewPager viewPager;
    Toolbar toolbar;
    public static ArrayList<AdditionalCourceDetails> additionalALLNONPAIDCourceDetails;
    public static ArrayList<AdditionalCourceDetails>all_aditional_lessons;
    public static HashMap<String,AdditionalCourceDetails>additionalCourceDetailsHashMap;
    public static HashMap<String,AdditionalCourceDetails>all_aditonal_lessonsHashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionalcource);
        initalizeAllComponent();
        makeNecessaryEvent();
    }
    public static void removeExtra(String id){
        if(additionalCourceDetailsHashMap!=null){
            if(additionalCourceDetailsHashMap.get(id)!=null)
                additionalCourceDetailsHashMap.remove(id);
        }if(all_aditonal_lessonsHashMap!=null){
            all_aditonal_lessonsHashMap.remove(id);
        }
    }
    public static void edit_extra(String id,String paid){
        if(additionalCourceDetailsHashMap!=null){
            if(additionalCourceDetailsHashMap.get(id)!=null)
                additionalCourceDetailsHashMap.get(id).setDoen_not(paid);
        }if(all_aditonal_lessonsHashMap!=null){
            all_aditonal_lessonsHashMap.get(id).setDoen_not(paid);
        }
    }
    public static void  setAllAditionalCource(String a){
        additionalALLNONPAIDCourceDetails=new ArrayList<>();
        additionalCourceDetailsHashMap=new HashMap<>();
        String course[]=a.split("%%%");
        for(int i=0;i<course.length;i++){
            String  details[]=course[i].split("&&");
            AdditionalCourceDetails aa=new AdditionalCourceDetails(details[0],details[3],details[4],details[5]
                    ,details[2],details[1],details[6],details[7]);
            aa.setId_student(details[8]);
            additionalALLNONPAIDCourceDetails.add(aa);
            additionalCourceDetailsHashMap.put(details[0],aa);

        }
    }
    public static void setALLessons(String a){
        all_aditional_lessons=new ArrayList<>();
        all_aditonal_lessonsHashMap=new HashMap<>();
        String course[]=a.split("%%%");
        for(int i=0;i<course.length;i++){
            String  details[]=course[i].split("&&");
            AdditionalCourceDetails aa=new AdditionalCourceDetails(details[0],details[3],details[4],details[5]
                    ,details[2],details[1],details[6],details[7]);
            aa.setId_student(details[8]);
            all_aditonal_lessonsHashMap.put(details[0],aa);
            all_aditional_lessons.add(aa);

        }
    }

    private void initalizeAllComponent() {

      tablayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewPager);
        toolbar=findViewById(R.id.toolbar);
        backEnd backEnd=new backEnd(additionalcource.this);
        backEnd.execute("ALL_EXTRA_LESSONS");
        backEnd backEnd2=new backEnd(additionalcource.this);
        backEnd2.execute("ALL_NON_PAID_LEESONS");
        setAttributeForTooLBar();
         setAttributeForViewPager();
        setAttributeForTabLayout();
    }

    private void setAttributeForTooLBar() {
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        setSupportActionBar(toolbar);
       // toolbar.setElevation(12.f);

    }

    private void setAttributeForViewPager() {
        AdapterForViewPager adapterForViewPager=new AdapterForViewPager(getSupportFragmentManager(),3);
        viewPager.setAdapter(adapterForViewPager);
        viewPager.setCurrentItem(0);
    }

    private void  setAttributeForTabLayout () {
        tablayout.addTab(tablayout.newTab().setText("Reg extra Lesson"));
        tablayout.addTab(tablayout.newTab().setText("Record extra Lesson"));
        tablayout.addTab(tablayout.newTab().setText("Not Paid Lesson"));
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       Intent intent;
       switch (item.getItemId()){
           case R.id.about_us:
               Toast.makeText(additionalcource.this,"about us!!",Toast.LENGTH_LONG).show();
               break;
           case R.id.setting_id:
               if(mainManagerLayout.arrayPersonalInfo==null){
                   Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                   backgroundWorker backgroundWorker=new backgroundWorker(additionalcource.this);
                   backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
               }else {
                   intent = new Intent(additionalcource.this, PeronalInfo.class);
                   startActivity(intent);
                   Toast.makeText(additionalcource.this, "setting", Toast.LENGTH_LONG).show();
               }
               break;
           case R.id.log_out_id:
               intent=new Intent(additionalcource.this,homeactivity.class);
               startActivity(intent);
               Toast.makeText(additionalcource.this,"log out",Toast.LENGTH_LONG).show();
               break;
           case R.id.main:
               intent=new Intent(additionalcource.this,mainManagerLayout.class);
               startActivity(intent);
               break;
       }
       return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private void makeNecessaryEvent() {
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
