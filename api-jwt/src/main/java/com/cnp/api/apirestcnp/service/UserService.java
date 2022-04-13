package com.cnp.api.apirestcnp.service;



import com.cnp.api.apirestcnp.model.User;
import com.cnp.api.apirestcnp.model.UserDto;

import java.util.List;

public interface UserService {
    
    User save(UserDto user);

    List<User> findAll();

    User findOne(String username);
}
