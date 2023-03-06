package com.demo.CarApplication.Model;

import jakarta.persistence.*;


@Entity
@Table
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String source;

    private String destination;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private CabDriver cabDriver;

    private String ride_status;

    public Ride() {
    }

    public CabDriver getCabDriver() {
        return cabDriver;
    }

    public void setCabDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }

    public String getRide_status() {
        return ride_status;
    }

    public void setRide_status(String ride_status) {
        this.ride_status = ride_status;
    }

    public Ride(String source, String destination, User user) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.ride_status="Booked";
        this.cabDriver = new CabDriver();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CabDriver getDriver() {
        return cabDriver;
    }

    public void setDriver(CabDriver cabDriver) {
        this.cabDriver = cabDriver;
    }
}
