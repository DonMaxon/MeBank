package com.example.demo.repositories;

import com.example.demo.entity.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {
}
