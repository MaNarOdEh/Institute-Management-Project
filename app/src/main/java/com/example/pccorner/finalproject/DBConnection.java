package com.example.pccorner.finalproject;

import java.util.ArrayList;

public class DBConnection {

    //this methods will call from java employeestudent
    public  static String getNumberOfStudentinSpecificEmployee(String employeeName){
        return "3";
    }
    public static String getNumberOfBlockedStudentInThatEmployee(String employeeName){
        return "1";

    }
    //this methods is Required for employee materials And Material..
    public static ArrayList getAllMaterialsForThatEmployee(String employeeName){
        ArrayList list=new ArrayList();
        list.add("JAVA");
        list.add("PHP");
        list.add("C++");
        return list;
    }
    public static ArrayList getAllMaterials(){
        ArrayList list=new ArrayList();
        list.add("JAVA");
        list.add("PHP");
        list.add("Android");
        return list;
    }
    public static void  addNewMaterial(String materialName){

    }
    //this methods will used in employee student fragment
    public static void block_srudent(String student_name){

    }
    public static void delete_student(String student_name){

    }
    //this methods will be used in extra lesson(Additional)
    public static void add_extra_lessons(String student_name,String employee_name,String date,String time_start,String time_end,String materials,String money){

    }

    //personal Inof
    public static void updateEmplpyeeName(String oldName,String newName,String newFirstNumber,String newSecondNumber,String facebook,String linkedIn,String hotmail){

    }

}
