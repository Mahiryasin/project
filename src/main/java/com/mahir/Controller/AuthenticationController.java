package com.mahir.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IAuthenticationController;
import com.mahir.Entity.User;
import com.mahir.JWT.AuthResponse;
import com.mahir.Service.imp.IAuthenticationService;


@RestController
@CrossOrigin(origins = "http://localhost:5173") 


public class AuthenticationController implements  IAuthenticationController{

    @Autowired
    private IAuthenticationService authenticationService;



    @PostMapping("/authenticate")
    @Override
    public User authenticate(@RequestBody User user) {
      return authenticationService.authenticate(user);
    }



    @PostMapping("/isauthenticate")
    @Override
    public AuthResponse isauthenticate(@RequestBody User user)throws  Exception {
       return authenticationService.isAuthenticate(user);
    }

}
