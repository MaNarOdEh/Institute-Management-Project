package com.example.pccorner.finalproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class personalStudentFragment extends Fragment {
    Button saveChanges;
    EditText name,phone,grade,teacher;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.personalstudent,container,false);
         initalizeAllComponent();
         setValueToAllcomponent();
         addEventToMyComponent();
        return view;
    }

    private void setValueToAllcomponent() {
    }

    private void addEventToMyComponent() {
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namestr=name.getText().toString();
                String phoneStr=phone.getText().toString();
                String gradeStr=grade.getText().toString();
                String teacherStr=teacher.getText().toString();
                if(isCorrect(namestr,phoneStr,gradeStr,teacherStr)){
                    backgroundWorker backgroundWorker=new backgroundWorker(getActivity());
                    backgroundWorker.execute("UPDATE_STUDENT_PERSONAL_INFO",profileStudent.student_id,namestr,gradeStr,phoneStr,teacherStr);
                    Toast.makeText(getActivity(), profileStudent.student_id, Toast.LENGTH_SHORT).show();
                   // (profileStudent)(getActivity()).setName(namestr,phoneStr);


                   //student_layout.student.remove(profileStudent.position);
                    Student student=new Student(namestr,phoneStr,gradeStr);
                    student.setId(profileStudent.student_id);
                    Student s;
                    for(int i=0;i<mainManagerLayout.allStudent.size();i++) {
                        s=mainManagerLayout.allStudent.get(i);
                        if(s.name.equals(profileStudent.student_name)){
                            mainManagerLayout.allStudent.remove(s);
                            mainManagerLayout.allStudent.add(student);
                            break;
                        }

                    }
                    student_layout.student=mainManagerLayout.allStudent;
                   // mainManagerLayout.allStudent.add(student);


                    ((profileStudent) getActivity()).setName(namestr,phoneStr);
                    Snackbar snackbar = Snackbar.make(v, "Updated Successfully", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }else{
                    Snackbar snackbar = Snackbar.make(v, "Wrong Input!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

        });
    }

    private boolean isCorrect(String namestr, String phoneStr, String gradeStr, String teacherStr) {
        boolean isCorrect=true;
        if(namestr.isEmpty()||phoneStr.isEmpty()||gradeStr.isEmpty()||teacherStr.isEmpty()){
            isCorrect=false;

        }int g=Integer.parseInt(gradeStr);
         if(namestr.length()<3){
            Toast.makeText(getContext(), "length name", Toast.LENGTH_SHORT).show();
            isCorrect=false;
        }else if(phoneStr.length()<9){
            Toast.makeText(getContext(), "length phone", Toast.LENGTH_SHORT).show();
            isCorrect=false;
        }
        else if(g<1||g>12){
            Toast.makeText(getContext(), "grade name", Toast.LENGTH_SHORT).show();
            isCorrect=false;
        }else if(teacherStr.length()<3){
            Toast.makeText(getContext(), "name teachername "+teacherStr+student_layout.allEmployee.indexOf(teacherStr)+" "+student_layout.allEmployee.size(), Toast.LENGTH_SHORT).show();

            isCorrect=false;
        }

        return isCorrect;
    }

    private void initalizeAllComponent() {
        saveChanges=view.findViewById(R.id.saveChanges);
        name=view.findViewById(R.id.nameEdit);
        phone=view.findViewById(R.id.phoneEdit);
        grade=view.findViewById(R.id.gradeEdit);
        teacher=view.findViewById(R.id.teacherEdit);
        name.setText(profileStudent.student_name);
        phone.setText(profileStudent.student_phone);
        grade.setText(profileStudent.student_grade);
        teacher.setText(profileStudent.student_teacher);
    }

}
