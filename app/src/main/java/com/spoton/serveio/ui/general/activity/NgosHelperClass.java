package com.spoton.serveio.ui.general.activity;

public class NgosHelperClass {
    String name, location,regno,email,phno,password, description;

    public NgosHelperClass(String name, String location, String regno, String email, String phno, String password, String description) {
        this.name = name;
        this.location = location;
        this.regno = regno;
        this.email = email;
        this.phno = phno;
        this.password = password;
        this.description = description;

    }

    public NgosHelperClass() {

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

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
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

    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }
}
