package com.mahir.Controller.imp;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.mahir.DTO.UserDTO;
import com.mahir.DTO.UserDTU_IO;

public interface IUserController {

    public List<UserDTO> GetAllUser();

  public ResponseEntity<UserDTO> PostUser(@RequestBody UserDTU_IO userDTO_IO);
    public Boolean DeleteUser(Long id);

    public UserDTO FindUser(Long id);

    public UserDTO UpdateUser(UserDTU_IO userDTO_IO,Long id);
}
