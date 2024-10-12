package com.project.ems_backend.service;

import com.project.ems_backend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getEmployees();


    EmployeeDto getEmployeeById(long id);

    EmployeeDto updateEmployee(Long id,EmployeeDto employeeDto);

    void deleteEmployee(long id);
}
