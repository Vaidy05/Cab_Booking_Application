package com.demo.CarApplication.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String gender;

    private String age;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CabDriver cabDriver;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ride> rideList;

    public CabDriver getCabDriver() {
        return cabDriver;
    }

    public void setCabDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }

    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User(String user_details) {


        String[] user_info = user_details.split(",");
        name = user_info[0];
        gender = user_info[1].trim();
        age = user_info[2].trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public CabDriver getDriver() {
        return cabDriver;
    }

    public void setDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }
}
