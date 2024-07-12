package com.example.demo.feign;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.demo.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientFeignFallback implements PatientFeignClient {

	@Override
    public List<Patient> getPatientByDoctorId(int n) {
        return null;
    }
	
	@Override
    public String getException() {
        return "Fixed response";
    }

}
