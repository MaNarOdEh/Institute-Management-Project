package com.example.pccorner.finalproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class addstudent extends AppCompatActivity {

    Toolbar toolbar;
    Button next_add_material,btn_addMaterial,btn_addStudentPersonalInfo;
    EditText studentFullName,studentPhoneNumber,studentClass,studentBirthDate,edit_start_date,edit_end_date
            ,edit_start_time,edit_end_time,studentSecondPhoneNumber,edit_money;
    RadioButton female,male;
    TextInputLayout inputLayoutStudentClass,inputLayoutStudentPhoneNumber,inputLayoutStudentFullName,inputLayoutStudentDate
            ,inputLayoutStudentSecondPhoneNumber,inputLayoutSuper_Employee;
    MultiAutoCompleteTextView multiAuto_material,multiAuto_days;

    AutoCompleteTextView multiAuto_employee,multiAuto_employeeSuper;
    LinearLayout layout_material;
    boolean birth,end;
    DatePickerDialog dt;
    TimePickerDialog timePickerDialog;
    ArrayList materials;
    ArrayList emplyeeName;
    CardView layout_materialInfo;
    static HashMap<String,String> materialNameId;
    static HashMap<String,String>employeeNameId;
    String name_student;
    Spinner blod;
    String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        initalizeAllWidgets();
        makeEventForWidgets();
    }

    private void makeEventForWidgets() {

        btn_addStudentPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addStudentValidate()) {

                    name_student = studentFullName.getText().toString();
                    String phone_number = studentPhoneNumber.getText().toString();
                    String anouther_phone_number = studentSecondPhoneNumber.getText().toString();
                    String birth_date = studentBirthDate.getText().toString();
                    String student_grade = studentClass.getText().toString();
                    String super_teacher = multiAuto_employeeSuper.getText().toString();
                    super_teacher = employeeNameId.get(super_teacher);
                    String gender = "";
                    if (male.isChecked()) {
                        gender = "0";

                    } else if (female.isChecked()) {
                        gender = "1";
                    }
                    backgroundWorker backgroundWorker = new backgroundWorker(addstudent.this);
                    Toast.makeText(addstudent.this, name_student + "   " + phone_number + "  " + anouther_phone_number + "\n" +
                            birth_date + "  " + student_grade + "  " + super_teacher, Toast.LENGTH_SHORT).show();
                    backgroundWorker.execute("Add_student_personal_info", name_student, phone_number, anouther_phone_number, gender,
                            birth_date, student_grade, super_teacher);
                    Snackbar snackbar = Snackbar.make(v, "Added Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }else{
                    Snackbar snackbar = Snackbar.make(v, "Wrong Input!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        next_add_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_student==null||name_student.isEmpty()){
                    Snackbar.make(v, "Add student first!!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else {
                    layout_materialInfo.setVisibility(View.VISIBLE);
                }

            }
        });
        studentBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birth=true;
                dt.show();
            }
        });
        edit_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=false;birth=false;
                dt.show();

            }
        });
       edit_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=true;birth=false;
                dt.show();
            }
        });
        edit_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=true;birth=false;
                timePickerDialog.show();
            }
        });
        edit_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                end=false;
                birth=false;
                timePickerDialog.show();
            }
        });
        btn_addMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeName = multiAuto_employee.getText().toString();
                String materials = multiAuto_material.getText().toString();
                String day = multiAuto_days.getText().toString();
                if (!employeeName.isEmpty() && !materials.isEmpty()) {
                    employeeName = employeeName.trim();
                    materials = materials.trim();
                    day = day.trim();
                    String from_time = edit_start_time.getText().toString();
                    String to_time = edit_start_time.getText().toString();
                    String from_date = edit_start_date.getText().toString();
                    String to_date = edit_start_date.getText().toString();
                    String money = edit_money.getText().toString();
                    if (day.isEmpty() || from_date.isEmpty() || to_date.isEmpty() || from_time.isEmpty() || to_time.isEmpty() || money.isEmpty()) {
                        Snackbar.make(v, "Input All Field Plz!!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        if (materials.charAt(materials.length() - 1) == ',') {
                            materials = materials.substring(0, materials.length() - 1);
                        } else {
                            Toast.makeText(addstudent.this, "WOW  " + materials.charAt(materials.length() - 1), Toast.LENGTH_SHORT).show();
                        }
                        if (day.charAt(day.length() - 1) == ',') {
                            day = day.substring(0, day.length() - 1);
                        } else {
                            Toast.makeText(addstudent.this, "WOW  " + day.charAt(day.length() - 1), Toast.LENGTH_SHORT).show();
                        }
                        if (emplyeeName.contains(employeeName)) {

                            if (isAllCorrectMaterials(materials)) {
                                View view = getLayoutInflater().inflate(R.layout.material_employee_addsudent, null);
                                ImageView imageView = view.findViewById(R.id.img_close);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        LinearLayout l = (LinearLayout) v.getParent().getParent().getParent();
                                        layout_material.removeView(l);

                                    }
                                });
                                TextView text_employeeName = view.findViewById(R.id.text_employeeName);
                                TextView text_materials = view.findViewById(R.id.text_materials);
                                TextView text_days = view.findViewById(R.id.text_days);
                                TextView text_time = view.findViewById(R.id.text_time);
                                TextView text_date = view.findViewById(R.id.text_date);
                                TextView text_money = view.findViewById(R.id.text_money);

                                text_money.setText(money);
                                text_time.setText("From " + from_time + " To " + to_time);
                                text_employeeName.setText(employeeName);
                                text_materials.setText(materials);
                                text_days.setText(day);
                                text_date.setText("From " + from_date + " To " + to_date);


                                backgroundWorker backgroundWorker = new backgroundWorker(addstudent.this);
                                Toast.makeText(addstudent.this, " " + name_student + "  " + from_time + "  " + to_time +
                                        from_date + "  " + to_date + "  \n" + day + " " + materials +
                                        employeeNameId.get(employeeName) + "  " + money + "0", Toast.LENGTH_SHORT).show();
                                backgroundWorker.execute("Add_Srudent_Study_Info", name_student, from_time, to_time, from_date, to_date, day, materials, employeeNameId.get(employeeName), money, "0");


                                layout_material.addView(view);
                                multiAuto_employee.setText("");
                                multiAuto_material.setText("");
                                multiAuto_days.setText("");
                                edit_end_time.setText("");
                                edit_start_date.setText("");
                                edit_end_date.setText("");
                                edit_end_time.setText("");
                                edit_money.setText("");
                                edit_start_time.setText("");


                            } else {
                                Snackbar.make(v, "Input Valid Materials", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }
                        } else {
                            Snackbar.make(v, "Input Valid Employee", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }}else{
                        Snackbar.make(v, "Input All Field Please", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }


        });

    }
    private boolean isAllCorrectMaterials(String material){
        boolean correct=true;
        String mat[]=material.split(", ");
        for(int i=0;i<mat.length;i++)
            if(!materials.contains(mat[i].trim())){
                Toast.makeText(addstudent.this,mat[i]+"  "+materials.get(0),Toast.LENGTH_SHORT).show();
                correct=false;
               break;
            }
        return correct;
    }


    private void initalizeAllWidgets() {
        toolbar=findViewById(R.id.toolbar);
        multiAuto_material=findViewById(R.id.multiAuto_material);
        next_add_material=findViewById(R.id.next_add_material);
        studentFullName=findViewById(R.id.studentFullName);
        studentPhoneNumber=findViewById(R.id.studentPhoneNumber);
        studentSecondPhoneNumber=findViewById(R.id.studentSecondPhoneNumber);
        studentClass=findViewById(R.id.studentClass);
        studentBirthDate=findViewById(R.id.studentBirthDate);
        inputLayoutStudentClass=findViewById(R.id.inputLayoutStudentClass);
        inputLayoutStudentPhoneNumber=findViewById(R.id.inputLayoutStudentPhoneNumber);
        inputLayoutStudentSecondPhoneNumber=findViewById(R.id.inputLayoutStudentSecondPhoneNumber);
        inputLayoutStudentFullName=findViewById(R.id.inputLayoutStudentFullName);
        inputLayoutStudentDate=findViewById(R.id.inputLayoutStudentDate);
        inputLayoutSuper_Employee=findViewById(R.id.inputLayoutSuper_Employee);
        edit_start_time=findViewById(R.id.edit_start_time);
        edit_end_time=findViewById(R.id.edit_end_time);
        edit_end_date=findViewById(R.id.edit_end_date);
        edit_start_date=findViewById(R.id.edit_start_date);
        edit_money=findViewById(R.id.edit_money);
        layout_material=findViewById(R.id.layout_material);
        btn_addMaterial=findViewById(R.id.btn_addMaterial);
        btn_addStudentPersonalInfo=findViewById(R.id.btn_addStudentPersonalInfo);
        multiAuto_employee=findViewById(R.id.multiAuto_employee);
        multiAuto_employeeSuper=findViewById(R.id.multiAuto_employeeSuper);
        multiAuto_days=findViewById(R.id.multiAuto_days);
        layout_materialInfo=findViewById(R.id.layout_materialInfo);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        setadapterForMultiAutoCompleteDays();
        setAdapterForMultiAutoCompleteEmployee();
        setAttributeTolBar();
        setAdapterForMultiAutocompleteMaterials();
        makeNecessaryDatePicker();
    }

    private void setadapterForMultiAutoCompleteDays(){
       ArrayList days=new ArrayList();
        days.add("Sunday");
        days.add("Monday");
        days.add("Tuesday");
        days.add("wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        ArrayAdapter arrayAdapter=new ArrayAdapter(addstudent.this,android.R.layout.simple_dropdown_item_1line,days);
        multiAuto_days.setAdapter(arrayAdapter);
        multiAuto_days.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAuto_days.setThreshold(1);
    }
    private void makeNecessaryDatePicker(){
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
         dt=new DatePickerDialog( addstudent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(birth)
                    studentBirthDate.setText(year+"-"+month+"-"+dayOfMonth);
                else  if(end)
                    edit_end_date.setText(year+"-"+month+"-"+dayOfMonth);
                else
                    edit_start_date.setText(year+"-"+month+"-"+dayOfMonth);

            }
        }, year, month, day);

         final int hour=c.get(Calendar.HOUR),minutes=c.get(Calendar.MINUTE);
        timePickerDialog=new TimePickerDialog(addstudent.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(end){
                    edit_end_time.setText(hourOfDay +":"+minute);
                }else{
                    edit_start_time.setText(hourOfDay+":"+minute);
                }
            }
        }, hour, minutes,false);

    }
    private void setAdapterForMultiAutocompleteMaterials(){
        materials=new ArrayList();
      materials=student_layout.materialsName;
      materialNameId=student_layout.materialNameId;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, materials);
        multiAuto_material.setAdapter(adapter);
        multiAuto_material.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        multiAuto_material.setThreshold(1);

    }
    private void setAdapterForMultiAutoCompleteEmployee(){
        emplyeeName=student_layout.allEmployee;
        employeeNameId=student_layout.employeeNameId;
        ArrayAdapter<String>a=new ArrayAdapter<String>(addstudent.this,android.R.layout.simple_list_item_1,emplyeeName);
        multiAuto_employee.setAdapter(a);
        multiAuto_employeeSuper.setAdapter(a);
        multiAuto_employeeSuper.setThreshold(1);
        multiAuto_employee.setThreshold(1);
    }
    private void setAttributeTolBar(){
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        setSupportActionBar(toolbar);
        toolbar.setElevation(12.f);
        ///toolbar.setSubtitle("For Good Learning ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(addstudent.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(addstudent.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(addstudent.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(addstudent.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                intent=new Intent(addstudent.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(addstudent.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                intent=new Intent(addstudent.this,mainManagerLayout.class);
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
    public boolean addStudentValidate(){
        boolean can_add=true;
        String name=studentFullName.getText().toString();
        String phone=studentPhoneNumber.getText().toString();
        String grade=studentClass.getText().toString();
        String super_teacher=multiAuto_employeeSuper.getText().toString();
        String birth_date=studentBirthDate.getText().toString();
        if(name.isEmpty()){
            can_add=false;
            inputLayoutStudentFullName.setErrorEnabled(true);
            inputLayoutStudentFullName.setError("Full name is Required");
        }
        else{
            if(name.length()<3){
                can_add=false;
                inputLayoutStudentFullName.setErrorEnabled(true);
                inputLayoutStudentFullName.setError("Your Name Should Be at Least 3 Digit!");
            }else {
                inputLayoutStudentFullName.setErrorEnabled(false);
            }
        }
        if(phone.isEmpty()){
            inputLayoutStudentPhoneNumber.setErrorEnabled(true);
            inputLayoutStudentPhoneNumber.setError("Phone is Required");
            can_add=false;
        }else{
            if(phone.length()<9) {
                can_add=false;
                inputLayoutStudentPhoneNumber.setErrorEnabled(true);
                inputLayoutStudentPhoneNumber.setError("Phone is should be at least 9 digit");
            }
            else {
                inputLayoutStudentPhoneNumber.setErrorEnabled(false);
            }
        }
        if(grade.isEmpty()){
            inputLayoutStudentClass.setErrorEnabled(true);
            inputLayoutStudentClass.setError("Grade is Required");
            can_add=false;
        }else{
            int g=Integer.parseInt(grade);
            if(g<1||g>12){
                can_add=false;
                inputLayoutStudentClass.setErrorEnabled(true);
                inputLayoutStudentClass.setError("Your grade should be between 1 and 12");
            }else {
                inputLayoutStudentClass.setErrorEnabled(false);
            }
        }

        if(!male.isChecked()&&!female.isChecked()){
            can_add=false;
        }
        if(super_teacher.isEmpty()){
            can_add=false;
            inputLayoutSuper_Employee.setErrorEnabled(true);
            inputLayoutSuper_Employee.setError("This is Required!!");
        }else {
            if (employeeNameId.get(super_teacher) == null) {
                can_add = false;
                inputLayoutSuper_Employee.setErrorEnabled(true);
                inputLayoutSuper_Employee.setError("Input Valid Employee");
            } else {
                inputLayoutSuper_Employee.setErrorEnabled(false);
            }
        }
        if(birth_date.isEmpty()){
            can_add=false;
            inputLayoutStudentDate.setError("this is Required!!");
            inputLayoutStudentDate.setErrorEnabled(true);
        }else{
            inputLayoutStudentDate.setErrorEnabled(false);
        }
        return can_add;
    }
    public boolean add_material(){
        boolean can_add=true;
        return can_add;
    }
}
