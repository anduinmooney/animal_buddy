package com.epicodus.animalbuddy.models;

/**
 * Created by Guest on 4/12/18.
 */

public class Shelter {
    private String name;
    private String phone;
    private String state;
    private String email;
    private String city;
    private String zip;

    public Shelter(String name, String phone, String state, String email, String city, String zip) {
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.email = email;
        this.city = city;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }
}
