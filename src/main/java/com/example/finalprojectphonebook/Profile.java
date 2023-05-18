package com.example.finalprojectphonebook;

public class Profile {
    //profile data
    String name, phone, email;
    //validation for bookData entry
    Boolean entered = false;
    Profile(String n, String p, String e){
        name = n;
        phone = p;
        email = e;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public Boolean getEntered(){
        return entered;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEntered(Boolean entered) {
        this.entered = entered;
    }
}
