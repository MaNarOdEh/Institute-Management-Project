package com.example.pccorner.finalproject;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class FragemntNotPaidExtraLessons extends Fragment {

    View view;
    LinearLayout linear_all_not_paid;
    TextView text_total_money;
    static ArrayList<AdditionalCourceDetails> additionalCourceDetails;
    HashMap<String,Student>id_Student;
    public static HashMap<String,AdditionalCourceDetails> additionalCourceDetailsHashMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragemnt_not_paid_extra_lessons, container, false);
        initalizeAllComponent();
        return view;
    }

    private void initalizeAllComponent() {
        linear_all_not_paid=view.findViewById(R.id.linear_all_not_paid);
        text_total_money=view.findViewById(R.id.text_total_money);
        additionalCourceDetails=additionalcource.additionalALLNONPAIDCourceDetails;
        if(additionalCourceDetails==null){
            //Toast.makeText(getActivity(), "Null!!", Toast.LENGTH_SHORT).show();
        }
        additionalCourceDetailsHashMap=additionalcource.additionalCourceDetailsHashMap;
        id_Student=mainManagerLayout.id_Student;
        setAllNonPaid();
    }
    private void setAllNonPaid(){
        int total_money=0;
        if(additionalCourceDetails!=null) {
           // for (int i = 0; i < additionalCourceDetailsHashMap.size(); i++) {
                Iterator iterator = additionalCourceDetailsHashMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry me2 = (Map.Entry) iterator.next();
                    //System.out.println("Key: "+me2.getKey() + " & Value: " + me2.getValue());
                    AdditionalCourceDetails a =(AdditionalCourceDetails) me2.getValue();
               // AdditionalCourceDetails a = additionalCourceDetailsHashMap.getValue();
                View vw = getLayoutInflater().inflate(R.layout.additional_student_layout, null);
                TextView txt_date = vw.findViewById(R.id.txt_date);
                TextView txt_time = vw.findViewById(R.id.txt_time);
                TextView txt_material = vw.findViewById(R.id.txt_material);
                TextView txt_employee = vw.findViewById(R.id.txt_employee);
                TextView value_money = vw.findViewById(R.id.value_money);
                TextView txt_delete = vw.findViewById(R.id.txt_delete);
                final RadioButton radio_paid = vw.findViewById(R.id.radio_paid);
                final TextView txt_id = vw.findViewById(R.id.txt_id);
                Button btn_save = vw.findViewById(R.id.btn_save);

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backgroundWorker backgroundWorker1 = new backgroundWorker(getActivity());
                        Toast.makeText(getActivity(), "" + txt_id.getText().toString(), Toast.LENGTH_SHORT).show();
                        backgroundWorker1.execute("SAVEADDITIONALCOURCE", txt_id.getText().toString(), radio_paid.isChecked() ? "1" : "0");
                       // additionalCourceDetailsHashMap.get(txt_id).setDoen_not(radio_paid.isChecked()?"1":"0");
                        additionalcource.edit_extra(txt_id.getText().toString(),radio_paid.isChecked() ? "1" : "0");
                        additionalCourceDetailsHashMap=additionalcource.additionalCourceDetailsHashMap;
                        Snackbar snackbar = Snackbar.make(v, "Updated Successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
                txt_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backgroundWorker backgroundWorker1 = new backgroundWorker(getActivity());
                        backgroundWorker1.execute("DELETEADDITIONALCOURCE", txt_id.getText().toString());
                        LinearLayout l = (LinearLayout) v.getParent().getParent().getParent();
                       // additionalCourceDetailsHashMap.remove(txt_id.getText().toString());
                        additionalCourceDetailsHashMap=additionalcource.additionalCourceDetailsHashMap;
                        additionalcource.removeExtra(txt_id.getText().toString());
                        Snackbar snackbar = Snackbar.make(v, "Deleted Successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        linear_all_not_paid.removeView(l);
                    }
                });

                txt_date.setText(a.getDate());
                txt_time.setText("From " + a.getTime_start() + " To " + a.getTime_end());
                txt_material.setText("Materials: " + a.getMaterials());

                  /*  text_name.setText(id_Student.get(a.id_student).getName());
                    text_phone.setText(id_Student.get(a.id_student).getPhone());*/
               // txt_employee.setText(a.get);
                    txt_employee.setText(id_Student.get(a.id_student).getName()+"  "+id_Student.get(a.id_student).getPhone());


                value_money.setText("Maoney " + a.getMoney());
                radio_paid.setChecked(a.getDoen_not().equals("0") ? false : true);
                txt_id.setText(a.getId());
                total_money=+Integer.parseInt(a.getMoney());

                linear_all_not_paid.addView(vw);
            }
            text_total_money.setText(total_money+"$");
        }
    }





}
