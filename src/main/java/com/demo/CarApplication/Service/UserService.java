package com.demo.CarApplication.Service;

import com.demo.CarApplication.Model.User;
import com.demo.CarApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public String add_user(String user_details){
        try {
            User user = new User(user_details);
            userRepository.save(user);
            return "User Successfully added";
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public String delete_user(String username){
        try{
            User user=userRepository.findByName(username);
            userRepository.delete(user);
            return "User is successfully deleted";
        }
        catch(Exception e){
            return e.toString();
        }
    }


}
