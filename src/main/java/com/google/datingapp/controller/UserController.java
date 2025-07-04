package com.google.datingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.datingapp.entity.User;

import com.google.datingapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userservice;
	
	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		
		return  userservice.saveUser(user);
	}
		
	@GetMapping("/users/gender/male")
	public ResponseEntity<?> findAllMaleUsers(){
		
		return userservice.findAllMaleUsers();
	}
	
	@GetMapping("/users/gender/female")
	public ResponseEntity<?> findAllFemaleUsers(){
		
		return userservice.findAllFemaleUsers();
	}
	
	@GetMapping("/users/best-match/{id}/{top}")

	public ResponseEntity<?> findBestMatch(@PathVariable int id,@PathVariable int top){
		return userservice.findBestMatch(id,top);
		
	}
	@GetMapping("/user/search/name/{letters}")
	public ResponseEntity<?> searchByName(@PathVariable String letters){
	 
	return userservice.searchByName(letters);
	}
	
	@GetMapping("/user/search/email/{letters}")
	public ResponseEntity<?> searchByEmail(@PathVariable String letters){
	 
	return userservice.searchByEmail(letters);
	}
	}
	

