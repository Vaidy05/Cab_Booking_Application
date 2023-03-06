package com.demo.CarApplication.Repository;

import com.demo.CarApplication.Model.Ride;
import com.demo.CarApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride,Integer> {

    Ride findByUser(User user);
}
