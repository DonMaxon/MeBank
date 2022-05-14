package com.example.demo.repositories;

import com.example.demo.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {
    Client findByLogin(String login);
}
