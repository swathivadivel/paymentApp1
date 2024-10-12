package com.project.ems_backend.service.impl;

import com.project.ems_backend.dto.EmployeeDto;
import com.project.ems_backend.dto.EmployeeMapper;
import com.project.ems_backend.entity.Employee;
import com.project.ems_backend.exception.ResourceNotFoundException;
import com.project.ems_backend.repository.EmployeeRepository;
import com.project.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeRepository employeeRepository;
    //constructor injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
//        List<EmployeeDto> employeeDtoList = new ArrayList<>();
//
//        for(Employee employee : employees ){
//            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
//            employeeDtoList.add(employeeDto);
//        }
//        return employeeDtoList;
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
//       Optional<Employee> employeeOpt =  employeeRepository.findById(id);
//       if(employeeOpt.isPresent()){
//           Employee employee = employeeOpt.get();
//           EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDt(employee);
//           return employeeDto;
//       }
//        else{
//            throw new ResourceNotFoundException("Employee is not exsist with given id");
//       }

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not exsist with given id :"+ id));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
//1. if id is not exsisting,throw resource not found exception
    //2.if id is there update
    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not exsist with given id :" + id) );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not exsist with given id :" + id) );

        employeeRepository.deleteById(id);
    }
}
