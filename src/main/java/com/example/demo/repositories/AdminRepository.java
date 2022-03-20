package com.example.demo.repositories;

import com.example.demo.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdminRepository extends CrudRepository<Admin, UUID> {
}
