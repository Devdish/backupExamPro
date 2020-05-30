package com.mytest;

import java.util.Date;

public class adminModelforlist {



    String Email;
    String Gender;
    String Institute;
    String Name;
    String Password;
    String UID;
    String Mobile_Number;

    public String getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        Date_of_Birth = date_of_Birth;
    }

    String Date_of_Birth;

    public String getType_User() {
        return Type_User;
    }

    public void setType_User(String type_User) {
        Type_User = type_User;
    }

    String Type_User;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getInstitute() {
        return Institute;
    }

    public void setInstitute(String institute) {
        Institute = institute;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMobile_Number() {
        return Mobile_Number;
    }

    public void setMobile_Number(String mobile_Number) {
        Mobile_Number = mobile_Number;
    }






    public adminModelforlist(){


    }
    public adminModelforlist(String Date_of_Birth,String Type_User,String Email,String Gender,String Institute,String Name,String Password,String Mobile_Number,String UID){
        this.Type_User=Type_User;
        this.Email= Email;
        this.Gender=Gender;
        this.Institute=Institute;
        this.Name=Name;
        this.Password=Password;
        this.Mobile_Number=Mobile_Number;
        this.UID=UID;
        this.Date_of_Birth=Date_of_Birth;

    }




}
