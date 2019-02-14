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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class additional_student_profile extends Fragment {
    View view;
    EditText edit_start_date,edit_end_date;
    Button btn_search;
    DatePickerDialog datePickerDialog;
    LinearLayout layout_additionalCource;
    boolean end;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_additional_student_profile, container, false);
        initalizeAllcomponent();
        makeNecessaryEvent();
        makePicker();
        backgroundWorker backgroundWorker=new backgroundWorker(getActivity());
        backgroundWorker.execute("GET_ALL_ADDITIONAL_COURCE",profileStudent.student_id);
        return view;
    }
    private void initalizeAllcomponent(){

        edit_end_date=view.findViewById(R.id.edit_end_date);
        edit_start_date=view.findViewById(R.id.edit_start_date);
        btn_search=view.findViewById(R.id.btn_search);
        layout_additionalCource=view.findViewById(R.id.layout_additionalCource);

    }
    private void makeNecessaryEvent(){
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_additionalCource.removeAllViews();
                String date_first = edit_start_date.getText().toString();
                String date_last = edit_end_date.getText().toString();
               // String date_first=edit_start_date.getText().toString();
                //String date_last=edit_end_date.getText().toString();
               // Toast.makeText(getActivity(), date_first + "  " + date_last, Toast.LENGTH_SHORT).show();
               // final backgroundWorker backgroundWorker = new backgroundWorker(getActivity());
                if (date_first.isEmpty() &&date_last.isEmpty()) {
                    // backgroundWorker.execute("ADDITIONALCOURCTSTYDENT", start_date, end_date);
                    Snackbar snackbar = Snackbar.make(v, "Input Valid Date!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    ArrayList<AdditionalCourceDetails> additionalCourceDetails = profileStudent.getAllAitionaleCourse();
                    Toast.makeText(getActivity(), additionalCourceDetails.size()+"  ", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < additionalCourceDetails.size(); i++) {
                        AdditionalCourceDetails a = additionalCourceDetails.get(i);
                        String to_compare = a.getDate();
                        Toast.makeText(getActivity(), to_compare + "  " + to_compare.compareTo(date_first)
                                + "   " + to_compare.compareTo(date_last) + "  " + date_first + "  " + date_last, Toast.LENGTH_LONG).show();
                        if (to_compare.compareTo(date_first) >= 0 && to_compare.compareTo(date_last) <= 0) {
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
                                    Snackbar snackbar = Snackbar.make(v, "Deleted Successfully", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                    layout_additionalCource.removeView(l);
                                }
                            });
                            radio_paid.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (radio_paid.isChecked()) {
                                        radio_paid.setChecked(false);
                                    } else {
                                        radio_paid.setChecked(true);
                                    }
                                }
                            });

                            txt_date.setText(a.getDate());
                            txt_time.setText("From " + a.getTime_start() + " To " + a.getTime_end());
                            txt_material.setText("Materials: " + a.getMaterials());
                            txt_employee.setText(a.getTeacher());
                            value_money.setText("Maoney " + a.getMoney());
                            radio_paid.setChecked(a.getDoen_not().equals("0") ? false : true);
                            txt_id.setText(a.getId());

                            layout_additionalCource.addView(vw);
                        }
                    }
                }
            }
        });
        edit_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=false;
                datePickerDialog.show();

            }
        });
        edit_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=true;
                datePickerDialog.show();

            }
        });

    }
    private void makePicker(){
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR),month=c.get(Calendar.MONTH),day=c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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
                    //edit_end_date.setText(year+"-"+month+"-"+dayOfMonth);
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
                }
            }
        }, year, month, day);

    }

}
