package com.example.afinal;

public class Users {
   public String username,email,gender,weight,height;
   public int fitlvl,age;

    public Users() {
    }

    public Users(String username, String email) {
        this.username = username;
        this.email = email;

    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    //    public Users(String gender){
//        this.gender = gender;
//    }
//    public Users(String weight){
//        this.weight = weight;
//    }
//    public Users(String height){
//        this.height = height;
//    }
//    public Users(String age){
//        this.age = age;
//    }
//    public Users(int fitlvl){
//        this.fitlvl = fitlvl;
//    }
}
