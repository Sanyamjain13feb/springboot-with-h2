package com.example.Springboot_h2.dto;

public record EmployeeDto(
		Long id,
		String firstName,
		String lastName,
		String email)
{
}
