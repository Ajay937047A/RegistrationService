package com.ashokit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "COUNTRY_MASTER")
@Data
@Entity
public class CountryEntity {
	
	
	@Id
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	

}
