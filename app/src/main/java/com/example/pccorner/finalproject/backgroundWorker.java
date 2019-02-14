package com.example.pccorner.finalproject;

import android.app.Activity;
import android.content.Intent;
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
import java.util.HashMap;

public class backgroundWorker extends AsyncTask<String,Void,String> {
    Activity activity;
    String type="";
    String result="";
    final String  URL_LOGIN="http://10.0.2.2/institutes/logIn.php ";
    final String URL_PersonalINFO="http://10.0.2.2/institutes/personalInfo.php ";
    final String URL_UPDATEPERSONALINFO="http://10.0.2.2/institutes/updatePersonalinfo.php ";
    final String URL_GETALLMATERIALS="http://10.0.2.2/institutes/getALlMaterials.php ";
    final String URL_ADDMATERIALS="http://10.0.2.2/institutes/addMaterials.php ";
    final String URL_DELETEMATERIALS="http://10.0.2.2/institutes/deleteMaterials.php ";
    final String URL_UPDATEMATERIALS="http://10.0.2.2/institutes/updateMaterials.php ";
    final String URL_GETSTUDENT="http://10.0.2.2/institutes/gerAllStudent.php ";
    final String URL_GETEMPLOYE="http://10.0.2.2/institutes/getALlEmployee.php ";
    final String URL_ADDSTUDENT="http://10.0.2.2/institutes/addStudent.php ";
    final String URL_MaterialNameEmployeeName="http://10.0.2.2/institutes/getMaterialsName.php ";
    final String URL_Add_Student_Study_Info="http://10.0.2.2/institutes/addStudyInfo.php ";
    final String URL_STUDENTPROFILE="http://10.0.2.2/institutes/studentPeronalnfo.php ";
    final String URL_UPDATESTUDENT="http://10.0.2.2/institutes/updatePerosnalInfoStudent.php ";
    final String URL_GET_ALL_ADDITIONAL="http://10.0.2.2/institutes/allAditionalCource.php ";
    final String URL_DEL_ADDITONAL="http://10.0.2.2/institutes/deletAdditionalCource.php ";
    final String URL_UPDATE_PAID_LESSONS="http://10.0.2.2/institutes/updateAdditionalCourceDetails.php ";
    final String URL_ADD_EXTRA_LESSONS="http://10.0.2.2/institutes/addExtraLessons.php ";
    final String URL_GET_FINANCIAL_STYDENT="http://10.0.2.2/institutes/getFinancialStudentStudyInfo.php ";
    final String URL_DEL_STYDY="http://10.0.2.2/institutes/del_study_infp.php ";
    final String URL_UPDATE_STYDy="http://10.0.2.2/institutes/update_study_info.php ";
    final String URL_RENEW="http://10.0.2.2/institutes/renew_study_infp.php ";
    final String URL_ATTENDENC="http://10.0.2.2/institutes/getAttendenceStudent.php ";
    String name;
    String name_student,phone_number,student_grade;

    backgroundWorker(Activity activity){
        this.activity=activity;
    }
    @Override
    protected String doInBackground(String... strings) {
        type = strings[0];
        if (type.equals("LOGIN")) {
            mainManagerLayout.username=strings[1];
            return logIn(strings[1],strings[2]);

        }else if(type.equals("PERSONALINFO")){
            return getPersonalInfo(strings[1]);
        }else if(type.equals("UPDATEPERSONAL")){
            return updatePersonalInfo(strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);
        }else if(type.equals("ALLMATERIALS")){
           return getALLMATERIALS();
        }else if(type.equals("ADDMATERIALS")){
            return addMaterials(strings[1]);
        }else if(type.equals("DELETEMATERIALS")){
            return deleteMaterials(strings[1]);
        }else if(type.equals("UPDATEDMATERIALS")){
            return upddateMaterials(strings[1]);
        }else if(type.equals("GETALLSTUDENT")){
            return getAllStudent();
        }else if(type.equals("GETALLEMPLOYE")){
            return getAllEmployee();
        }else if(type.equals("GETALLMATERIALSEMPLOUEENAME")){
            return getAllEmployeeMaterialsName();
        }else if(type.equals("Add_student_personal_info")){
            name_student=strings[1];
            phone_number=strings[2];
            student_grade=strings[6];
            return add_student_perosna_info(strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7]);

        }else if(type.equals("Add_Srudent_Study_Info")){
            return add_studnet_study_info(strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7],
                    strings[8],strings[9],strings[10]);
        }else if(type.equals("STUDENTIDPROFILE")){
            return get_info_student(strings[1]);
        }else if(type.equals("UPDATE_STUDENT_PERSONAL_INFO")){
            return update_info_student(strings[1],strings[2],strings[3],strings[4],strings[5]);
        }else if(type.equals("GET_ALL_ADDITIONAL_COURCE")){
            return get_ALl_Additional_Cource_For_Specific_student(strings[1]);
        }else if(type.equals("DELETEADDITIONALCOURCE")){
            return delte_addtional_cource(strings[1]);
        }else if(type.equals("SAVEADDITIONALCOURCE")){
            return UPDATE_PAID_LESSONS(strings[1],strings[2]);
        }else if(type.equals("ADDEXTRALESSONS")){
            return add_extra_lessons(strings[1],strings[2],strings[3],strings[4],strings[5],strings[6],strings[7],strings[8]);
        }else if(type.equals("get_all_study_info")){
            return getURL_GET_FINANCIAL_STYDENT(strings[1]);
        }else if(type.equals("DEL_STUDY")){
            return remove_study(strings[1]);
        }else if(type.equals("update_study_info")){
             return UPDATE_STYDy(strings[1],strings[2]);
        }else if(type.equals("renew")){
            return renew_(strings[1],strings[2],strings[3]);
        }else if(type.equals("attendnce_student")){
            return attendnce_student(strings[1]);
        }
        else{
            return " ";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(type.equals("LOGIN")){
            if(s.equals("!!!")){
                Toast.makeText(activity, "Wrong Input!", Toast.LENGTH_SHORT).show();
            }else {
                mainManagerLayout.log_in=true;
                Intent intent = new Intent(activity.getApplicationContext(),mainManagerLayout.class);
                mainManagerLayout.id=s;
                activity.overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                activity.startActivity(intent);
            }
        }else if(type.equals("PERSONALINFO")){
            if(s.equals("!!!")){

            }else{
                Intent intent=new Intent(activity.getApplicationContext(),PeronalInfo.class);
                mainManagerLayout.setPersonaInfo(s);
                activity.startActivity(intent);
                }
        }else if(type.equals("UPDATEPERSONAL")){
            //Toast.makeText(activity, s+"          Empty  OMG!!!", Toast.LENGTH_LONG).show();
            if(s.equals("TRUEUPDATE")){
                mainManagerLayout.username=name;
            }else if(s.equals("!!!")){
                Intent intent = new Intent(activity.getApplicationContext(),mainManagerLayout.class);
                activity.startActivity(intent);
            }else if(s.equals("!!!")){
                Intent intent = new Intent(activity.getApplicationContext(),mainManagerLayout.class);
                activity.startActivity(intent);
            }
        }else if(type.equals("ALLMATERIALS")){
            if(s.equals("!!!")){

            }else{
                Intent intent = new Intent(activity.getApplicationContext(),Materials.class);
                mainManagerLayout.setMaterials(s);
                activity.startActivity(intent);
            }

        }else if(type.equals("ADDMATERIALS")){
            if(s.equals("TRUEADDMaterials")){
            }else if(s.equals("!!!")){
            }

        }else if(type.equals("DELETEMATERIALS")){
            if(s.equals("TRUEDELETEDMATERIALS")){
            }else if(s.equals("!!!")){
            }
        }else if(type.equals("UPDATEDMATERIALS")){
            if(s.equals("TRUEUPDATEDMATERIALS")){
            }else if(s.equals("!!!")){
            }
        }else if(type.equals("GETALLSTUDENT")){
            if(s.equals("!!!")){

            }else{
                Intent intent = new Intent(activity.getApplicationContext(),student_layout.class);
                mainManagerLayout.setAllStudent(s);
                activity.startActivity(intent);
            }
        }else if(type.equals("GETALLEMPLOYE")){
            if(s.equals("!!!")){

            }else{
                Intent intent=new Intent(activity.getApplicationContext(),Employee.class);
                mainManagerLayout.setALLEmployee(s);
                activity.startActivity(intent);
            }
        }else if(type.equals("GETALLMATERIALSEMPLOUEENAME")){
            if(s.isEmpty()){

            }else{
                student_layout.setEmployeeMaterials(s);
                Intent intent=new Intent(activity.getApplicationContext(),addstudent.class);
                activity.startActivity(intent);
            }
        }else if(type.equals("Add_student_personal_info")){
            if(s.equals("!!!")){

            }else{
                Student st=new Student(name_student,phone_number,student_grade);
                st.setId(s);
                mainManagerLayout.allStudent.add(st);

            }

        }else if(type.equals("Add_Srudent_Study_Info")){
            if(s.equals("!!!")){
                Intent intent=new Intent(activity.getApplicationContext(),student_layout.class);
                activity.startActivity(intent);
            }

        }else if(type.equals("STUDENTIDPROFILE")){
            if(s.equals("!!!")){

            }else{
                Intent intent=new Intent(activity.getApplicationContext(),profileStudent.class);
                     profileStudent.setDate(s);
                activity.startActivity(intent);
            }
        }else if(type.equals("UPDATE_STUDENT_PERSONAL_INFO")){
            if(s.equals("!!!")){
                Intent intent=new Intent(activity.getApplicationContext(),mainManagerLayout.class);
                activity.startActivity(intent);
            }else{
                profileStudent.student_id_teacher=s;

            }
        }else if(type.equals("GET_ALL_ADDITIONAL_COURCE")){
            if(s.equals("!!!")){

            }else{
               profileStudent.setAllAditionalCource(s);
            }
        }else if(type.equals("DELETEADDITIONALCOURCE")){

        }else if(type.equals("SAVEADDITIONALCOURCE")){

        }else if(type.equals("ADDEXTRALESSONS")){

        }else if(type.equals("get_all_study_info")){
            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            //profileStudent.setAllFinancialStudent(s);
            if(s.equals("!!!")){
                //profileStudent.setAllFinancialStudent("");
                profileStudent.monthlycourceDetails=new HashMap<>();
            }else{
                profileStudent.setAllFinancialStudent(s);
            }
        }else if(type.equals("attendnce_student")){
            Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
            if(s.equals("!!!")){

            }else if(!s.isEmpty()){
                profileStudent.setAttendenceStudent(s);
            }
        }
        else if(s.isEmpty()){
        }
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {

    }
    //Materials
    public String addMaterials(String name){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_ADDMATERIALS,post_data);

    }
    public String deleteMaterials(String name){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_DELETEMATERIALS,post_data);
    }

    public String upddateMaterials(String name){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_UPDATEMATERIALS,post_data);
    }
    public String getALLMATERIALS(){
        return publicConnetiond(URL_GETALLMATERIALS,"");

    }



    //Personal Manager
    public String updatePersonalInfo(String UserName,String new_user_name,String Mobile, String phone,String hotmail,String facebook,String linkedIn){
        String post_data = null;
        try {
            post_data= URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(UserName, "UTF-8") +
                    "&"
                    + URLEncoder.encode("new_user_name", "UTF-8") + "=" + URLEncoder.encode(new_user_name, "UTF-8")+
                    "&"
                    + URLEncoder.encode("Mobile", "UTF-8") + "=" + URLEncoder.encode(Mobile, "UTF-8")+
                    "&"
                    + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")+
                    "&"
                    + URLEncoder.encode("hotmail", "UTF-8") + "=" + URLEncoder.encode(hotmail, "UTF-8")+
                    "&"
                    + URLEncoder.encode("facebook", "UTF-8") + "=" + URLEncoder.encode(facebook, "UTF-8")+
                    "&"
                    + URLEncoder.encode("linkedIn", "UTF-8") + "=" + URLEncoder.encode(linkedIn, "UTF-8");
            name=new_user_name;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_UPDATEPERSONALINFO,post_data);
    }
    public String getPersonalInfo(String user_name){

        String post_data = null;
        try {
            post_data = URLEncoder.encode("user_name", "UTF-8")+ "=" + URLEncoder.encode(user_name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_PersonalINFO,post_data);
    }

      //log In
    public String logIn(String user_name,String user_pass){

        String post_data = null;
        try {
             post_data =
                    URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                            + URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_LOGIN,post_data);
    }

    //Student
    public String getAllStudent(){
        return publicConnetiond(URL_GETSTUDENT,"");
    }
    public String addStudent(){
        return publicConnetiond(URL_ADDSTUDENT,"");
    }
    //Employee
    public String getAllEmployee(){
        return publicConnetiond(URL_GETEMPLOYE,"");
    }
    public String getAllEmployeeMaterialsName(){
        return publicConnetiond(URL_MaterialNameEmployeeName,"");
    }public String add_student_perosna_info(String name,String phone,String phoneS,String gender,String birthdate,String grade,String teacher){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                            + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")
                            + "&"
                            + URLEncoder.encode("phoneS", "UTF-8") + "=" + URLEncoder.encode(phoneS, "UTF-8")
                            + "&"
                            + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8")
                            + "&"
                            + URLEncoder.encode("birthdate", "UTF-8") + "=" + URLEncoder.encode(birthdate, "UTF-8")
                            + "&"
                            + URLEncoder.encode("grade", "UTF-8") + "=" + URLEncoder.encode(grade, "UTF-8")
                            + "&"
                            + URLEncoder.encode("teacher", "UTF-8") + "=" + URLEncoder.encode(teacher, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return publicConnetiond(URL_ADDSTUDENT,post_data);
    }
    public String add_studnet_study_info(String name,String from_time,String to_time,String from_date,
                                         String to_date,String days,String materials,String employee_id,
                                          String money,String done_not){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                            + URLEncoder.encode("from_time", "UTF-8") + "=" + URLEncoder.encode(from_time, "UTF-8")
                            + "&"
                            + URLEncoder.encode("to_time", "UTF-8") + "=" + URLEncoder.encode(to_time, "UTF-8")
                            + "&"
                            + URLEncoder.encode("from_date", "UTF-8") + "=" + URLEncoder.encode(from_date, "UTF-8")
                            + "&"
                            + URLEncoder.encode("to_date", "UTF-8") + "=" + URLEncoder.encode(to_date, "UTF-8")
                            + "&"
                            + URLEncoder.encode("days", "UTF-8") + "=" + URLEncoder.encode(days, "UTF-8")
                            + "&"
                            + URLEncoder.encode("materials", "UTF-8") + "=" + URLEncoder.encode(materials, "UTF-8")
                            + "&"
                            + URLEncoder.encode("employee_id", "UTF-8") + "=" + URLEncoder.encode(employee_id, "UTF-8")
                            + "&"
                            + URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(money, "UTF-8")
                            + "&"
                            + URLEncoder.encode("done_not", "UTF-8") + "=" + URLEncoder.encode(done_not, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return publicConnetiond(URL_Add_Student_Study_Info,post_data);
    }
    public String get_info_student(String name){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_STUDENTPROFILE,post_data);
    }
    public String update_info_student(String id,String name,String grade,String phone,String teacher){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+
                            "&"
                            +URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                            +"&"
                            + URLEncoder.encode("grade", "UTF-8") + "=" + URLEncoder.encode(grade, "UTF-8")
                            +"&"
                            + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")
                            +"&"
                            + URLEncoder.encode("teacher", "UTF-8") + "=" + URLEncoder.encode(teacher, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_UPDATESTUDENT,post_data);
    }
    public String get_ALl_Additional_Cource_For_Specific_student(String id){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_GET_ALL_ADDITIONAL,post_data);
    }
    public String delte_addtional_cource(String id){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_DEL_ADDITONAL,post_data);
    }
    public String UPDATE_PAID_LESSONS(String id,String paidNot){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+
            "&"+URLEncoder.encode("paidNot", "UTF-8") + "=" + URLEncoder.encode(paidNot, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_UPDATE_PAID_LESSONS,post_data);

    }
    public String add_extra_lessons(String IdStudent,String IdTeacher,String IdMaterials,
                                    String date,String startTime,String endTime,String money,String paidNot){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("IdStudent", "UTF-8") + "=" + URLEncoder.encode(IdStudent, "UTF-8")+
                            "&"+URLEncoder.encode("IdTeacher", "UTF-8") + "=" + URLEncoder.encode(IdTeacher, "UTF-8")+
                            "&"+URLEncoder.encode("IdMaterials", "UTF-8") + "=" + URLEncoder.encode(IdMaterials, "UTF-8")+
                            "&"+URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")+
                            "&"+URLEncoder.encode("startTime", "UTF-8") + "=" + URLEncoder.encode(startTime, "UTF-8")+
                            "&"+URLEncoder.encode("endTime", "UTF-8") + "=" + URLEncoder.encode(endTime, "UTF-8")+
                            "&"+URLEncoder.encode("money", "UTF-8") + "=" + URLEncoder.encode(money, "UTF-8")+
                            "&"+URLEncoder.encode("paidNot", "UTF-8") + "=" + URLEncoder.encode(paidNot, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_ADD_EXTRA_LESSONS,post_data);

    }
    public String getURL_GET_FINANCIAL_STYDENT(String id){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_GET_FINANCIAL_STYDENT,post_data);
    }
    public String remove_study(String id){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_DEL_STYDY,post_data);
    }


    public String UPDATE_STYDy(String id,String done_not){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+
            "&"+ URLEncoder.encode("done_not", "UTF-8") + "=" + URLEncoder.encode(done_not, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_UPDATE_STYDy,post_data);
    }
    public String renew_(String id,String date_start,String date_end){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8")+
                            "&"+ URLEncoder.encode("to_date", "UTF-8") + "=" + URLEncoder.encode(date_end, "UTF-8")+
            "&"+ URLEncoder.encode("from_date", "UTF-8") + "=" + URLEncoder.encode(date_start, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_RENEW,post_data);

    }
    public String attendnce_student(String id){
        String post_data = null;
        try {
            post_data =
                    URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  publicConnetiond(URL_ATTENDENC,post_data);
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
