package com.mahir.Service.imp;

import java.util.List;

import com.mahir.DTO.UserDTO;
import com.mahir.DTO.UserDTU_IO;

public interface IUserService {
    public List<UserDTO> GetAllUser();

    public UserDTO PostUser(UserDTU_IO userDTO_IO);

    public Boolean DeleteUser(Long id);

    public UserDTO FindUser(Long id);

    public UserDTO UpdateUser(UserDTU_IO userDTO_IO, Long id);

}
