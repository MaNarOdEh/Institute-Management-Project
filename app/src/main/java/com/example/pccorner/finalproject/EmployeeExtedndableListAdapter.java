package com.example.pccorner.finalproject;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;


public class EmployeeExtedndableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<EmployeeInfo> listTitle;
    private HashMap<String, String> expandebleList;
    Activity activity;

    public EmployeeExtedndableListAdapter(Context context, List<EmployeeInfo> listTitle, HashMap<String, String> expandebleList, Activity activity) {
        this.context = context;
        this.listTitle = listTitle;
        this.expandebleList = expandebleList;
        this.activity = activity;

    }

    @Override
    public int getGroupCount() {
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.expandebleList.get(listTitle.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.employe_headerlist, null);
        }
        TextView name = convertView.findViewById(R.id.name);
        TextView phone = convertView.findViewById(R.id.phone);
        TextView id_emplyee = convertView.findViewById(R.id.id_emplyee);
        name.setText(listTitle.get(groupPosition).getName());
        phone.setText(listTitle.get(groupPosition).getPhone());
        id_emplyee.setText(listTitle.get(groupPosition).getId());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.employeeextedndiablelist, null);
        }
         final Fragment f = new employeePersonal();
        final TextView name = convertView.findViewById(R.id.name);
        final TextView phone = convertView.findViewById(R.id.phone);

        LinearLayout linearLayout = convertView.findViewById(R.id.mm);
        CardView personal = linearLayout.findViewById(R.id.Personal);
        CardView financial = linearLayout.findViewById(R.id.financial);
        CardView student = linearLayout.findViewById(R.id.student);
        CardView any = linearLayout.findViewById(R.id.any);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new employeePersonal();
                Bundle bundle = new Bundle();
                FragmentManager fm = ((Employee) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameEmployee, f);
                fragmentTransaction.commit();
            }
        });
        financial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Financial", Toast.LENGTH_LONG).show();


            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Student", Toast.LENGTH_LONG).show();
                Fragment f=new employeeStudent();
                FragmentManager fm = ((Employee) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameEmployee, f);
                fragmentTransaction.commit();


            }
        });
        any.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new employeeMaterils();
                FragmentManager fm = ((Employee) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameEmployee, f);
                fragmentTransaction.commit();

            }
        });
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        convertView.startAnimation(animation);
        return convertView;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
