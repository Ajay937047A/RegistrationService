package com.ashokit.service;

import java.util.Map;

import com.ashokit.binding.UserBinding;
import com.ashokit.entity.UserEntity;

public interface RegistrationService {
	
	public boolean uniqueEmail(String userEmail);
	
	public UserEntity registerUser(UserBinding user);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	List<String> listVideos();

}
