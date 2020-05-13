package com.spoton.serveio.ui.general.activity;

public class VolunteersHelperClass {
    String name,location,email,phno,password,age;

    public VolunteersHelperClass(String name, String location, String email, String phno, String password, String age) {
        this.name = name;
        this.location = location;
        this.email = email;
        this.phno = phno;
        this.password = password;
        this.age = age;
    }

    public VolunteersHelperClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
