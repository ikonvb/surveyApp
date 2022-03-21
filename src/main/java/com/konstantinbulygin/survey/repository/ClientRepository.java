package com.konstantinbulygin.survey.repository;

import com.konstantinbulygin.survey.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByClientName(String name);
}
