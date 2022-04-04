package com.example.demo.services;

import com.example.demo.entity.Pay;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PayService {
    @Autowired
    private final PayRepository payRepository;

    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }
    public void save(Pay pay) {
        payRepository.save(pay);
    }

    public void delete(UUID id){
        if (!payRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        payRepository.deleteById(id);
    }

    public Pay findById(UUID id){
        return payRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        payRepository.deleteAll();
    }


}
