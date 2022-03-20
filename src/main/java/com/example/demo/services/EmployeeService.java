package com.example.demo.services;

import com.example.demo.entity.Deposit;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(UUID id){
        if (!employeeRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    public Employee findById(UUID id){
        return employeeRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        employeeRepository.deleteAll();
    }
}
