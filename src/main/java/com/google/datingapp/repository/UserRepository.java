package com.google.datingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.datingapp.entity.User;
import com.google.datingapp.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(UserGender male);
	
}
