package com.mytest;

public class VideoModelClass {
 String title,description,key,date;

 public VideoModelClass(){

    }

    public VideoModelClass(String title,String description,String key, String date){

     this.title= title;
     this.description= description;
     this.key=key;
     this.date=date;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
