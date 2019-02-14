package com.example.pccorner.finalproject;

public class AdditionalCourceDetails {
    String id;
    String date;
    String time_start;
    String time_end;
    String materials;
    String teacher;
    String money;
    String doen_not;
    String id_student;
    public AdditionalCourceDetails(String id,String date,String time_start,String time_end,
                                   String materials,String teacher,String money,String does_not){
        this.id=id;
        this.date=date;
        this.time_start=time_start;
        this.time_end=time_end;
        this.materials=materials;
        this.teacher=teacher;
        this.money=money;
        this.doen_not=does_not;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getMaterials() {
        return materials;
    }

    public String getDate() {
        return date;
    }

    public String getDoen_not() {
        return doen_not;
    }

    public String getMoney() {
        return money;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTime_end() {
        return time_end;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDoen_not(String doen_not) {
        this.doen_not = doen_not;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }
}
