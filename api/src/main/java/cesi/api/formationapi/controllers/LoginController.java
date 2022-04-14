package cesi.api.formationapi.controllers;

import cesi.api.formationapi.config.JwtTokenUtils;
import cesi.api.formationapi.dtos.JwtUserDTO;
import cesi.api.formationapi.models.User;
import cesi.api.formationapi.repositories.UserRepository;
import cesi.api.formationapi.services.JwtUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("login")
public class LoginController {

    @Autowired
    private JwtTokenUtils _jwtTokenUtils;

    @Autowired
    private JwtUserService _jwtUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public LoginController() {
        modelMapper = new ModelMapper();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ResponseEntity<?> register(@RequestBody JwtUserDTO jwtUserDTO) {
        jwtUserDTO.setPassword(new BCryptPasswordEncoder().encode(jwtUserDTO.getPassword()));
        cesi.api.formationapi.models.User user =  userRepository.save(modelMapper.map(jwtUserDTO, User.class));
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<?> login(@RequestBody JwtUserDTO jwtUserDTO) {

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtUserDTO.getUsername(),
                            jwtUserDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = _jwtUserService.loadUserByUsername(jwtUserDTO.getUsername());
            String token = _jwtTokenUtils.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }catch (Exception e) {
            return ResponseEntity.status(401).body("not allowed");
        }

    }
}
