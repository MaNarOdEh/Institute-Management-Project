package com.example.pccorner.finalproject;

public class Student {
    String name;
    String phone;
    String grade;
    String id;

    public Student(String name,String phone,String grade){
        this.name=name;
        this.phone=phone;
        this.grade=grade;
    }

    public String getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/*="#191E0F"
    android:endColor="#05193f"*/