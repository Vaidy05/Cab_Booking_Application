package com.demo.CarApplication.Controller;

import com.demo.CarApplication.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book_ride")
public class RideController {

    @Autowired
    RideService rideService;

    @GetMapping("/find_ride")
    public String find_ride(@RequestParam String username,@RequestParam String source,@RequestParam String destination){
        try{
            return rideService.find_ride(username, source, destination);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @GetMapping("/choose_ride")
    public String choose_ride(@RequestParam String username,@RequestParam String drivername,@RequestParam String source,@RequestParam String destination){
        try{
            return rideService.choose_ride(username, drivername, source, destination);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/cancel_ride")
    public String cancel_ride(@RequestParam String username, @RequestParam String drivername){
        try{
            return rideService.cancel_ride(username,drivername);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @PutMapping("/complete_ride")
    public String complete_ride (@RequestParam String username, @RequestParam String drivername){
        try{
            return rideService.ride_complete(username,drivername);
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
