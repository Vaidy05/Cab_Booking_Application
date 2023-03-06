package com.demo.CarApplication.Repository;

import com.demo.CarApplication.Model.CabDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<CabDriver,Integer> {

    CabDriver findByName(String name);

    @Override
    List<CabDriver> findAll();
}
