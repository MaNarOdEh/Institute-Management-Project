package com.example.pccorner.finalproject;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class studentAdapter extends ArrayAdapter {
    ArrayList<Student> student;
    Activity activity;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view =inflater.inflate(R.layout.studentlist,null);
        TextView name=view.findViewById(R.id.name);
        TextView phone=view.findViewById(R.id.phone);
        TextView grade=view.findViewById(R.id.grade);
        name.setText(student.get(position).getName());
        phone.setText(student.get(position).getPhone());
        grade.setText(student.get(position).getGrade());
        CircleImageView imageView=view.findViewById(R.id.myimage);
        return view;
    }

    public studentAdapter(@NonNull Activity activity, ArrayList <Student>student) {
        super(activity,R.layout.studentlist, student);
        this.student=student;
        this.activity=activity;
    }
}
