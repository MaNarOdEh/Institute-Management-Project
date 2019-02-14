package com.example.pccorner.finalproject;

public class MonthlyCourceDetails {
    String id;
    String from_time;
    String end_time;
    String from_date;
    String to_date;
    String days;
    String materials;
    String employee_id;
    String money;
    String done_not;
    public MonthlyCourceDetails(String id,String from_time,String end_time,String from_date,
                                String to_date,String days,String materials,String money,String done_not){
        this.id=id;
        this.from_time=from_time;
        this.end_time=end_time;
        this.from_date=from_date;
        this.to_date=to_date;
        this.days=days;
        this.materials=materials;
        this.money=money;
        this.done_not=done_not;

    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDays() {
        return days;
    }

    public String getDone_not() {
        return done_not;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getFrom_date() {
        return from_date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public void setDone_not(String done_not) {
        this.done_not = done_not;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }
}
