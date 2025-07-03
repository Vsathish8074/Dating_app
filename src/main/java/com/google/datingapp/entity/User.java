package com.google.datingapp.entity;

import java.util.List;

import com.google.datingapp.util.UserGender;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String name;
    private  String email;
    private int age;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    // one to many
	@ElementCollection
	private List<String> interests;
	
	
}
