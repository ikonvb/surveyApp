package com.konstantinbulygin.survey.repository;

import com.konstantinbulygin.survey.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    Optional<Role> findById(Integer id);
}
