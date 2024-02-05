package com.ashokit.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name ="User_DTLS")

@Entity
@Data
public class UserEntity {

	@Id
	@Column(name="USER_ID")
	private Integer userid;
	
	@Column(name="USER_FIRST_NAME")
	private String userFirstName;
	
	@Column(name="USER_LAST_NAME")
	private String userLastName;
	
	@Column(name="USER_EMAIL", unique = true)
	private String userEmail;
	
	@Column(name="USER_PHNO")
	private Long userphno;
	
	@Column(name="USER_DOB")
	private Date userDateOfBirth;
	
	@Column(name="USER_GENDER")
	private String userGender;
	
	@Column(name="USER_COUNTRY")
	private Integer userCountry;
	
	@Column(name="USER_STATE")
	private Integer userState;
	
	@Column(name="USER_CITY")
	private Integer userCity;
	
	@Column(name="USER_PWD")
	private Integer userPWD;
	
	@Column(name="USER_ACC_STATUS")
	private String userAccStatus;
	
	@Column(name="CREATED_DATE")
	@CreationTimestamp
    private Date userCreateDate;
    
	@Column(name="UPDATED_DATE")
	@CreationTimestamp
    private Date userUpdateDate;

	
}
