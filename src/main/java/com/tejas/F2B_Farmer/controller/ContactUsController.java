package com.tejas.F2B_Farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.F2B_Farmer.model.Buyer;
import com.tejas.F2B_Farmer.model.ContactUs;
import com.tejas.F2B_Farmer.model.Farmer;
import com.tejas.F2B_Farmer.request.EmailRequest;
import com.tejas.F2B_Farmer.services.ContactUsService;

@RestController
@RequestMapping("/contact")
//@CrossOrigin(origins ="http://localhost:4200/")
@CrossOrigin("*")
public class ContactUsController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	
	 @Autowired
	    private EmailRequest emailService;
	 
	 
	 @Autowired
	 private LoginController loginController;
	 
	 
	 
	 
	 
	 boolean flag = false;
	
	public void contactus() {
				
	}
	
	@PostMapping("farmer")
	public String saveRequest(@RequestBody ContactUs contactUs) {
		
		flag = true;
		sendThankYouEmail(contactUs.getMessage());
		return contactUsService.postContact(contactUs);		
		
	}
	
	
	@PostMapping("buyer")
	public String buyerRequest(@RequestBody ContactUs contactUs) {
		sendThankYouEmail(contactUs.getMessage());
		return contactUsService.postContact(contactUs);		
		
	}
	
	
	  @GetMapping("/farmer/{farmerid}")
	    public List<ContactUs> getContactsByFarmer(@PathVariable Long farmerid) {
	        return contactUsService.getContactByFarmer(farmerid);
	    }
	  
	  	  
	  
	  @GetMapping("/buyer/{buyerid}")
	    public List<ContactUs> getContactsByBuyer(@PathVariable Long buyerid) {
	        return contactUsService.getContactUsByBuyer(buyerid);
	    }
	  
	  
	  public boolean sendThankYouEmail(String message) {
		 Farmer farmer= loginController.getDbfarmer();
		 
		 Buyer buyer = loginController.getCurrentBuyer();
		 
		 String firstName ;
		 String lastName;
		 String email ; 
		 
		if(flag==true) {
			
			firstName = farmer.getFirstName();
			lastName=farmer.getLastName();
		    email = farmer.getEmail();
		    flag=false;
			
		}
		else {
			firstName = buyer.getFirstName();
			lastName=buyer.getLastName();
		    email = buyer.getEmail();
					
		}
		 
		
		 
	        try {
	        	String subject = "Thank You for Contacting Us!";
	        	
	        	String body = "Dear " + firstName +" "+lastName+ ",\r\n"
	        	            + "\r\n"
	        	            + "Thank you for reaching out to KrushiLink. We have received your message and will get back to you as soon as possible.\r\n"
	        	            + "\r\n"
	        	            + "Here’s a summary of your inquiry:\r\n"
	        	            + "\r\n"
	        	            + "Name: " + firstName + "\r\n"
	        	            + "Email: " + email + "\r\n"
	        	            + "Message:\r\n"
	        	            + message + "\r\n"
	        	            + "\r\n"
	        	            + "Our team is working to assist you, and you can expect a response within 24-48 hours. If you have additional details to share, feel free to reply to this email.\r\n"
	        	            + "\r\n"
	        	            + "Thank you for choosing KrushiLink. We’re here to help!\r\n"
	        	            + "\r\n"
	        	            + "Best regards,\r\n"
	        	            + "KrushiLink Team";

	        	

	            
	            
	            
	            emailService.sendEmail(email, body, subject);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	        
	        try {
	        	String subject1 = "New Query!";
	            
	            // Construct the body of the email
	            String body1 = "Received a message from:\r\n"
	                         + "Name: " + firstName + " "+lastName +"\r\n"
	                         + "Email: " + email + "\r\n"
	                         + "Message: " + message + "\r\n";
	            
	            // Recipient email address
	            String recipientEmail = "farmerproject11@gmail.com";
	            
	            // Send the email
	            emailService.sendEmail(recipientEmail, body1, subject1);
	            
	            return true;
	            
	        } catch (Exception e) {
	        	
	            return  false;
	        }
	        
	        
	        
	    }
	

}





















