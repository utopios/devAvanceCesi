package com.cesi.apireservation.service;

import com.cesi.apireservation.model.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private UserAppService userAppService;

    public  void Log(String classeName, List<Object> args, String message) throws Exception {
        UserApp user = userAppService.getUserFromToken();
        System.out.println("user : "+ user.getUsername());
        System.out.println("classe : "+ classeName);
        System.out.println("Exception message : "+ message);
        System.out.println(args);
    }
}
