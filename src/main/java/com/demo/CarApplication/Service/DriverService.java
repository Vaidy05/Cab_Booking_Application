package com.demo.CarApplication.Service;

import com.demo.CarApplication.Model.CabDriver;
import com.demo.CarApplication.Model.User;
import com.demo.CarApplication.Repository.DriverRepository;
import com.demo.CarApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    public String add_driver(String driver_details, String vehicle_details, String current_location){
        try {
            driverRepository.save(new CabDriver(driver_details, vehicle_details, current_location));
            return "Driver Successfully added";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String delete_driver(String drivername, String vehicle_no){
        try{
            List<CabDriver> cabDriverList = driverRepository.findAll();
            CabDriver driver = new CabDriver();

            for(CabDriver cabDriver : cabDriverList){
                if(cabDriver.getName()==drivername && cabDriver.getVehicle_No()==vehicle_no){
                    driver = cabDriver;
                }
            }

            User user = driver.getUser();
            user.setDriver(null);

            userRepository.save(user);
            driverRepository.delete(driver);
            return "Driver is successfully deleted";
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
