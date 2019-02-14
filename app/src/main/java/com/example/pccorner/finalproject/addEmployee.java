package com.example.pccorner.finalproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class addEmployee extends AppCompatActivity {

    Button btn_addEmplyeePersonalInfo;
    EditText edit_employeePassord,edit_employeeBirthDate
            ,edit_EmployeePhoneNumber,edit_EmployeeFullName;
    TextInputLayout inputLayoutpassordEmployee,inputLayoutEmployeeDate,
            inputLayoutEmployeePhoneNumber,inputLayoutEmployeeFullName;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        initalizeAllComponent();
        makeNecessaryEvent();
    }

    private void initalizeAllComponent() {
        btn_addEmplyeePersonalInfo=findViewById(R.id.btn_addEmplyeePersonalInfo);
        edit_employeePassord=findViewById(R.id.edit_employeePassord);
        edit_employeeBirthDate=findViewById(R.id.edit_employeeBirthDate);
        edit_EmployeePhoneNumber=findViewById(R.id.edit_EmployeePhoneNumber);
        edit_EmployeeFullName=findViewById(R.id.edit_EmployeeFullName);
        inputLayoutpassordEmployee=findViewById(R.id.inputLayoutpassordEmployee);
        inputLayoutEmployeeDate=findViewById(R.id.inputLayoutEmployeeDate);
        inputLayoutEmployeePhoneNumber=findViewById(R.id.inputLayoutEmployeePhoneNumber);
        inputLayoutEmployeeFullName=findViewById(R.id.inputLayoutEmployeeFullName);
        toolbar=findViewById(R.id.toolbar);
        setAttributeTolBar();
        generatPassword();
    }
    private void generatPassword(){
        int len=6;
        Random random=new Random();
        String pass="";
        String strong_pass="#$&%MnkoPOEVGT5698POQP^&!WERplpiqma;.";
        for(int i=0;i<len;i++){
            pass+=strong_pass.charAt(random.nextInt(strong_pass.length()-1));
        }
        edit_employeePassord.setText(pass);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(addEmployee.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(addEmployee.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(addEmployee.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(addEmployee.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                intent=new Intent(addEmployee.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(addEmployee.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                intent=new Intent(addEmployee.this,mainManagerLayout.class);
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

    private void setAttributeTolBar(){
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        setSupportActionBar(toolbar);
        toolbar.setElevation(12.f);
        //   mytollbar.setSubtitle("For Good Learning ");
    }

    private void makeNecessaryEvent() {
        edit_employeeBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR),month=c.get(Calendar.MONTH),day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(addEmployee.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edit_employeeBirthDate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();;
            }
        });
        btn_addEmplyeePersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,phone,passord,bd;
                name=edit_EmployeeFullName.getText().toString();
                phone=edit_EmployeePhoneNumber.getText().toString();
                passord=edit_employeePassord.getText().toString();
                bd=edit_employeeBirthDate.getText().toString();
                if(isCorrecr(name,phone,passord,bd)){
                    backEnd backEnd=new backEnd(addEmployee.this);
                    backEnd.execute("employee_add",name,passord,phone,bd);
                    Snackbar snackbar = Snackbar.make(v, "Added Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    Snackbar snackbar = Snackbar.make(v, "Non Valid Input!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
    private boolean isCorrecr(String name, String phone, String passord, String bd) {
        boolean is_correct=true;
        if(name.isEmpty()){
            is_correct=false;
            inputLayoutEmployeeFullName.setErrorEnabled(true);
            inputLayoutEmployeeFullName.setError("* Required");
        }else if(name.length()<3){
            is_correct=false;
            inputLayoutEmployeeFullName.setErrorEnabled(true);
            inputLayoutEmployeeFullName.setError("Your Name should be at least 3 char");
        }else {
            inputLayoutEmployeeFullName.setErrorEnabled(false);
        }if(phone.isEmpty()){
            is_correct=false;
            inputLayoutEmployeePhoneNumber.setErrorEnabled(true);
            inputLayoutEmployeePhoneNumber.setError("* Required");
        }else if(phone.length()<9){
            inputLayoutEmployeePhoneNumber.setErrorEnabled(true);
            inputLayoutEmployeePhoneNumber.setError("Your Phone ahould be at least 9 digit");
            is_correct=false;
        }else{
            inputLayoutEmployeePhoneNumber.setErrorEnabled(false);
        }if(passord.isEmpty()){
            inputLayoutpassordEmployee.setError("* Required");
            inputLayoutpassordEmployee.setErrorEnabled(true);
        }else if(passord.length()<6){
            inputLayoutpassordEmployee.setError("Your password should at least 6 char");
            inputLayoutpassordEmployee.setErrorEnabled(true);
        }else{
            inputLayoutpassordEmployee.setErrorEnabled(false);
        }if(bd.isEmpty()){
            inputLayoutEmployeeDate.setError("* Required");
            inputLayoutEmployeeDate.setErrorEnabled(true);
        }else{
            inputLayoutEmployeeDate.setErrorEnabled(false);

        }

        return  is_correct;
    }
}
