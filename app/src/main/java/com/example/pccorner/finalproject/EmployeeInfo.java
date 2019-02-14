package com.example.pccorner.finalproject;

public class EmployeeInfo {
    String name;
    String phone;
    String id;

    public EmployeeInfo(String name,String phone,String id){
        this.name=name;
        this.phone=phone;
        this.id=id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }
}
