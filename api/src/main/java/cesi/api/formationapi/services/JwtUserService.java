package cesi.api.formationapi.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserService  implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Cette méthode permet de vérfier si l'utilisateur exite dans nos base de données et renvoyer l'utlisateur avec le mot de passe
        if("ihab".equals(username)) {
            return new User("ihab", "mot de passe", new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
