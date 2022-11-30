package com.rigelstar.osso.model;

public class User
{
    private String username;
    private String joinDate;
    private String citizenshipNumber;
    private String phone;
    private String email;

    public User(String username, String joinDate, String citizenshipNumber, String phone, String email) {
        this.username = username;
        this.joinDate = joinDate;
        this.citizenshipNumber = citizenshipNumber;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
