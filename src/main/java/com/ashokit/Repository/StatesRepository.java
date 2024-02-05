package com.ashokit.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.StateEntity;

@Repository
public interface StatesRepository  extends JpaRepository<StateEntity, Serializable>{
	
	public List<StateEntity> findByStateId(Integer countryId);
	

}
