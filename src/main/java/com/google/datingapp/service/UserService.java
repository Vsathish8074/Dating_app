package com.google.datingapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.datingapp.dao.UserDao;
import com.google.datingapp.dto.MatchingUser;
import com.google.datingapp.entity.User;
import com.google.datingapp.sorting.UserSorting;
import com.google.datingapp.util.UserGender;

@Service
public class UserService {
	@Autowired

	UserDao userdao;

	public ResponseEntity<?> saveUser(User user) {
		User saveUser = userdao.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}

	public ResponseEntity<?> findAllMaleUsers() {
		List<User> maleUsers = userdao.findAllMaleUsers();

		if (maleUsers.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users present in the db table");
		}

		else {

			return ResponseEntity.status(HttpStatus.OK).body(maleUsers);
		}

	}

	public ResponseEntity<?> findAllFemaleUsers() {

		List<User> femaleusers = userdao.findAllFemaleUsers();
		if (femaleusers.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no users present in the db table");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(femaleusers);
		}
	}


	public ResponseEntity<?> findBestMatch(int id, int top) {
		Optional<User> optional = userdao.findBestMatch(id);

		if (optional.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid user");
		}
		User user = optional.get();
		List<User> users = null;
		if (user.getGender().equals(UserGender.MALE)) {

			users = userdao.findAllFemaleUsers();
		} else {

			users = userdao.findAllMaleUsers();
		}

		List<MatchingUser> matchingusers = new ArrayList<>();

		for (User u : users) {
			MatchingUser mu = new MatchingUser();
			mu.setId(u.getId());
			mu.setName(u.getName());
			mu.setEmail(u.getEmail());
			mu.setAge(u.getAge());
			mu.setGender(u.getGender());
			mu.setInterests(u.getInterests());

			mu.setAgediff(Math.abs(user.getAge() - u.getAge()));
			List<String> interests1 = user.getInterests();
			List<String> interests2 = user.getInterests();
			int mic = 0;
			for (String s : interests1) {

				if (interests2.contains(s)) {
					mic++;
				}
			}

			mu.setMic(mic);
			matchingusers.add(mu);
		}
		Comparator<MatchingUser> c = new UserSorting();
		Collections.sort(matchingusers, c);
		List<MatchingUser> result = new ArrayList<>();
		for (MatchingUser mu : matchingusers) {
			if (top == 0) {
				break;
			} else {
				result.add(mu);
				top--;
			}
		}
		return ResponseEntity.status(200).body(result);
	}

	public ResponseEntity<?> searchByName(String letters) {

		List<User> users = userdao.searchByName("%" + letters + "%");
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no User Found With Letters:" + letters);
		}
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	public ResponseEntity<?> searchByEmail(String letters) {
		List<User> users=userdao.searchByEmail(letters);
		if(users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid user email");
		}else
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
}
