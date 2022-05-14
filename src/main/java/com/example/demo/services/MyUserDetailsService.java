package com.example.demo.services;

import com.example.demo.entity.Admin;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    private final AdminRepository adminRepository;

    private final ClientRepository clientRepository;


    @Autowired
    public MyUserDetailsService(EmployeeRepository employeeRepository, AdminRepository adminRepository, ClientRepository clientRepository) {
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (clientRepository.findByLogin(username)!=null){
            return clientRepository.findByLogin(username);
        }
        if (adminRepository.findByLogin(username)!=null){
            return adminRepository.findByLogin(username);
        }
        if (employeeRepository.findByLogin(username)!=null){
            return employeeRepository.findByLogin(username);
        }
        return null;
    }


}
