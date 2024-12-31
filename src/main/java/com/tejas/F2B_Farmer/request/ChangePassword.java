package com.tejas.F2B_Farmer.request;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {
	
	
	private String oldPassword;
	
	private String newPassword;
	

}
