package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	@Query(value = "select p from patience p where p.docId = ?1")
	List<Patient> findByDoctorID(Integer docId);
}
