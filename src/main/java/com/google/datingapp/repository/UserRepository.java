package com.google.datingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.google.datingapp.entity.User;
import com.google.datingapp.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(UserGender male);

//	Write HQL Query
	@Query("select u from User u  where u.name like ?1")
	List<User> searchByName(String letters);
	@Query("select u from User u  where u.email like ?1")
	List<User> searchByEmail(String letters);
	
}
