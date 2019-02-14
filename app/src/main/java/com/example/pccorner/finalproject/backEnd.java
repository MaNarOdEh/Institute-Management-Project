package com.example.pccorner.finalproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class backEnd extends AsyncTask<String,Void,String> {
    String type="";
    String result="";
    final String URL_GETEMPLOYE="http://10.0.2.2/institutes/getALlEmployee.php ";
    final String URL_GETSTUDENT="http://10.0.2.2/institutes/gerAllStudent.php ";
    final String URL_GETALLMATERIALS="http://10.0.2.2/institutes/getALlMaterials.php ";
    final String URL_ALLNONPAID="http://10.0.2.2/institutes/getAllNonPaidExtraLessons.php ";
    final String URL_ALLEXTRA="http://10.0.2.2/institutes/getAllExtraLessons.php ";
    final String URL_EMPLOYEE_MATERIALS="http://10.0.2.2/institutes/getMaterialForThatEmployee.php ";
    final String URL_Pass_Birth="http://10.0.2.2/institutes/getPassordBirthDateEmployee.php ";
    final String URL_UPDATE_EMPLOYEE="http://10.0.2.2/institutes/updateEmployeeInfro.php ";
    final String URL_ADD_EMPLOYEE="http://10.0.2.2/institutes/addNewEmployee.php ";
    final String URL_ADD_ATENDENCE="http://10.0.2.2/institutes/add_attendence.php ";
    Activity activity;
    String name="",phone="";


     backEnd(Activity activity){
        this.activity=activity;
    }
    @Override
    protected void onPostExecute(String s) {
         if(type.equals("ALL_NON_PAID_LEESONS")){
             if(s.isEmpty()){
                 Toast.makeText(activity, "Empty", Toast.LENGTH_SHORT).show();

             }else if(s.equals("!!!")){
                 Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
                 additionalcource.setAllAditionalCource(s);
             }
         }else if(type.equals("ALL_EXTRA_LESSONS")){
             if(s.isEmpty()){
                 Toast.makeText(activity, "Empty!!", Toast.LENGTH_SHORT).show();
             }else if(s.equals("!!!")){
                 Toast.makeText(activity, "!!", Toast.LENGTH_SHORT).show();
             }else{
                 additionalcource.setALLessons(s);
                 Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
             }

         }else if(type.equals("MATERIAL_EMPLOYEE")){
             employeeMaterils.setEmplyeeMaterial(s);
         }else if(type.equals("PASS_BIRTH")){
             String arr[]=s.split("&&");
             employeePersonal.birth_employee=arr[1];
             employeePersonal.pass_employee=arr[0];
             Toast.makeText(activity,arr[0]+"    "+arr[1],Toast.LENGTH_LONG).show();
         }else if(type.equals("employee_add")){
             if(s.equals("!!!")){

             }else{
                 mainManagerLayout.allEmployee.add(new EmployeeInfo(name,phone,s));
             }
         }
         else if(type.equals("add_attendence")){
             if(s.isEmpty()){
               //  Toast.makeText(activity, s+"  EMPTU!!!!! "+s, Toast.LENGTH_LONG).show();
             }
           //  Toast.makeText(activity, s+"   "+s, Toast.LENGTH_LONG).show();
         }
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        type=strings[0];
        if(type.equals("allStudent_allEmployee_allMaterials")){
            getEmployeeInfo();
            getStudentInfo();
            getALLMATERIALS();
        }else if(type.equals("ALL_NON_PAID_LEESONS")){
           return getNonPaidLessons();
        }else if(type.equals("ALL_EXTRA_LESSONS")){
            return getAllExtraLessons();
        }else if(type.equals("MATERIAL_EMPLOYEE")){
            return getMaterialEmployee(strings[1]);
        }else if(type.equals("PASS_BIRTH")){
            return getPassBirthEmloyee(strings[1]);
        }else if(type.equals("updateEmployee")){
            updateEmployeeInfo(strings[1],strings[2],strings[3],strings[4],strings[5]);
        }else if(type.equals("employee_add")){
            addEmployeeInfo(strings[1],strings[2],strings[3],strings[4]);
            name=strings[1];
            phone=strings[3];
        }else if(type.equals("add_attendence")){
           return add_attendence(strings[1],strings[2],strings[3],strings[4],strings[5]);
        }
        return "";
    }



    private String getEmployeeInfo(){
        String s= publicConnetiond(URL_GETEMPLOYE,"");
        mainManagerLayout.setALLEmployee(s);
        return s;
    }
    public String getALLMATERIALS(){
        String s= publicConnetiond(URL_GETALLMATERIALS,"");
        mainManagerLayout.setMaterials(s);
        return s;

    }
    private  String getStudentInfo(){
        String s= publicConnetiond(URL_GETSTUDENT,"");
        mainManagerLayout.setAllStudent(s);
        return s;
    }
    private String getNonPaidLessons(){
          String s=publicConnetiond(URL_ALLNONPAID,"");
         //FragemntNotPaidExtraLessons.setAllAditionalCource(s)
           return s;
    }private String getAllExtraLessons(){
         String s=publicConnetiond(URL_ALLEXTRA,"");
         return s;
    }
    private String getMaterialEmployee(String id){
        String s=publicConnetiond(URL_EMPLOYEE_MATERIALS,"");
        return s;
    }private String getPassBirthEmloyee(String id){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_Pass_Birth,post_data);

    }private void updateEmployeeInfo(String id,String username,String password,String mobile,String birthdate){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+"&"
                            +URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")+"&"
                            +URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")+"&"
                            +URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8")+"&"
                            +URLEncoder.encode("birthdate", "UTF-8") + "=" + URLEncoder.encode(birthdate, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
         publicConnetiond(URL_UPDATE_EMPLOYEE,post_data);

    }
    private void addEmployeeInfo(String username,String password,String mobile,String birthdate){
        String post_data = null;
        try {
            post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")+"&"
                            +URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")+"&"
                            +URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8")+"&"
                            +URLEncoder.encode("birthdate", "UTF-8") + "=" + URLEncoder.encode(birthdate, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        publicConnetiond(URL_ADD_EMPLOYEE,post_data);

    }
    private String add_attendence(String name,String date,String time_start,String time_end,String employee_id){
        String post_data = null;
        try {
            post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                    +"&"
                    +URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")
                    +"&"
                    +URLEncoder.encode("time_start", "UTF-8") + "=" + URLEncoder.encode(time_start, "UTF-8")
                    +"&"
                    +URLEncoder.encode("time_end", "UTF-8") + "=" + URLEncoder.encode(time_end, "UTF-8")
                    +"&"
                    +URLEncoder.encode("employee_id", "UTF-8") + "=" + URLEncoder.encode(employee_id, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       return publicConnetiond(URL_ADD_ATENDENCE,post_data);
    }
    private String publicConnetiond(String URL_LINK,String post_data){
        URL url = null;
        try {

            url = new URL(URL_LINK);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            //Server Request
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            //Put Parameters Together
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            //read the result..
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
