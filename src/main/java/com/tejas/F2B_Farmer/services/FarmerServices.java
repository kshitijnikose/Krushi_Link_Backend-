package com.tejas.F2B_Farmer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.F2B_Farmer.model.Farmer;
import com.tejas.F2B_Farmer.repositories.FarmerRepository;
import com.tejas.F2B_Farmer.request.ChangePassword;
import com.tejas.F2B_Farmer.request.LoginRequest;


@Service
public class FarmerServices {

    @Autowired
    private FarmerRepository farmerRepository;

    
    public List<Farmer> getallfarmerdata(){

        return farmerRepository.findAll();
    }


     // Add a new farmer
     public boolean addFarmer(Farmer farmer) {
    	 farmerRepository.save(farmer);
     return true;
    }
     
     
     //LoginRequest
     public Farmer loginRequest(LoginRequest loginRequest) {
    	 
    	 	
    	 
    	    Farmer dbfarmer = farmerRepository.findByEmail(loginRequest.getUsername());
    	    
    	    if (dbfarmer == null || dbfarmer.getPassword() == null) {
    	        return null; // Farmer or password does not exist
    	    }
    	    
    	    else if(dbfarmer.getPassword().equals(loginRequest.getPassword())) {
    	    	
    	    	
    	    	return dbfarmer; 
    	    }
    	    
    	    return null;
    	    
    	    
    	}

     
     
     

     // Find a farmer by ID
     public Optional<Farmer> findFarmerById(Long id) {
        return farmerRepository.findById(id); // Return the result as Optional
    }
     
     
     
     
     

    public String deleteFarmerById(Long id) {
    	farmerRepository.deleteById(id); 
         return "farmer data is deleted"; // Return the result as Optional
    }
    
    
    
    

     // Update a farmer by ID
     public Farmer updateFarmer(Long id, Farmer newFarmerData) {
    	 
        if (farmerRepository.existsById(id)) {
        	
        	String dbpassword = farmerRepository.findById(id).get().getPassword();
        	newFarmerData.setPassword(dbpassword);
        	
        	
            newFarmerData.setFarmerid(id);  // Make sure the ID is set
            
            farmerRepository.save(newFarmerData); // Save the updated data
            
            return newFarmerData;
        } else {
            return null;
        }
    }
     
     
     public boolean changePassword(ChangePassword changePass,Long id) {
    	 
    	 
    	Farmer farmer =  findFarmerById(id).get();
    	
    	String dbpassword = farmer.getPassword();
    	
    	String oldpass = changePass.getOldPassword();
    	
    	String newpassword = changePass.getNewPassword();
    	
    	System.err.println(newpassword+" new password");

    	// Use .equals() to compare the strings
    	if (dbpassword.equals(oldpass)) {
    		
    	    farmer.setPassword(newpassword);
    	    
    	    System.err.println("From if statement");
    	    
    	    System.err.println("From farmer " + farmer.getPassword() );
    	    
    	    
    	    addFarmer(farmer);
    	    
    	    
    	    return true;
    	} else {
    	    return false;
    	 
    
    
}}}




