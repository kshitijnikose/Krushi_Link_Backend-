package com.tejas.F2B_Farmer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.tejas.F2B_Farmer.model.ContactUs;
import com.tejas.F2B_Farmer.model.Farmer;


@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, Long> {

    // Use the correct property name "farmerid" from the Farmer entity
    List<ContactUs> findByFarmer_Farmerid(Long farmerid);

    List<ContactUs> findByBuyer_Buyerid(Long buyerid);
}


