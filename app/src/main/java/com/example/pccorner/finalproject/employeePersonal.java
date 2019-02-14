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
import android.widget.Toast;

import java.util.Calendar;


public class employeePersonal extends Fragment {
    View view;
    EditText employee_password,employee_bd,employee_phone,employee_name;
    Button saveChanges;
    String name_employee,id_employee,phone_employee;
    static String pass_employee="",birth_employee="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view=inflater.inflate(R.layout.fragment_employee_personal,container,false);
         Bundle b=getArguments();
         if(b!=null){
             name_employee=b.getString("nameEmployee");
             id_employee=b.getString("idEmployee");
             phone_employee=b.getString("phoneNumber");

         }
         initalizeAllcomponent();
         makeEventForComponent();
        return view;
    }

    private void makeEventForComponent() {
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=employee_name.getText().toString()
                        ,phone=employee_phone.getText().toString(),
                        password=employee_password.getText().toString(),
                        bd=employee_bd.getText().toString();
                if(!name.isEmpty()&&!phone.isEmpty()&&!password.isEmpty()&&!bd.isEmpty()){
                    backEnd backEnd=new backEnd(getActivity());
                    backEnd.execute("updateEmployee",id_employee,name,password,phone,bd);
                    Snackbar snackbar = Snackbar.make(v, "updated Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                   // Toast.makeText(getActivity(),"it should save your updates",Toast.LENGTH_LONG).show();
                }else{
                    Snackbar snackbar = Snackbar.make(v, "Input All Required Input Plz!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                  //  Toast.makeText(getActivity(),"Input All Required Input Plz",Toast.LENGTH_LONG).show();
                }
            }
        });
        employee_bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c= Calendar.getInstance();
                int year=c.get(Calendar.YEAR),month=c.get(Calendar.MONTH),day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        employee_bd.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void initalizeAllcomponent() {
        employee_name=view.findViewById(R.id.employee_name);
        employee_phone=view.findViewById(R.id.employee_phone);
        employee_password=view.findViewById(R.id.employee_password);
        employee_bd=view.findViewById(R.id.employee_bd);
        saveChanges=view.findViewById(R.id.saveChanges);

        //employee_name.setText(name_employee);
        //employee_phone.setText(phone_employee);
        //employee_password.setText(pass_employee);
        //employee_bd.setText(birth_employee);
    }


}
