package com.example.demo.repositories;

import com.example.demo.entity.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DepositRepository extends CrudRepository<Deposit, UUID> {

    @Query(value = "SELECT info.name, count(*) FROM Deposit GROUP BY info.name ORDER BY count(*) DESC")
    public List<?> countByType();

}
