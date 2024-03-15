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
	
	
	private final String VIDEO_DIRECTORY = "C:\\Ajay\\Ashok IT\\JRTP\\JRTP";
	private String FILE_PATH_ROOT = "C:\\Ajay\\Ashok IT\\";
	
	@Autowired
	private RegistrationService registrationService;
	
	
	
	@GetMapping("/register/{userEmail}")
	public String uniqueEmail(@PathVariable String userEmail) {
		System.out.println("**** To Call uniqueEmail Method()");
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
	
	
		@GetMapping("/images/{filename}")
	public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) {
		byte[] image = new byte[0];
		try {
			image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

	

	@GetMapping("/videos/download/{fileName}")
	public ResponseEntity<Resource> serveVideo(@PathVariable String fileName) {
		try {
			// Load file as Resource
			Path videoPath = Paths.get(VIDEO_DIRECTORY).resolve(fileName);
			Resource resource = new UrlResource(videoPath.toUri());

			// Check if the file exists
			if (resource.exists() || resource.isReadable()) {
				return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
						.header(HttpHeaders.CONTENT_DISPOSITION,
								"attachment; filename=\"" + resource.getFilename() + "\"")
						.body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/videos")
    public ResponseEntity<List<String>> listVideos() {
        List<String> videoNames = registrationService.listVideos();
        return ResponseEntity.ok().body(videoNames);
    }
}
