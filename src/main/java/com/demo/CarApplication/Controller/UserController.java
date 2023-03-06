package com.demo.CarApplication.Controller;

import com.demo.CarApplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add_user")
    public String add_user(@RequestParam String user_details){
        try{
            return userService.add_user(user_details);
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @DeleteMapping("/delete_user")
    public String delete_user(@RequestParam String username){
        try{
            return userService.delete_user(username);
        }
        catch (Exception e){
            return e.toString();
        }
    }
}
