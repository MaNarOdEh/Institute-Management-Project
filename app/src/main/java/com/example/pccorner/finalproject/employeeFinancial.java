package com.example.pccorner.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class employeeFinancial extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_employee_financial, container, false);
        intializeAllcomponent();
        makeNecessaryEvent();
        return view;
    }

    private void makeNecessaryEvent() {
    }
    private void intializeAllcomponent(){

    }
}
