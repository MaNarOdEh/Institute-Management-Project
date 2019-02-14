package com.example.pccorner.finalproject;

public class SudentInfo {
    String name;
    int grade;
    String Materials;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public String getMaterials() {
        return Materials;
    }

    public void setMaterials(String materials) {
        Materials = materials;
    }
    public SudentInfo(String name,int grade,String Materials){
        this.name=name;
        this.grade=grade;
        this.Materials=Materials;
    }
}
