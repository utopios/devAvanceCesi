package cesi.api.formationapi.controllers;

import cesi.api.formationapi.config.JwtTokenUtils;
import cesi.api.formationapi.dtos.JwtUserDTO;
import cesi.api.formationapi.services.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping()
    public String get() {
        return  "test";
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody JwtUserDTO jwtUserDTO) {
        String test = "";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtUserDTO.getUsername(), jwtUserDTO.getPassword()));
            UserDetails userDetails = _jwtUserService.loadUserByUsername(jwtUserDTO.getUsername());
            String token = _jwtTokenUtils.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }catch (Exception e) {
            return ResponseEntity.status(401).body("not allowed");
        }

    }
}
