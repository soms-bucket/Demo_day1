package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Patient;

import jakarta.validation.Valid;

public interface IPatientService {
	
	List<Patient> getPatientFromDatabase();
	public Optional<Patient> getPatientById(int id);
	void deletePatientById(int id);
	Patient createPatient(@Valid Patient newProduct);
	//ResponseEntity<Product> updateProduct(Integer productId);
	ResponseEntity<Patient> updatePatient(Integer productId, @Valid Patient newProduct);
	public List<Patient> getPatientByDoctor(@PathVariable int doctorId);
	

}
