package com.cesi.apireservation.repository;

import com.cesi.apireservation.model.UserApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends CrudRepository<UserApp, Long> {

    UserApp findUserAppByUsername(String username);
}
