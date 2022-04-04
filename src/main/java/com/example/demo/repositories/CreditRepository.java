package com.example.demo.repositories;

import com.example.demo.entity.Credit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {

    @Query(value = "SELECT info.name, count(*) FROM Credit GROUP BY info.name ORDER BY count(*) DESC")
    public List<?> countByType();

    @Query(value = "SELECT info.name, info.maxSumm*(info.rate/100+1)*count(*) FROM Credit GROUP BY info.name, info.maxSumm, info.rate ORDER BY info.maxSumm*(info.rate/100+1)*count(*) DESC")
    public List<?> profitByType();

}
