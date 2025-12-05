package com.mahir.Controller.imp;

import com.mahir.Entity.User;
import com.mahir.JWT.AuthResponse;

public interface IAuthenticationController {

    User authenticate(User user) ;

      AuthResponse isauthenticate(User user)throws  Exception ;

    

}
