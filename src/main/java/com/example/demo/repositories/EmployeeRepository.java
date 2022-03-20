package com.example.demo.repositories;

import com.example.demo.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
}
