package com.example.demo.services;

import com.example.demo.entity.Client;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public void save(Client client) {
        clientRepository.save(client);
    }

    public void delete(UUID id){
        if (!clientRepository.existsById(id)){
            throw new NotFoundException(id);
        }
        clientRepository.deleteById(id);
    }

    public Client findById(UUID id){
        return clientRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void deleteAll(){
        clientRepository.deleteAll();
    }

    public List<Client> findAll(){
        List<Client> res = new ArrayList<>();
        clientRepository.findAll().forEach(res::add);
        return res;
    }

}
