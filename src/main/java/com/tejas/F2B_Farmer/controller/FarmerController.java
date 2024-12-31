package com.tejas.F2B_Farmer.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.F2B_Farmer.model.Farmer;
import com.tejas.F2B_Farmer.request.EmailRequest;
import com.tejas.F2B_Farmer.request.LoginRequest;
import com.tejas.F2B_Farmer.services.FarmerServices;


@CrossOrigin(origins ="http://localhost:4200/")
@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    private FarmerServices farmerServices;
    
    @Autowired
    private LoginController controller;
    
    
    
    @Autowired
    private EmailRequest emailService;
    
   
    
   
    @GetMapping()
    
    public List<Farmer> getallfarmerdata() {
        return farmerServices.getallfarmerdata();
    
    }
    
   
    @PostMapping("login")
    public Farmer loginRequest(@RequestBody LoginRequest loginRequest) {
    	
    	return farmerServices.loginRequest(loginRequest);  	
    	
    }

    @PostMapping("insert")
    public boolean addFarmer(@RequestBody Farmer farmer) {
    	System.out.println("working");
    	farmerServices.addFarmer(farmer);
    	
    	    return sendThankYouEmail(farmer);
    
    }
     
  
    @DeleteMapping("/{id}")    
    public boolean deletefarmerById(@PathVariable Long id) {
    	
    	
    	farmerServices.deleteFarmerById(id);
    	
    	
     return true;
    }
    

     @PutMapping("/{id}")
    
    public boolean updateFarmer(@PathVariable Long id, @RequestBody Farmer updatedFarmer) {
    	 
    	Farmer farmer =  farmerServices.updateFarmer(id, updatedFarmer);
    	if(farmer!=null)
    	controller.setDbfarmer(farmer);

     return true;
    }
     
     
     public boolean sendThankYouEmail(Farmer farmer) {
	        try {
	            String subject = "Thank You for Registering!";
	            String body = "Dear "+farmer.getFirstName()+" "+farmer.getLastName()+"  \r\n"
	            		+ "\r\n"
	            		+ "Thank you for  Registration! We’re thrilled to have you as part of our community.  \r\n"
	            		+ "\r\n"
	            		+ "For any questions, our support team is here to help at "+" farmerproject11@gmail.com"  +" \r\n"
	            		+ "\r\n"
	            		+ "Best regards,  \r\n"
	            		+ "KrushiLink Team";
	            
	            
	            
	            emailService.sendEmail(farmer.getEmail(), body, subject);
	            return true;
	        } catch (Exception e) {
	            return  false;
	        }
	    }
    
}
















