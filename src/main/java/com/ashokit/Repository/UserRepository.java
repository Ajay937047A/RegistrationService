package com.ashokit.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.UserEntity;


public interface UserRepository extends  JpaRepository<UserEntity, Serializable>{
	
	
	public UserEntity  findByUserEmail(String userEmail);
	
	

}
