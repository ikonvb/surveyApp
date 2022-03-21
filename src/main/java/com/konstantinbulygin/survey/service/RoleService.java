package com.konstantinbulygin.survey.service;

import com.konstantinbulygin.survey.model.Role;
import com.konstantinbulygin.survey.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findById(Integer roleId) {
        return roleRepository.findById(roleId);
    }
}
