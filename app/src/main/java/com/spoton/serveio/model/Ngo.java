package com.spoton.serveio.model;

public class Ngo {
    String id;
    String name;
    String  location;
    String ngoRegNo;
    String email;
    String password;
    String description;
    String  phoneNo;
    String upi;
    boolean approved;

    public Ngo() {
    }

    public Ngo(String id, String name, String location, String ngoRegNo, String email, String password, String description, String phoneNo, String upi, boolean approved) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.ngoRegNo = ngoRegNo;
        this.email = email;
        this.password = password;
        this.description = description;
        this.phoneNo = phoneNo;
        this.upi = upi;
        this.approved = approved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNgoRegNo() {
        return ngoRegNo;
    }

    public void setNgoRegNo(String ngoRegNo) {
        this.ngoRegNo = ngoRegNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
