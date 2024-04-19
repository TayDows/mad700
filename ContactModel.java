package com.example.a402005776_contactapp;

public class ContactModel {
    private int contact_id;
    private String contact_name;
    private String contact_email;
    private String contact_phone_number;
    private String contact_birthday;

    public int getId() {
        return contact_id;
    }

    public void setId(int id) {
        this.contact_id = id;
    }

    public String getName() {
        return contact_name;
    }

    public void setName(String name) {
        this.contact_name = name;
    }

    public String getEmail() {
        return contact_email;
    }

    public void setEmail(String email) {
        this.contact_email = email;
    }

    public String getPhoneNumber() {
        return contact_phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.contact_phone_number = phone_number;
    }

    public String getBirthday() {
        return contact_birthday;
    }

    public void setBirthday(String birthday) {
        this.contact_birthday = birthday;
    }
}
