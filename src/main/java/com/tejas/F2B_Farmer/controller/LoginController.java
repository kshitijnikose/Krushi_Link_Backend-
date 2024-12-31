package com.tejas.F2B_Farmer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tejas.F2B_Farmer.model.Buyer;
import com.tejas.F2B_Farmer.model.Farmer;
import com.tejas.F2B_Farmer.request.ChangePassword;
import com.tejas.F2B_Farmer.request.LoginRequest;
import com.tejas.F2B_Farmer.services.BuyerServices;
import com.tejas.F2B_Farmer.services.FarmerServices;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200/")
public class LoginController {
	
	private Farmer dbfarmer;
	
	private Buyer dbbuyer;

    @Autowired
   private FarmerServices farmerServices;

    @Autowired
   private BuyerServices buyerServices;
    
    
  

    // Farmer login request
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("farmer")
    public Farmer farmerCredentials(@RequestBody LoginRequest loginRequest) {
    	

        
        Farmer farmer = farmerServices.loginRequest(loginRequest);

    System.out.println(farmer+"farmer object retrive from database");
      
        if (farmer != null) {
        
           
            dbfarmer = farmer;
            
            System.out.println(dbfarmer);
            
           return dbfarmer;
            
           
        } else {
        	
        	System.out.println("db farme validation get wroing");
            return null;
        }

 
    }

    // Buyer login request
    @PostMapping("buyer")
    public Buyer buyerCredentials(@RequestBody LoginRequest loginRequest) {
      
        Buyer buyer = buyerServices.buyerlogin(loginRequest);

     
        if (buyer != null) {
        	 dbbuyer=buyer;
        	 
        	 System.out.println("Formm buyer credential : "+dbbuyer);
        	 return dbbuyer;
           
        }
		return buyer; 

  
    }

   //Get current farmer
    @GetMapping("currentFarmer")
    public Farmer getCurrentFarmer(HttpSession session) {
    
        
        return dbfarmer; 
    }

    
    @GetMapping("currentBuyer")
    public Buyer getCurrentBuyer() {
 
    	
        Buyer currentBuyer = dbbuyer;
        return currentBuyer; 
    }
    
    
    
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("changepassword")
    public boolean changePassword(@RequestBody ChangePassword changePassword) {
    	
    	
    	Long farmerid = dbfarmer.getFarmerid();
    	//System.err.println(farmerServices.changePassword(changePassword, farmerid)+" from change pasword frm login");
    	
      return farmerServices.changePassword(changePassword, farmerid);
    
    	
    	
    }
    
    
    
    
    
}
	