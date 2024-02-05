package com.ashokit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Table(name = "STATE_MASTER")
@Data
@Entity
public class StateEntity {
	
	@Id
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;

}
