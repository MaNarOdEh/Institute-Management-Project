package com.example.pccorner.finalproject;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Materials extends AppCompatActivity {

    EditText txt_materialName;
    Button btn_addMaterial;
    GridLayout grideMaterial;
    Toolbar toolbar;
    ArrayList <String>materialsName;
    ArrayList <String>materialsNumber;
    String material;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        initalizeAllComponent();
        makeNecessaryEvent();
    }

    private void makeNecessaryEvent() {
        btn_addMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                material=txt_materialName.getText().toString();
                if(!material.isEmpty()) {
                    material=material.trim();
                    if (materialsName.indexOf(material) < 0) {
                        final backgroundWorker backgroundWorker=new backgroundWorker(Materials.this);
                        backgroundWorker.execute("ADDMATERIALS",material);
                        Snackbar snackbar = Snackbar.make(v, "Added Successfully", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        txt_materialName.setText("");
                        //Toast.makeText(Materials.this, "This material should adedd to dataBase", Toast.LENGTH_SHORT).show();
                        materialsName.add(material);
                        materialsNumber.add("0");
                       // DBConnection.addNewMaterial(material);
                        TextView nameMaterial, numberStudent;
                        view = getLayoutInflater().inflate(R.layout.materialall, null);
                        nameMaterial = view.findViewById(R.id.txt_name);
                        numberStudent = view.findViewById(R.id.txt_number);
                        numberStudent.setText(0+"");
                        nameMaterial.setText(material);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final TextView t = v.findViewById(R.id.txt_name);
                               // Toast.makeText(Materials.this, t.getText(), Toast.LENGTH_SHORT).show();
                                View view1=getLayoutInflater().inflate(R.layout.removematerial,null);
                                AlertDialog.Builder aBuilder=new AlertDialog.Builder(Materials.this);
                                aBuilder.setView(view1);
                                aBuilder.setCancelable(false);
                               final AlertDialog alertDialog=aBuilder.create();
                                Button remove=view1.findViewById(R.id.remove);
                                Button cancel=view1.findViewById(R.id.cancel);
                                TextView txtClose=view1.findViewById(R.id.close);
                                remove.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                            backgroundWorker backgroundWorker1=new backgroundWorker(Materials.this);
                                            backgroundWorker1.execute("DELETEMATERIALS",(t.getText()).toString());
                                        int index=materialsName.indexOf((t.getText()).toString());
                                        materialsName.remove((t.getText()).toString());
                                        LinearLayout l=(LinearLayout)t.getParent().getParent().getParent();
                                        materialsNumber.remove(index);
                                        grideMaterial.removeView(l);
                                            //view.setVisibility(View.GONE);
                                        Toast.makeText(Materials.this,"Should remove cource ",Toast.LENGTH_LONG).show();
                                        alertDialog.dismiss();
                                    }
                                });
                                txtClose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
                                    }
                                });
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
                                    }
                                });
                                alertDialog.show();
                            }
                        });
                        grideMaterial.addView(view);

                    }else{
                        Snackbar snackbar = Snackbar.make(v, "This Materials Is Already Found!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }else {
                    Snackbar snackbar = Snackbar.make(v, "Please Input Material First!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    // Toast.makeText(Materials.this, "This cource is already found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.about_us:
                Toast.makeText(Materials.this,"about us!!",Toast.LENGTH_LONG).show();
                break;
            case R.id.setting_id:
                if(mainManagerLayout.arrayPersonalInfo==null){
                    Toast.makeText(this, "Null Personal Array", Toast.LENGTH_SHORT).show();
                    backgroundWorker backgroundWorker=new backgroundWorker(Materials.this);
                    backgroundWorker.execute("PERSONALINFO",mainManagerLayout.username);
                }else {
                    intent = new Intent(Materials.this, PeronalInfo.class);
                    startActivity(intent);
                    Toast.makeText(Materials.this, "setting", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.log_out_id:
                intent=new Intent(Materials.this,homeactivity.class);
                startActivity(intent);
                Toast.makeText(Materials.this,"log out",Toast.LENGTH_LONG).show();
                break;
            case R.id.main:
                intent=new Intent(Materials.this,mainManagerLayout.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem mSearch = menu.findItem(R.id.search_src_text);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("Search..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<String> mn = new ArrayList<>();
                ArrayList<String> n = new ArrayList<>();
                for (int i = 0; i < materialsName.size(); i++) {
                    if (materialsName.get(i).length() >= s.length()) {
                        if ((materialsName.get(i).subSequence(0, s.length())).equals(s)) {
                            mn.add(materialsName.get(i));
                            n.add(materialsNumber.get(i));
                        }
                    }
                }
                grideMaterial.removeAllViews();
                for (int i = 0; i < mn.size(); i++) {
                    view = getLayoutInflater().inflate(R.layout.materialall, null);
                    TextView nameMaterial = view.findViewById(R.id.txt_name);
                    TextView numberStudent = view.findViewById(R.id.txt_number);
                    nameMaterial.setText(String.valueOf(mn.get(i)));
                    numberStudent.setText(String.valueOf(n.get(i)));
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final TextView t = v.findViewById(R.id.txt_name);
                            Toast.makeText(Materials.this, t.getText(), Toast.LENGTH_SHORT).show();
                            View view1 = getLayoutInflater().inflate(R.layout.removematerial, null);
                            AlertDialog.Builder aBuilder = new AlertDialog.Builder(Materials.this);
                            aBuilder.setView(view1);
                            aBuilder.setCancelable(false);
                            final AlertDialog alertDialog = aBuilder.create();
                            Button remove = view1.findViewById(R.id.remove);
                            Button cancel = view1.findViewById(R.id.cancel);
                            TextView txtClose = view1.findViewById(R.id.close);
                            remove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    backgroundWorker backgroundWorker1 = new backgroundWorker(Materials.this);
                                    backgroundWorker1.execute("DELETEMATERIALS", (t.getText()).toString());
                                    int index = materialsName.indexOf((t.getText()).toString());
                                    materialsName.remove((t.getText()).toString());
                                    materialsNumber.remove(index);
                                    //view.setVisibility(View.GONE);
                                    LinearLayout l = (LinearLayout) t.getParent().getParent().getParent();
                                    grideMaterial.removeView(l);
                                    Toast.makeText(Materials.this, "Should remove cource ", Toast.LENGTH_LONG).show();
                                    alertDialog.dismiss();
                                }
                            });
                            txtClose.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                            alertDialog.show();
                        }
                    });
                    grideMaterial.addView(view);
                }
                return true;
            }

        });


        return true;
    }



    private void setAttributeTolBar(){
        toolbar.setTitle(mainManagerLayout.username);
        toolbar.setSubtitle(" Institute");
        setSupportActionBar(toolbar);
        toolbar.setElevation(12.f);
    }

    private void initalizeAllComponent() {
            TextView emptyView = findViewById(R.id.emptyView);
            txt_materialName = findViewById(R.id.txt_materialName);
            btn_addMaterial = findViewById(R.id.btn_addMaterial);
            grideMaterial = findViewById(R.id.grideMaterial);
            // grideMaterial.setEmptyView(emptyView);
            toolbar = findViewById(R.id.toolbar);
            materialsName = mainManagerLayout.getMaterialName();
            materialsNumber = mainManagerLayout.getMaterialNumber();
            TextView nameMaterial, numberStudent;
            for (int i = 0; i < materialsName.size(); i++) {
                view = getLayoutInflater().inflate(R.layout.materialall, null);
                nameMaterial = view.findViewById(R.id.txt_name);
                numberStudent = view.findViewById(R.id.txt_number);
                nameMaterial.setText(String.valueOf(materialsName.get(i)));
                numberStudent.setText(String.valueOf(materialsNumber.get(i)));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TextView t = v.findViewById(R.id.txt_name);
                        Toast.makeText(Materials.this, t.getText(), Toast.LENGTH_SHORT).show();
                        View view1 = getLayoutInflater().inflate(R.layout.removematerial, null);
                        AlertDialog.Builder aBuilder = new AlertDialog.Builder(Materials.this);
                        aBuilder.setView(view1);
                        aBuilder.setCancelable(false);
                        final AlertDialog alertDialog = aBuilder.create();
                        Button remove = view1.findViewById(R.id.remove);
                        Button cancel = view1.findViewById(R.id.cancel);
                        TextView txtClose = view1.findViewById(R.id.close);
                        remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                backgroundWorker backgroundWorker1 = new backgroundWorker(Materials.this);
                                backgroundWorker1.execute("DELETEMATERIALS", (t.getText()).toString());
                                int index = materialsName.indexOf((t.getText()).toString());
                                materialsName.remove((t.getText()).toString());
                                materialsNumber.remove(index);
                                //view.setVisibility(View.GONE);
                                LinearLayout l = (LinearLayout) t.getParent().getParent().getParent();
                                grideMaterial.removeView(l);
                                Toast.makeText(Materials.this, "Should remove cource ", Toast.LENGTH_LONG).show();
                                alertDialog.dismiss();
                            }
                        });
                        txtClose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                });
                grideMaterial.addView(view);

            }
            setAttributeTolBar();
        }
}

