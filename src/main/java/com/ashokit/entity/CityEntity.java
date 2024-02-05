package com.ashokit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Table(name="CITY_MASTER")
@Data
@Entity
public class CityEntity {
	
	@Id
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	
	@Column(name="STATE_ID")
	private Integer stateId;

}
