package com.mytest;

public class StudentListModelClass {

  String name,email,mobile,gender,date_of_birth,about,type_user,institute,date,status;


  public  StudentListModelClass(){}

  public StudentListModelClass(String name, String email, String mobile, String gender, String date_of_birth, String about, String type_user, String institute, String date, String status) {
    this.name = name;
    this.email = email;
    this.mobile = mobile;
    this.gender = gender;
    this.date_of_birth = date_of_birth;
    this.about = about;
    this.type_user = type_user;
    this.institute = institute;
    this.date = date;
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDate_of_birth() {
    return date_of_birth;
  }

  public void setDate_of_birth(String date_of_birth) {
    this.date_of_birth = date_of_birth;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  public String getType_user() {
    return type_user;
  }

  public void setType_user(String type_user) {
    this.type_user = type_user;
  }

  public String getInstitute() {
    return institute;
  }

  public void setInstitute(String institute) {
    this.institute = institute;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
