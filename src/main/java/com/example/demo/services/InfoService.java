package com.example.demo.services;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Info;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InfoService {
    @Autowired
    private final InfoRepository infoRepository;

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public void save(Info info) {
        infoRepository.save(info);
    }

    public void delete(UUID id){
        if (!infoRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        infoRepository.deleteById(id);
    }

    public Info findById(UUID id){
        return infoRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        infoRepository.deleteAll();
    }
}
