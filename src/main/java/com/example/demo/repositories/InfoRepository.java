package com.example.demo.repositories;

import com.example.demo.entity.Info;
import com.example.demo.utils.InfoStatisctics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface InfoRepository extends CrudRepository<Info, UUID> {

    List<Info> getByType(Info.Type type);

    @Query("SELECT " +
            "    new com.example.demo.utils.InfoStatisctics(c.answer, COUNT(c)) " +
            "FROM " +
            "    credit c " +
            "GROUP BY " +
            "    c.info")
    List<InfoStatisctics> findSurveyCount();

}
