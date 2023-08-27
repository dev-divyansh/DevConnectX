package com.divyansh.devconnectX.UI;

public class Modal {
    String name;
    String bio;
    String Number;
    String mail;

    public Modal(String name, String bio, String number, String mail) {
        this.name = name;
        this.bio = bio;
        Number = number;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
