package com.example.pccorner.finalproject;

public class AttendStu {
    private String id;
    private String date;
    private String time_start;
    private String time_end;
    private String id_employee;



    public AttendStu(String id,String date,String time_start,String time_end,String id_employee){
        this.id=id;
        this.date=date;
        this.time_start=time_start;
        this.time_end=time_end;
        this.id_employee=id_employee;

    }
    public void setId_employee(String id_employee) {
        this.id_employee = id_employee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public String getDate() {
        return date;
    }

    public String getId_employee() {
        return id_employee;
    }
}
