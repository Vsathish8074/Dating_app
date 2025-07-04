package com.google.datingapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.datingapp.entity.User;
import com.google.datingapp.repository.UserRepository;
import com.google.datingapp.util.UserGender;

@Repository
public class UserDao {

	@Autowired
	UserRepository userrepository;

	public User saveUser(User user) {

		return userrepository.save(user);
	}

	public List<User> findAllMaleUsers() {
		return userrepository.findByGender(UserGender.MALE);

	}

	public List<User> findAllFemaleUsers() {
		return userrepository.findByGender(UserGender.FEMALE);
	}

	public Optional<User> findBestMatch(int id) {

		return userrepository.findById(id);
	}

	public List<User> searchByName(String letters) {

		return userrepository.searchByName(letters);
	}

	public List<User> searchByEmail(String letters) {
		
		return userrepository.searchByEmail(letters);
	}

}
