package com.cesi.apireservation.service;

import com.cesi.apireservation.dto.RegisterDTO;
import com.cesi.apireservation.dto.UserDTO;
import com.cesi.apireservation.model.UserApp;
import com.cesi.apireservation.repository.UserAppRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserAppService implements UserDetailsService {

    @Autowired
    private UserAppRepository userAppRepository;

    private ModelMapper modelMapper;

    public UserAppService() {
        modelMapper = new ModelMapper();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userAppRepository.findUserAppByUsername(username);
        if(userApp == null) {
            throw new UsernameNotFoundException("no user in database with this username");
        }
        return new User(username, userApp.getPassword(), new ArrayList<>());
    }

    public UserDTO regiserUser(RegisterDTO registerDTO) throws Exception {
        //Vérification sur les champs, email, téléphone, ....
        UserApp userApp = userAppRepository.save(modelMapper.map(registerDTO, UserApp.class));
        if(userApp == null) {
            throw new Exception("error adding user to database");
        }
        return modelMapper.map(userApp, UserDTO.class);
    }
}
