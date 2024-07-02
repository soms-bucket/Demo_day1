package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

import jakarta.validation.Valid;
@Service
public class PatientService implements IPatientService{
	//for getting log in console
	private final Logger logger = LoggerFactory.getLogger(PatientService.class);
	
	@Autowired
	PatientRepository patienceRepo;
	
	@Override
	public List<Patient> getPatientFromDatabase() {
		logger.info("Fetching all records from Database...");
		return patienceRepo.findAll();
	}
	
	@Override
	public Optional<Patient> getPatientById(int id) {
		
		return patienceRepo.findById(id);
	}

	@Override
	public void deletePatientById(int id) {
		// TODO Auto-generated method stub
		patienceRepo.deleteById(id);
		
	}

	@Override
	public Patient createPatient(@Valid Patient newProduct) {
		// TODO Auto-generated method stub
		return patienceRepo.save(newProduct);
	}

	@Override
	public ResponseEntity<Patient> updatePatient(Integer productId, @Valid Patient newProduct) {
		Optional<Patient> existingProduct = patienceRepo.findById(productId);
		existingProduct.get().setAge(newProduct.getAge());
		existingProduct.get().setAddress(newProduct.getAddress());
		patienceRepo.save(existingProduct.get());
		return ResponseEntity.ok(existingProduct.get());
	}
	@Override
	public List<Patient> getPatientByDoctor(@PathVariable int doctorId){
		return patienceRepo.findByDoctorID(doctorId);
	}
	
	
	
	
	

}
