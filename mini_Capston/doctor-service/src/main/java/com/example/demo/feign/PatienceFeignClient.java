package com.example.demo.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.PatienceDTO;

import feign.Headers;

@Headers("Content-Type: application/json")

@FeignClient(name = "patience-service", url = "${PRODUCT_SERVICE:http://localhost:9193}")

public interface PatienceFeignClient {

	@GetMapping("/api/patient/doctor/{doctorId}")
	List<PatienceDTO> getPatientByDoctorId(@PathVariable("doctorId") int doctorId);
}
