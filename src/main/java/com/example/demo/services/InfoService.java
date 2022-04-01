package com.example.demo.services;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Info;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.InfoRepository;
import com.example.demo.utils.InfoStatisctics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public List<Info> findAll(){
        List<Info> res = new ArrayList<>();
        infoRepository.findAll().forEach(res::add);
        return res;
    }

    public void deleteAll(){
        infoRepository.deleteAll();
    }

    public List<Info> getCredits(){
        return infoRepository.getByType(Info.Type.CREDIT);
    }

    public List<Info> getDeposits(){
        return infoRepository.getByType(Info.Type.DEPOSIT);
    }

    /*public List<InfoStatisctics> getStatsCredits(){
       List<InfoStatisctics> stats = infoRepository.findSurveyCount();
       stats.sort(new Comparator<InfoStatisctics>() {
           @Override
           public int compare(InfoStatisctics o1, InfoStatisctics o2) {
               return (int)(o1.getNum()-o2.getNum());
           }
       });
       return stats;
    }*/

}
