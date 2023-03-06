package com.demo.CarApplication.Controller;

import com.demo.CarApplication.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/add_driver")
    public String add_driver(@RequestParam String driver_details, @RequestParam String vehicle_details, @RequestParam String current_location){
        try{
            return driverService.add_driver(driver_details, vehicle_details, current_location);
        }
        catch(Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_driver")
    public String delete_driver(@RequestParam String drivername, @RequestParam String vehicle_No){
        try{
            return driverService.delete_driver(drivername,vehicle_No);
        }
        catch(Exception e){
            return e.toString();
        }
    }
}
