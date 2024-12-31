package com.tejas.F2B_Farmer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.F2B_Farmer.model.ContactUs;
import com.tejas.F2B_Farmer.repositories.ContactUsRepo;

@Service
public class ContactUsService {
	
	
	@Autowired
	private ContactUsRepo contactUsRepo;
	
	
	public void getContact() {		
		contactUsRepo.findAll();
		
	}
	
	public String postContact(ContactUs contactUs) {	
		contactUsRepo.save(contactUs);
		return "Succcessfull";
				
	}
	
	
	

    public List<ContactUs> getContactByFarmer(Long farmerid) {
        return contactUsRepo.findByFarmer_Farmerid(farmerid);
    }
    
    
    
    public List<ContactUs> getContactUsByBuyer(Long buyerid) {
        return contactUsRepo.findByBuyer_Buyerid(buyerid);
    }
	
}






















