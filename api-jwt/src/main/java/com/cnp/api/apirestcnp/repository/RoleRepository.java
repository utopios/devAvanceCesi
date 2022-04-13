package com.cnp.api.apirestcnp.repository;

import com.cnp.api.apirestcnp.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    
    Role findRoleByName(String name);

}
