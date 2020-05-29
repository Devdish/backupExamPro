package com.mytest;

public class PreoductModel {



    private String Institute;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    private PreoductModel() {
    }
    private PreoductModel(String Institute,String Name) {
    this.Name= Name;
    this.Institute= Institute;
    }




    public String getInstitute() {
        return Institute;
    }

    public void setInstitute(String institute) {
        Institute = institute;
    }
}
