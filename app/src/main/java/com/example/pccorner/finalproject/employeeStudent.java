package com.example.pccorner.finalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class employeeStudent extends Fragment {

   View view;
   TextView numberOfStudent,numberOfBlockedStudent;
   LinearLayout student;
   String emplyeeName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_employee_student, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
          emplyeeName = bundle.getString("nameEmployee");
        }
        intializeAlllComponent();
        return view;
    }

    private void intializeAlllComponent() {
        Toast.makeText(getContext(),emplyeeName,Toast.LENGTH_LONG).show();
        numberOfBlockedStudent=view.findViewById(R.id.numberOfBlockedStudent);
        numberOfStudent=view.findViewById(R.id.numberOfStudent);
        student=view.findViewById(R.id.student);
        numberOfBlockedStudent.setText(DBConnection.getNumberOfBlockedStudentInThatEmployee(emplyeeName));
        numberOfStudent.setText(DBConnection.getNumberOfStudentinSpecificEmployee(emplyeeName));
        View view1;

        Button btn_del,btn_block;
        for(int i=0;i<2;i++){
            final TextView txt_studentFullName,txt_materiallist,txt_grade,txt_status;
            view1=getLayoutInflater().inflate(R.layout.studentemployeelayout,null);
            txt_studentFullName=view1.findViewById(R.id.txt_studentFullName);
            txt_materiallist=view1.findViewById(R.id.txt_materiallist);
            txt_grade=view1.findViewById(R.id.txt_grade);
            txt_status=view1.findViewById(R.id.txt_status);
            txt_studentFullName.setText("MaNar OdEh");
            txt_materiallist.setText("Materials: "+"DB,Android,C++");
            txt_grade.setText("Grade: "+8);
            txt_status.setText("status: Monthly");
            btn_del=view1.findViewById(R.id.btn_del);
            btn_block=view1.findViewById(R.id.btn_block);
            btn_block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  DBConnection.block_srudent(txt_studentFullName.getText().toString());
                    numberOfBlockedStudent.setText(Integer.parseInt(numberOfBlockedStudent.getText().toString())+1+"");
                  Toast.makeText(getActivity(),"this is student will blocked",Toast.LENGTH_SHORT).show();
                }
            });
            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  DBConnection.delete_student(txt_studentFullName.getText().toString());
                  Toast.makeText(getActivity(),"this is student will be deleted ",Toast.LENGTH_SHORT).show();
                }
            });

            student.addView(view1);

        }
    }
}
