package com.ashokit.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.CityEntity;

@Repository
public interface CitiesRepository extends JpaRepository<CityEntity, Serializable>{
	
	List<CityEntity> findByStateId(Integer stateId);

}
