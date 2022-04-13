package com.cnp.api.apirestcnp.service;


import com.cnp.api.apirestcnp.model.Role;

public interface RoleService {
    Role findByName(String name);
}
