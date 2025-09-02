package com.example.Springboot_h2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springboot_h2.dto.EmployeeDto;
import com.example.Springboot_h2.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	public final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public List<EmployeeDto> getAllEmployees(){
		return employeeService.getAllEmployees();
		
	}
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
	 	return employeeService.saveEmployee(employeeDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
		Optional<EmployeeDto> employee=employeeService.getEmployeeById(id);
		return employee.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
		try {
			EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
			return ResponseEntity.ok(updatedEmployee);
			
		} catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	 @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
