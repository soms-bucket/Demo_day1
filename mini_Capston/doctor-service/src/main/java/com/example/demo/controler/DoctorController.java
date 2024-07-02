package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.PatienceDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.feign.PatienceFeignClient;
import com.example.demo.service.IDoctorService;

import jakarta.validation.Valid;

@RestController // http://localhost:9192/api/hello
@RequestMapping("/api")
public class DoctorController {

	// http://localhost:9193/swagger-ui/index.html
	
	@Autowired
	IDoctorService DoctorService;
	
	@Autowired
	PatienceFeignClient patienceClient;

	@GetMapping("/hello")
	String hello() {
		return "Hello World, Spring Boot.... Welcome to You!";
	}
	
	@GetMapping("/{doctorId}/patient")
	public List<PatienceDTO> getpatiencesByDoctor(@PathVariable int doctorId) {
        return patienceClient.getPatientByDoctorId(doctorId);
	}
	

	@GetMapping("/doctors")
	List<Doctor> doctors() {
		// System.out.println("Running");
		return DoctorService.getDoctorsFromDatabase();

	}

	// http://localhost:9192/api/doctors/1
	@GetMapping("/doctors/{id}")
	Optional<Doctor> findByDoctorsId(@PathVariable int id) throws ResourceNotFoundException {
		Optional<Doctor> product = DoctorService.getDoctorById(id);
		product.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
		return product;
	}

	@DeleteMapping("/doctors/{id}")
	void deleteDoctorsById(@PathVariable int id) {
		DoctorService.deleteDoctorById(id);
	}

	@PostMapping("/doctors")
	public Doctor createDoctor(@Valid @RequestBody Doctor newProduct) {
		return DoctorService.createDoctor(newProduct);
	}

	@PutMapping("/doctors/{id}")
	public ResponseEntity<Doctor> updateDoctor
	(@PathVariable(value = "id") Integer productId,@Valid @RequestBody Doctor newProduct) {
		return DoctorService.updateDoctor(productId,newProduct);
	}

	// http://localhost:9192/api/doctors/req?id=1
	@GetMapping("/doctors/req")
	Optional<Doctor> findByDoctorIdUsingRequest(@RequestParam int id) {
		
		return DoctorService.getDoctorById(id);
	}
	
	
	
	
	
}
