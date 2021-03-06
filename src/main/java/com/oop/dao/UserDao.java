package com.oop.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oop.entities.AppUser;

@Repository
public interface UserDao extends CrudRepository<AppUser, Integer> {
	
	AppUser findByUsername(String username);
	
}