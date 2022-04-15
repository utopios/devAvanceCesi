package com.cesi.apireservation.service;

import com.cesi.apireservation.config.PasswordEncoder;
import com.cesi.apireservation.dto.RegisterDTO;
import com.cesi.apireservation.dto.UserDTO;
import com.cesi.apireservation.model.UserApp;
import com.cesi.apireservation.repository.UserAppRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserAppService implements UserDetailsService {

    @Autowired
    private UserAppRepository userAppRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        UserApp userApp = modelMapper.map(registerDTO, UserApp.class);
        userApp.setPassword(passwordEncoder.encoder().encode(userApp.getPassword()));
        userApp = userAppRepository.save(userApp);
        if(userApp == null) {
            throw new Exception("error adding user to database");
        }
        return modelMapper.map(userApp, UserDTO.class);
    }

    public UserApp getUserFromToken() throws Exception {
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        UserApp userApp = userAppRepository.findUserAppByUsername(userDetails.getUsername());
        if(userApp == null) {
            throw new Exception("user not found");
        }
        return userApp;
    }
}
