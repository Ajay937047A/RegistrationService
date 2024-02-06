package com.ashokit.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.binding.UserBinding;
import com.ashokit.constant.AppConstant;
import com.ashokit.entity.UserEntity;
import com.ashokit.service.RegistrationService;

@RestController
public class RegistrationServiceController {
	
	@Autowired
	private RegistrationService registrationService;
	
	
	
	@GetMapping("/register/{userEmail}")
	public String uniqueEmail(@PathVariable String userEmail) {
		System.out.println("**** To Find Unique Email******");
		boolean uniqueEmail = registrationService.uniqueEmail(userEmail);
		if(uniqueEmail) {
			return AppConstant.UNIQUE;
		}else {
			return AppConstant.DUPLICATE;
		}
		
	}
	
	@PostMapping("/registerUser")
	public String saveUser(@RequestBody UserBinding user) {
		UserEntity registerUser = registrationService.registerUser(user);
		
		if(registerUser!=null) {
			return AppConstant.REGISTRATION_SUCCESSFULLY;
		}else {
			return AppConstant.FAILURE;
		}
		
	}
	
	@GetMapping("/countries")
	public Map<Integer, String> getCountry(){
		Map<Integer, String> countries=registrationService.getCountries();
		return countries;
	
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> getState(@PathVariable Integer countryId){
		return registrationService.getStates(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> getCountries(@PathVariable Integer stateId){
		return registrationService.getCities(stateId);
	}
}
