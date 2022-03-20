package com.example.demo.repositories;

import com.example.demo.entity.Pay;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PayRepository extends CrudRepository<Pay, UUID> {
}
