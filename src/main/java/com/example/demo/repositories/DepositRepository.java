package com.example.demo.repositories;

import com.example.demo.entity.Deposit;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DepositRepository extends CrudRepository<Deposit, UUID> {
}
