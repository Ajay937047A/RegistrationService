package com.ashokit.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.Repository.CitiesRepository;
import com.ashokit.Repository.CountriesRepository;
import com.ashokit.Repository.StatesRepository;
import com.ashokit.Repository.UserRepository;
import com.ashokit.binding.UserBinding;
import com.ashokit.constant.AppConstant;
import com.ashokit.entity.CityEntity;
import com.ashokit.entity.CountryEntity;
import com.ashokit.entity.StateEntity;
import com.ashokit.entity.UserEntity;
import com.ashokit.exceptionHandler.RegAppException;
import com.ashokit.utils.EmailUtils;

@Service
public class RegistrationImpl implements RegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountriesRepository countriesRepository;

	@Autowired
	private StatesRepository statesRepository;

	@Autowired
	private CitiesRepository citiesRepository;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean uniqueEmail(String userEmail) {

		try {
			UserEntity findByUserEmail = userRepository.findByUserEmail(userEmail);
			if (findByUserEmail != null) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null != null;
	}

	@Override
	public UserEntity registerUser(UserBinding user) {

		UserEntity entity = new UserEntity();

		if (user.getUserid() != null) {
			user.setUserAccStatus(AppConstant.LOCKED);
			user.setUserPWD(generatePWD());
			
			

			BeanUtils.copyProperties(user, entity);

			UserEntity save = userRepository.save(entity);
			
			if(null!=save.getUserid()) {
			
			 sendEmail(user);
			 
			 return save;
			 
			}
		}
		return null;

	}

	private boolean  sendEmail(UserBinding user) {
		boolean emailSend=false;
		String subject="User Registration Success";
		String body=readMailBody("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt", user);
		
		emailUtils.sendMail(subject, body, user.getUserEmail());
		
		return emailSend;
	}
	
	
	public String readMailBody(String fileName, UserBinding user) {
		String mailBody=null;
		StringBuffer buffer=new StringBuffer();
		Path path = Paths.get(fileName);
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line->{
				buffer.append(line);
			});
			mailBody=buffer.toString();
			mailBody= mailBody.replace("{FNAME}", user.getUserFirstName());
			mailBody= mailBody.replace("{EMAIL}", user.getUserEmail());
			mailBody= mailBody.replace("{TEMP-PWD}", Integer.toString(user.getUserPWD()));
			return mailBody;
			
		} catch (Exception e) {
			
			throw new RegAppException(e.getMessage());
		}
		
		
	}

	private int generatePWD() {

		Integer passwd = null;

		int min = 50000; // Minimum value of range
		int max = 500000; // Maximum value of range
		passwd = (int) Math.floor(Math.random() * (max - min + 1) + min);

		return passwd;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> countries = countriesRepository.findAll();
		Map<Integer, String> countriesMap = new HashMap<>();
		for (CountryEntity entity : countries) {
			countriesMap.put(entity.getCountryId(), entity.getCountryName());

		}

		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> findByStateId = statesRepository.findByStateId(countryId);
		Map<Integer, String> statesMap = new HashMap<>();
		for (StateEntity entity : findByStateId) {
			statesMap.put(entity.getStateId(), entity.getStateName());
		}

		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {

		List<CityEntity> findByStateId = citiesRepository.findByStateId(stateId);
		Map<Integer, String> countriesMap = new HashMap<>();
		for (CityEntity entity : findByStateId) {
			countriesMap.put(entity.getCityId(), entity.getCityName());
		}

		return countriesMap;
	}

}
