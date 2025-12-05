package com.mahir.Service.imp;

import java.util.Optional;

import com.mahir.Entity.User;
import com.mahir.JWT.AuthResponse;

public interface IAuthenticationService {

   User authenticate(User user) ;

   AuthResponse isAuthenticate(User user)throws Exception;
   
}
