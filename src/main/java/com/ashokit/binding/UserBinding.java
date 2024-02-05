package com.ashokit.binding;

import java.sql.Date;

import lombok.Data;

@Data
public class UserBinding {
	
	
	
	private Integer userid;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmail;
	
	private Long userphno;
	
	private Date userDateOfBirth;
	
	private String userGender;
	
	private Integer userCountry;
	
	private Integer userState;
	
	private Integer userCity;
	
	private Integer userPWD;
	
	private String userAccStatus;
	
    private Date userCreateDate;
    
    private Date userUpdateDate;
    

}
