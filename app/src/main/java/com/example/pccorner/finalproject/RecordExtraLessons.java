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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class RecordExtraLessons extends Fragment {
    View view;
    LinearLayout myRecords;
    EditText edit_start_date,edit_end_date;
    Button btn_search;
    DatePickerDialog datePickerDialog;
    TextView text_total_money,text_remain_money;
    public   ArrayList<AdditionalCourceDetails> additionalALLCourceDetails;
    public static HashMap<String,AdditionalCourceDetails>all_aditonal_lessonsHashMap;

    HashMap<String,Student>id_Student;

    boolean end;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_record_extra_lessons,container,false);
        initalizeAllComponent();
        makeNecessaryEvent();
        makeRequiredDateDialog();
        return view;
    }

    private void makeRequiredDateDialog() {
        Calendar c=Calendar.getInstance();
        final int year=c.get(Calendar.YEAR),month=c.get(Calendar.MONTH),day=c.get(Calendar.DAY_OF_MONTH);
         datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(end){
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
                }else{
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
                    //edit_start_date.setText(year+ "-"+(month+1)+"-"+dayOfMonth);
                }

            }
        },year,month,day);
    }


    private void initalizeAllComponent() {
        myRecords=view.findViewById(R.id.myRecords);
        edit_start_date=view.findViewById(R.id.edit_start_date);
        edit_end_date=view.findViewById(R.id.edit_end_date);
        btn_search=view.findViewById(R.id.btn_search);
        text_remain_money=view.findViewById(R.id.text_remain_money);
        text_total_money=view.findViewById(R.id.text_total_money);
        additionalALLCourceDetails=additionalcource.all_aditional_lessons;
        all_aditonal_lessonsHashMap=additionalcource.all_aditonal_lessonsHashMap;
        id_Student=mainManagerLayout.id_Student;
        text_total_money.setText("");
        text_remain_money.setText("");
    }
    private void makeNecessaryEvent(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecords.removeAllViews();
                String date_first=edit_start_date.getText().toString();
                String date_last=edit_end_date.getText().toString();
                int total_money=0;
                int total_nonpaid=0;
                if(date_first.isEmpty()||date_last.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Input Valid Date!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                  //  for (int i = 0; i < all_aditonal_lessonsHashMap.size(); i++) {
                        Iterator iterator = all_aditonal_lessonsHashMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry me2 = (Map.Entry) iterator.next();
                            //System.out.println("Key: "+me2.getKey() + " & Value: " + me2.getValue());
                            AdditionalCourceDetails a =(AdditionalCourceDetails) me2.getValue();
                        String to_compare = a.getDate();
                       // if (to_compare.compareTo(date_first) >= 0 && to_compare.compareTo(date_last) <= 0) {
                            View vw = getLayoutInflater().inflate(R.layout.extradetails, null);
                            TextView text_close = vw.findViewById(R.id.text_close);
                            TextView text_date = vw.findViewById(R.id.text_date);
                            TextView text_name = vw.findViewById(R.id.text_name);
                            TextView text_phone = vw.findViewById(R.id.text_phone);
                            TextView text_total = vw.findViewById(R.id.text_total);
                            TextView text_status = vw.findViewById(R.id.text_status);
                            final TextView id_vv = vw.findViewById(R.id.id_vv);
                            text_close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LinearLayout l = (LinearLayout) v.getParent().getParent().getParent();
                                    myRecords.removeView(l);
                                    backgroundWorker backgroundWorker1 = new backgroundWorker(getActivity());
                                    backgroundWorker1.execute("DELETEADDITIONALCOURCE", id_vv.getText().toString());
                                   // all_aditonal_lessonsHashMap.remove(id_vv.getText().toString());
                                    additionalcource.removeExtra(id_vv.getText().toString());
                                    all_aditonal_lessonsHashMap=additionalcource.all_aditonal_lessonsHashMap;
                                    Snackbar snackbar = Snackbar.make(v, "Deleted Successfully", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            });
                            total_money += Integer.parseInt(a.getMoney());
                            if (a.getDoen_not().equals("0")) {
                                total_nonpaid += Integer.parseInt(a.getMoney());
                            }
                            id_vv.setText(a.getId());
                            text_date.setText(a.getDate());
                            text_name.setText(id_Student.get(a.id_student).getName());
                            text_phone.setText(id_Student.get(a.id_student).getPhone());

                            text_total.setText(a.getMoney() + "$");
                            text_status.setText("status: " + (a.doen_not.equals("0") ? "Not Yet" : "Done"));
                            myRecords.addView(vw);
                      //  }
                    }
                }
                else{

                    for(int i=0;i<additionalALLCourceDetails.size();i++){
                        AdditionalCourceDetails a=additionalALLCourceDetails.get(i);
                        String to_compare=a.getDate();
                        Toast.makeText(getActivity(), to_compare+"  "+to_compare.compareTo(date_first)
                                +"   "+to_compare.compareTo(date_last)+"  "+date_first+"  "+date_last, Toast.LENGTH_LONG).show();
                        if(to_compare.compareTo(date_first)>=0&&to_compare.compareTo(date_last)<=0){
                            View vw=getLayoutInflater().inflate(R.layout.extradetails,null);
                             TextView text_close=vw.findViewById(R.id.text_close);
                             TextView text_date=vw.findViewById(R.id.text_date);
                             TextView text_name=vw.findViewById(R.id.text_name);
                             TextView text_phone=vw.findViewById(R.id.text_phone);
                             TextView text_total=vw.findViewById(R.id.text_total);
                             TextView text_status=vw.findViewById(R.id.text_status);
                             final TextView id_vv=vw.findViewById(R.id.id_vv);
                             text_close.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     LinearLayout l=(LinearLayout)v.getParent().getParent().getParent();
                                     myRecords.removeView(l);
                                     backgroundWorker backgroundWorker1 = new backgroundWorker(getActivity());
                                     backgroundWorker1.execute("DELETEADDITIONALCOURCE", id_vv.getText().toString());
                                     Snackbar snackbar = Snackbar.make(v, "Deleted Successfully", Snackbar.LENGTH_LONG);
                                     snackbar.show();
                                 }
                             });
                             total_money+=Integer.parseInt(a.getMoney());
                             if(a.getDoen_not().equals("0")){
                                 total_nonpaid+=Integer.parseInt(a.getMoney());
                             }id_vv.setText(a.getId());
                             text_date.setText(a.getDate());
                             text_name.setText(id_Student.get(a.id_student).getName());
                             text_phone.setText(id_Student.get(a.id_student).getPhone());

                            text_total.setText(a.getMoney()+"$");
                            text_status.setText("status: "+(a.doen_not.equals("0")?"Not Yet":"Done"));
                            myRecords.addView(vw);
                        }
                    }
                }

                text_total_money.setText(total_money+"");
                text_remain_money.setText(total_nonpaid+"");
            }
        });
        edit_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=true;
                datePickerDialog.show();
            }
        });
        edit_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=false;
                datePickerDialog.show();
            }
        });
    }

}
