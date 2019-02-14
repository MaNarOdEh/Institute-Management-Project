package com.example.pccorner.finalproject;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class employeeMaterils extends Fragment {

    View view;
    Spinner showAllMaterials;
    Button addMaterials;
    GridLayout gridforMaterials;
    String employeeName;
   static HashMap<String,String> materilsFotThatEmployee;
     static ArrayList<String>materialsName=mainManagerLayout.getMaterialName();
    View view1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_employee_materils, container, false);
        Bundle bundle=this.getArguments();
        if(bundle!=null){
           employeeName=bundle.getString("nameEmployee");
        }
        intalizeAllComponent();
        makeEventForAllComponent();
        return view;
    }
    public static void setEmplyeeMaterial(String s){
        materilsFotThatEmployee=new HashMap<>();
      String arr[]=s.split("%%%");
      String a[];
      for(int i=0;i<arr.length;i++){
          a=arr[i].split("&&");
          materilsFotThatEmployee.put(a[1],a[0]);
      }

    }
    private void makeEventForAllComponent() {
        addMaterials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object materials=String.valueOf(showAllMaterials.getSelectedItem());
                if(materilsFotThatEmployee.get(materials)==null) {
                    view1 = getLayoutInflater().inflate(R.layout.material, null);
                    TextView txt=view1.findViewById(R.id.materialName);
                    txt.setText(String.valueOf(materials));
                    gridforMaterials.addView(view1);
                    view1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView txt=v.findViewById(R.id.materialName);
                            Toast.makeText(getActivity(),txt.getText()+"  ",Toast.LENGTH_LONG).show();
                        }
                    });

                }else{
                    Toast.makeText(getActivity(),"This cource is already found!",Toast.LENGTH_LONG).show();
                }
              //  Toast.makeText(getActivity(),materials,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void intalizeAllComponent() {
        showAllMaterials=view.findViewById(R.id.showAllMaterials);
        addMaterials=view.findViewById(R.id.addMaterials);
        gridforMaterials=view.findViewById(R.id.gridforMaterials);
        materialsName=mainManagerLayout.getMaterialName();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,materialsName);
        showAllMaterials.setAdapter(arrayAdapter);
      //  backEnd backEnd=new backEnd(getActivity());
        //backEnd.execute("MATERIAL_EMPLOYEE");
        materilsFotThatEmployee=new HashMap<>();
        TextView txt;
        for(int i=0;i<materilsFotThatEmployee.size();i++) {
            view1=getLayoutInflater().inflate(R.layout.material,null);
             txt=view1.findViewById(R.id.materialName);
             txt.setText(String.valueOf(materilsFotThatEmployee.get(i)));
           view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  final  TextView txt=v.findViewById(R.id.materialName);
                    Toast.makeText(getActivity(),txt.getText()+"  ",Toast.LENGTH_LONG).show();
                    final AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                    view1=getLayoutInflater().inflate(R.layout.removematerial,null);
                    Button remove=view1.findViewById(R.id.remove);
                    Button cancel=view1.findViewById(R.id.cancel);
                    TextView txtClose=view1.findViewById(R.id.close);
                    alertDialog.setView(view1);
                    alertDialog.setCancelable(false);
                    final AlertDialog alert=alertDialog.create();
                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(),"Should remove cource "+txt.getText(),Toast.LENGTH_LONG).show();
                            alert.dismiss();
                        }
                    });
                    txtClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.dismiss();
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           alert.dismiss();
                        }
                    });
                    alert.show();

                }
            });
            gridforMaterials.addView(view1);
        }

    }
}
