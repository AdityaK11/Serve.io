package com.spoton.serveio.model;

public class Ngo {
    Double id;
    String name;
    String  location;
    String proof;
    String email;
    String password;
    String description;
    String  phoneNo;
    String upi;
    boolean approved;

    public Ngo() {
    }

    public Ngo(Double id, String name, String location, String proof, String email, String password, String description, String phoneNo, String upi, boolean approved) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.proof = proof;
        this.email = email;
        this.password = password;
        this.description = description;
        this.phoneNo = phoneNo;
        this.upi = upi;
        this.approved = approved;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
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

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
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
