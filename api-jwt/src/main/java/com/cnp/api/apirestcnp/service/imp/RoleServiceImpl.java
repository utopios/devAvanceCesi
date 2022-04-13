package com.cnp.api.apirestcnp.service.imp;


import com.cnp.api.apirestcnp.model.Role;
import com.cnp.api.apirestcnp.repository.RoleRepository;
import com.cnp.api.apirestcnp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return role;
    }
}
