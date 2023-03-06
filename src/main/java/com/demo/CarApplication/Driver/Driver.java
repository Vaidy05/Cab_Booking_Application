package com.demo.CarApplication.Driver;

import com.demo.CarApplication.Service.UserService;

public class Driver {

    public static void main(String[] args) throws Exception{

        UserService userService = new UserService();
        System.out.println(userService.add_user("Abhishek, M, 23"));

    }
}
