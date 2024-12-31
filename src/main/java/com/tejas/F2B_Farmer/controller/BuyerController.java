package com.tejas.F2B_Farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // Import RestController annotations
import com.tejas.F2B_Farmer.model.Buyer;
import com.tejas.F2B_Farmer.model.Farmer;
import com.tejas.F2B_Farmer.request.EmailRequest;
import com.tejas.F2B_Farmer.services.BuyerServices;


@CrossOrigin(origins ="http://localhost:4200/")
@RestController // Changed from @Controller to @RestController
@RequestMapping("buyer")
public class BuyerController {

    @Autowired
    private BuyerServices buyerServices;
    
    @Autowired
    private LoginController controller;
    
    @Autowired
    private EmailRequest emailService;

    
    @CrossOrigin(origins ="http://localhost:4200/")
    @GetMapping() // Add a forward slash for consistency
    public List<Buyer> getallbuyerdata() {
        return buyerServices.getallbuyerdata();
    }
    
    
    

    // Add a new buyer
    @CrossOrigin(origins ="http://localhost:4200/")
    @PostMapping()
    public boolean addBuyer(@RequestBody Buyer buyer) {
    	
    	sendThankYouEmail(buyer);
    	 return buyerServices.addbuyer(buyer);
       
    }
    
    
    
    
    @CrossOrigin(origins ="http://localhost:4200/")
    @DeleteMapping("/{id}")
    public boolean deleteBuyerById(@PathVariable Long id) {
    	buyerServices.deletebuyerById(id);
        return true;
    }
    
    
    
    
    
    @CrossOrigin(origins ="http://localhost:4200/")
    @PutMapping("/{id}") 
    public Buyer updateBuyer(@PathVariable Long id, @RequestBody Buyer updatedbuyer) {
    	
    	Buyer dbbuyer = buyerServices.updatebuyer(id, updatedbuyer);
    	
    	controller.setDbbuyer(dbbuyer);
    	
    	return dbbuyer;
       
    }
    
    
    
    
    public boolean sendThankYouEmail(Buyer buyer) {
        try {
            String subject = "Thank You for Registering!";
            String body = "Dear "+buyer.getFirstName()+" "+buyer.getLastName()+"  \r\n"
            		+ "\r\n"
            		+ "Thank you for  Registration! We’re thrilled to have you as part of our community.  \r\n"
            		+ "\r\n"
            		+ "For any questions, our support team is here to help at "+" farmerproject11@gmail.com"  +" \r\n"
            		+ "\r\n"
            		+ "Best regards,  \r\n"
            		+ "KrushiLink Team";
            
            
            
            emailService.sendEmail(buyer.getEmail(), body, subject);
            return true;
        } catch (Exception e) {
            return  false;
        }
    }
     
}















