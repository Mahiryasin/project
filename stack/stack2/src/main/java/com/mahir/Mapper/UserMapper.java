package com.mahir.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.mahir.DTO.UserDTO;
import com.mahir.DTO.UserDTU_IO;
import com.mahir.Entitites.UserEntitiy;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    public List<UserDTO> userdList(List<UserEntitiy> userDTO_IOs);

    @Mapping(source = "username_IO", target = "username")
    @Mapping(source = "password_IO", target = "password")

    public UserEntitiy Convert_IO_User(UserDTU_IO userDTO_IO);

    @Mapping(source = "id", target = "id")
    public UserDTO Convert_User_DTO(UserEntitiy userEntitiy);

}
