package com.example.demo.services;

import com.example.demo.entity.Client;
import com.example.demo.entity.Credit;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CreditService {
    @Autowired
    private final CreditRepository creditRepository;

    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public void save(Credit credit) {
        creditRepository.save(credit);
    }

    public void delete(UUID id){
        if (!creditRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        creditRepository.deleteById(id);
    }

    public Credit findById(UUID id){
        return creditRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        creditRepository.deleteAll();
    }

    public List<Credit> findAll(){
        List<Credit> res = new ArrayList<>();
        creditRepository.findAll().forEach(res::add);
        return res;
    }
}
