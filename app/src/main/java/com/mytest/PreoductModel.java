package com.mytest;

public class PreoductModel {



    private String Institute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;



    private PreoductModel() {
    }
    private PreoductModel(String Institute,String Name) {
    this.name= name;
    this.Institute= Institute;
    }




    public String getInstitute() {
        return Institute;
    }

    public void setInstitute(String institute) {
        Institute = institute;
    }
}
