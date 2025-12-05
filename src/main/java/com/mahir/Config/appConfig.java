package com.mahir.Config;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import com.mahir.Service.imp.IUserService;

@Configuration

public class appConfig {

    // override edilen method bean olarak kullanılamaz !!
     @Autowired
     private  IUserService userService;
   
     


     @Bean
     // usernami iceriye kosul olarak vermemek icin interfaci inner class yaparak veriyoruz
     public UserDetailsService userDetails(){
    return  new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username){
            Optional<com.mahir.Entity.User> user=userService.findByUsername(username);
           if(user.isPresent()){
            return user.get();
           }else{
            throw new UsernameNotFoundException("User not found");
           }
            
        }
     };
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userDetails());
        authenticationProvider.setPasswordEncoder(bPasswordEncoder());
        return authenticationProvider;
      
    };

    @Bean
    BCryptPasswordEncoder bPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

  
}

