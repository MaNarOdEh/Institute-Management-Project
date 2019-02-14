package com.example.pccorner.finalproject;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class finanical_student_profile extends Fragment {

    View view;
    TextView text_total_money,total_non_paid;
    LinearLayout layout_financial_layout;
    Button btn_bb;
    public  HashMap<String,MonthlyCourceDetails> monthlycourceDetails;
    boolean end;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_finanical_student_profile, container, false);
        initalizeAllcomponent();
        makeNecessaryEvent();
        makePicker();
        Toast.makeText(getContext(), profileStudent.student_id, Toast.LENGTH_SHORT).show();
        return view;
    }
    public  void setAllFinancialStudent(String s){
        monthlycourceDetails=new HashMap<>();
        String financial[]=s.split("%%%");
        for(int i=0;i<financial.length;i++){
            String  details[]=financial[i].split("&&");
            monthlycourceDetails.put(details[0],new MonthlyCourceDetails(
                    details[0],details[1],details[2],details[3],details[4],details[5],details[6],details[7],details[8]));

        }

    }
    private void initalizeAllcomponent(){
        text_total_money=view.findViewById(R.id.text_total_money);
        total_non_paid=view.findViewById(R.id.total_non_paid);
        layout_financial_layout=view.findViewById(R.id.layout_financial_layout);
        btn_bb=view.findViewById(R.id.btn_bb);
        monthlycourceDetails=new HashMap<>();


    }
    private void makeNecessaryEvent(){
        btn_bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monthlycourceDetails=profileStudent.monthlycourceDetails;
                if(monthlycourceDetails!=null) {
                    Iterator iterator = monthlycourceDetails.entrySet().iterator();
                    int money = 0;
                    int total_paid = 0;
                    while (iterator.hasNext()) {
                        Map.Entry me = (Map.Entry) iterator.next();
                        View vi = getLayoutInflater().inflate(R.layout.financialstudemt, null);

                        TextView text_close = vi.findViewById(R.id.text_close);
                        final TextView id_studyInfo = vi.findViewById(R.id.id_studyInfo);
                        TextView txt_date = vi.findViewById(R.id.txt_date);
                        TextView txt_time = vi.findViewById(R.id.txt_time);
                        TextView txt_material = vi.findViewById(R.id.txt_material);
                        TextView txt_status = vi.findViewById(R.id.txt_status);
                        final RadioButton done_not = vi.findViewById(R.id.done_not);
                        Button saveChanges = vi.findViewById(R.id.saveChanges);
                        Button btn_renew = vi.findViewById(R.id.btn_renew);
                        final EditText end_date = vi.findViewById(R.id.end_date);
                        final EditText start_date = vi.findViewById(R.id.start_date);
                        final Button save_btn = vi.findViewById(R.id.save_btn);
                        final LinearLayout linear_renew=vi.findViewById(R.id.linear_renew);
                        saveChanges.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TextView id_studyInfo = v.findViewById(R.id.id_studyInfo);
                                backgroundWorker backgroundWorker=new backgroundWorker(getActivity());
                                backgroundWorker.execute("update_study_info",id_studyInfo.getText().toString(),done_not.isChecked()?"1":"0");
                                Toast.makeText(getActivity(), id_studyInfo.getText()+"  "+done_not.isChecked(), Toast.LENGTH_SHORT).show();
                                Snackbar snackbar = Snackbar.make(view, "Changes Saved", Snackbar.LENGTH_LONG);
                                snackbar.show();

                            }
                        });

                        final MonthlyCourceDetails m = (MonthlyCourceDetails) me.getValue();

                        text_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                 backgroundWorker backgroundWorker=new backgroundWorker(getActivity());
                                 backgroundWorker.execute("DEL_STUDY",id_studyInfo.getText().toString());
                                LinearLayout l = (LinearLayout) v.getParent().getParent().getParent();
                                layout_financial_layout.removeView(l);
                                monthlycourceDetails.remove(id_studyInfo.getText().toString());
                                Toast.makeText(getActivity(), id_studyInfo.getText(), Toast.LENGTH_SHORT).show();
                                Snackbar snackbar = Snackbar.make(view, "DELETED Successfully", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        });
                        end_date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar calendar = Calendar.getInstance();
                                int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day = calendar.get(Calendar.DAY_OF_MONTH);
                                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        end_date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                    }
                                }, year, month, day);
                                datePickerDialog.show();
                            }
                        });
                        start_date.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar calendar = Calendar.getInstance();
                                int year = calendar.get(Calendar.YEAR), month = calendar.get(Calendar.MONTH), day = calendar.get(Calendar.DAY_OF_MONTH);
                                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        start_date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                    }
                                }, year, month, day);
                                datePickerDialog.show();
                            }
                        });
                        save_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                backgroundWorker backgroundWorker=new backgroundWorker(getActivity());
                                backgroundWorker.execute("renew",id_studyInfo.getText().toString(),start_date.getText().toString(),end_date.getText().toString());
                                Snackbar snackbar = Snackbar.make(view,
                                        "Saved Successfully"+end_date.getText()+start_date.getText(), Snackbar.LENGTH_LONG);
                                MonthlyCourceDetails mm=monthlycourceDetails.get(id_studyInfo.getText().toString());
                                mm.setFrom_date(start_date.getText().toString());
                                mm.setTo_date(end_date.getText().toString());
                                monthlycourceDetails.put(id_studyInfo.getText().toString(),mm);
                                snackbar.show();

                            }
                        });
                        btn_renew.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                linear_renew.setVisibility(View.VISIBLE);


                            }
                        });
                        id_studyInfo.setText(me.getKey() + "");
                        txt_date.setText("From " + m.getFrom_date() + " To " + m.getTo_date());
                        txt_time.setText(m.getMoney() + "$");
                        txt_material.setText("Materials:" + m.getMaterials());
                        txt_status.setText("Status:" + ((m.getDone_not()).equals("0") ? "Not Yet" : "YEH"));
                        done_not.setChecked((m.getDone_not()).equals("0") ? false : true);
                        money += Integer.parseInt(m.getMoney());
                        total_paid += ((m.getDone_not()).equals("1") ? Integer.parseInt(m.getMoney()) : 0);
                        layout_financial_layout.addView(vi);


                    }
                    text_total_money.setText(money + "");
                    total_non_paid.setText((money - total_paid) + "");
                }

            }
        });

    }
    private void makePicker(){


    }

}
