package com.example.demo.repositories;

import com.example.demo.entity.Credit;
import com.example.demo.entity.Info;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {

    List<Info> countByInfo(Info info);
}
