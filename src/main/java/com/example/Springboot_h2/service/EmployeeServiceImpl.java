package com.example.Springboot_h2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Springboot_h2.dto.EmployeeDto;
import com.example.Springboot_h2.entity.Employee;
import com.example.Springboot_h2.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService  {

	
	private final EmployeeRepository employeeRepository;
	
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		
		return employeeRepository.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<EmployeeDto> getEmployeeById(Long id) {
		return employeeRepository.findById(id).map(this::convertToDTO);
	}

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee=convertToEntity(employeeDto);
		Employee savedEmployee=employeeRepository.save(employee);
		
		return convertToDTO(savedEmployee);
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Employee employee=employeeRepository.findById(id).get();
		employee.setFirstName(employeeDto.firstName());
		employee.setLastName(employeeDto.lastName());
		employee.setEmail(employeeDto.email());
		Employee updatedEmployee=employeeRepository.save(employee);
		return convertToDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
		
	}

	
	//Convert Employee Entity to EmployeeDTO
	private EmployeeDto convertToDTO(Employee employee) {
		return new EmployeeDto(employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail());
				
	}
	
	//Convert EmployeeDto to Employee Entity
	
	private Employee convertToEntity(EmployeeDto employeeDto) {
		Employee employee=new Employee();
		
		employee.setFirstName(employeeDto.firstName());
		employee.setLastName(employeeDto.lastName());
		employee.setEmail(employeeDto.email());
		 return employee;
		
	}
}
