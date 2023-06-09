package com.example.finalprojectphonebook;

public class Profile {
    //profile data
    String name, phone, pEmail, sEmail;
    //validation for bookData entry
    Boolean entered = false;
    Profile(String n, String p, String pe, String se){
        name = n;
        phone = p;
        pEmail = pe;
        sEmail = se;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getPrimary() {
        return pEmail;
    }

    public String getSecondary(){return sEmail;}
    public Boolean getEntered(){
        return entered;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrimary(String email) {
        this.pEmail = email;
    }
    public void setSecondary(String email){this.sEmail = email;}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEntered(Boolean entered) {
        this.entered = entered;
    }
}
