package com.example.Springboot_h2.service;

import java.util.List;
import java.util.Optional;

import com.example.Springboot_h2.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getAllEmployees();
	Optional<EmployeeDto> getEmployeeById(Long id);
	
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	EmployeeDto updateEmployee(Long id,EmployeeDto employeeDto);
	void deleteEmployee(Long id);
}
