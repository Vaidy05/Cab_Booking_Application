package com.demo.CarApplication.Model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table
public class CabDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String gender;

    private String age;

    private String vehicle_model;

    private String vehicle_No;

    private boolean available;

    private String current_location;

    public List<Ride> getRideList() {
        return rideList;
    }

    public void setRideList(List<Ride> rideList) {
        this.rideList = rideList;
    }

    @OneToOne
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "cabDriver",cascade = CascadeType.ALL)
    private List<Ride> rideList;

    public CabDriver(String driver_details, String vehicle_details, String current_location) {

        String[] driver_info = driver_details.split(",");
        name = driver_info[0];
        gender = driver_info[1];
        age = driver_info[2];

        String[] vehicle_info = vehicle_details.split(",");
        vehicle_model = vehicle_info[0];
        vehicle_No = vehicle_info[1];

        this.current_location = current_location;
        available=true;
    }

    public CabDriver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_No() {
        return vehicle_No;
    }

    public void setVehicle_No(String vehicle_No) {
        this.vehicle_No = vehicle_No;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
