package com.example.demo.repositories;

import com.example.demo.entity.Info;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface InfoRepository extends CrudRepository<Info, UUID> {

    List<Info> getByType(Info.Type type);

}
