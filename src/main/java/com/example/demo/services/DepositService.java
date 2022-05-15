package com.example.demo.services;

import com.example.demo.entity.Deposit;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DepositService {
    @Autowired
    private final DepositRepository depositRepository;

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    public void save(Deposit deposit) {
        if (!(deposit.getSumm()>deposit.getInfo().getMaxSumm() || deposit.getSumm()<deposit.getInfo().getMinSumm())){
            depositRepository.save(deposit);
        }

    }

    public void delete(UUID id){
        if (!depositRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        depositRepository.deleteById(id);
    }

    public Deposit findById(UUID id){
        return depositRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        depositRepository.deleteAll();
    }

    public List<Deposit> findAll(){
        List<Deposit> res = new ArrayList<>();
        depositRepository.findAll().forEach(res::add);
        return res;
    }

    public List<?> countByType() {
        return depositRepository.countByType();
    }

    public List<?> profitByType() {
        List<?> infos = depositRepository.profitByType();
        return infos;
    }
}
