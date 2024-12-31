package com.tejas.F2B_Farmer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tejas.F2B_Farmer.model.Farmer;


@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long>{
	
	 Farmer findByEmail(String email);
    
}