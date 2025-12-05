package com.mahir.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahir.Controller.imp.IUserController;
import com.mahir.DTO.UserDTO;
import com.mahir.DTO.UserDTU_IO;
import com.mahir.Service.imp.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Rest/Api")
@EnableJpaAuditing(auditorAwareRef="AuditAware")
public class UserController implements  IUserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

   @GetMapping("/GetallUser")
    @Override
    public List<UserDTO> GetAllUser() {
        return userService.GetAllUser();
    }

   @PostMapping("/PostUser")
   @Override
   public ResponseEntity<UserDTO> PostUser(@RequestBody UserDTU_IO userDTO_IO) {
      org.springframework.http.HttpHeaders httpHeaders=new org.springframework.http.HttpHeaders();
      httpHeaders.set("key", "dnsndksndksds");
       return ResponseEntity.status(200).headers(httpHeaders).body(userService.PostUser(userDTO_IO));
   }

   @Override
   @DeleteMapping("/DeleteUser")
   public Boolean DeleteUser(@RequestParam(name="id") Long id) {
       return userService.DeleteUser(id);
   }

   @Override
   @GetMapping("/FindUser/ById/{id}")
   public UserDTO FindUser(@PathVariable(name="id") Long id) {
       return userService.FindUser(id);
   }

   @Override
   @PutMapping("/UpdateUser/{id}")
   public UserDTO UpdateUser(@RequestBody UserDTU_IO userDTO_IO,@PathVariable(name="id")  Long id) {
       return userService.UpdateUser(userDTO_IO, id);
      
   }

   

   


    

}
