package com.ashokit.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.CountryEntity;

@Repository
public interface CountriesRepository extends JpaRepository<CountryEntity, Serializable>{
	
	
}
