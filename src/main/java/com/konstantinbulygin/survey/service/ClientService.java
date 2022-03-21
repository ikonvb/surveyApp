package com.konstantinbulygin.survey.service;

import com.konstantinbulygin.survey.model.Client;
import com.konstantinbulygin.survey.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Client findByClientName(String clientName) {
        return clientRepository.findByClientName(clientName);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
