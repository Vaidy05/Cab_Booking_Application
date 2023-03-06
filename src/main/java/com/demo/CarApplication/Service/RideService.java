package com.demo.CarApplication.Service;

import com.demo.CarApplication.Model.CabDriver;
import com.demo.CarApplication.Model.Ride;
import com.demo.CarApplication.Model.User;
import com.demo.CarApplication.Repository.DriverRepository;
import com.demo.CarApplication.Repository.RideRepository;
import com.demo.CarApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RideRepository rideRepository;

    public String find_ride(String username, String source, String destination){
        try {
            User user = userRepository.findByName(username);

            List<CabDriver> cabDriverList = driverRepository.findAll();

            int[] source_xy = getCoordinates(source);

            CabDriver nearest_Cab_driver = new CabDriver();
            int min_distance = Integer.MAX_VALUE;

            for (CabDriver cabDriver : cabDriverList) {

                int[] driver_xy = getCoordinates(cabDriver.getCurrent_location());

                if (Math.abs(driver_xy[0] -source_xy[0] ) <= 5 && Math.abs(driver_xy[1] - source_xy[1]) <= 5 && cabDriver.isAvailable() && Math.abs(driver_xy[0] + driver_xy[1] - source_xy[0] - source_xy[1]) < min_distance) {
                    nearest_Cab_driver = cabDriver;
                    min_distance = Math.abs(driver_xy[0] + driver_xy[1] - source_xy[0] - source_xy[1]);
                }
            }

            if (min_distance==Integer.MAX_VALUE) {
                return "No ride found";
            }
            Ride ride = new Ride(source,destination,user);
            BookRide(ride,user,nearest_Cab_driver);

            return nearest_Cab_driver.getName();
        }
        catch (Exception e){
            return e.toString();
        }
    }


    public String choose_ride(String username,String drivername,String source,String destination){
        try {

            User user = userRepository.findByName(username);
            CabDriver cabDriver = driverRepository.findByName(drivername);

            if(!cabDriver.isAvailable()){
                return drivername+" not available!";
            }

            int[] source_xy = getCoordinates(source);

            int[] driver_xy = getCoordinates(cabDriver.getCurrent_location());


            if(Math.abs(driver_xy[0] - source_xy[0]) > 5 && Math.abs(driver_xy[1] - source_xy[1]) > 5)
                return drivername+" more than 5 units away!";


            Ride ride = new Ride(source,destination,user);
            BookRide(ride,user,cabDriver);

            return "Successfully booked ride with "+drivername;

        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String cancel_ride(String username, String drivername){
        try{
            User user = userRepository.findByName(username);
            CabDriver cabDriver = driverRepository.findByName(drivername);
            List<Ride> rideList = user.getRideList();
            Ride ride = new Ride();

            for(Ride rides : rideList){
                if(rides.getDriver()==cabDriver){
                    ride = rides;
                    break;
                }
            }
            ride.setRide_status("Canceled");
            user.setRideList(rideList);
            user.setDriver(null);
            cabDriver.setUser(null);
            cabDriver.setAvailable(true);

            userRepository.save(user);
            driverRepository.save(cabDriver);

            return "Successfully canceled ride";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String ride_complete(String username, String drivername){
        try{
            User user = userRepository.findByName(username);
            CabDriver cabDriver = driverRepository.findByName(drivername);
            List<Ride> rideList = user.getRideList();
            Ride ride = new Ride();

            for(Ride rides : rideList){
                if(rides.getDriver()==cabDriver){
                    ride = rides;
                    break;
                }
            }

            ride.setRide_status("Completed");
            user.setRideList(rideList);
            user.setDriver(null);
            cabDriver.setUser(null);
            cabDriver.setAvailable(true);

            userRepository.save(user);
            driverRepository.save(cabDriver);

            return "Ride completed";

        }
        catch (Exception e){
            return e.toString();
        }
    }

    public void BookRide(Ride ride, User user, CabDriver cabDriver){
        List<Ride> user_ride = user.getRideList();
        if(user_ride==null)
            user_ride=new ArrayList<>();
        user_ride.add(ride);

        List<Ride> driver_ride = cabDriver.getRideList();
        if(driver_ride==null)
            driver_ride=new ArrayList<>();
        driver_ride.add(ride);


        cabDriver.setUser(user);
        cabDriver.setAvailable(false);
        cabDriver.setRideList(driver_ride);
        user.setDriver(cabDriver);
        user.setRideList(user_ride);
        ride.setDriver(cabDriver);

        userRepository.save(user);
    }

    public int[] getCoordinates(String coordinates){
        int[]xy = new int[2];
        String[] coordinates_xy = coordinates.split(",");
        xy[0] = Integer.parseInt(coordinates_xy[0].trim().substring(1).trim());
        xy[1] = Integer.parseInt(coordinates_xy[1].trim().substring(0,coordinates_xy[1].trim().length()-1).trim());
        return xy;
    }
}
