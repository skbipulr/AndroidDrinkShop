package com.example.bipul.androiddrinkshop.Model;

public class User {
    private String phone;
    private String address;
    private String name;
    private String birthdate;
    private  String error_mag;

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getError_mag() {
        return error_mag;
    }

    public void setError_mag(String error_mag) {
        this.error_mag = error_mag;
    }
}
