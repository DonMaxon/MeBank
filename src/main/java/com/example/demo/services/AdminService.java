package com.example.demo.services;

import com.example.demo.entity.Admin;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public void delete(UUID id){
        if (!adminRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        adminRepository.deleteById(id);
    }

    public Admin findById(UUID id){
        return adminRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        adminRepository.deleteAll();
    }

    public void post (Admin admin){
        adminRepository.save(admin);
    }

}
