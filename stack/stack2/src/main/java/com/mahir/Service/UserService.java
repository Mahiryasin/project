package com.mahir.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mahir.DTO.UserDTO;
import com.mahir.DTO.UserDTU_IO;
import com.mahir.Entitites.UserEntitiy;
import com.mahir.Mapper.UserMapper;
import com.mahir.Reporistory.UserReporistory;
import com.mahir.Service.imp.IUserService;

@Service
public class UserService implements  IUserService {

   private  final UserReporistory userReporistory;

   private final UserMapper userMapper; 
   public UserService(UserReporistory userReporistory,UserMapper userMapper) {
       this.userReporistory = userReporistory;
       this.userMapper = userMapper;
   }

   @Override
   public List<UserDTO> GetAllUser() {
       List<UserEntitiy> Users = userReporistory.findAll();
       return  userMapper.userdList(Users);
   }

   @Override
   public UserDTO PostUser(UserDTU_IO userDTO_IO) {
       UserEntitiy userEntitiy = userReporistory.save(userMapper.Convert_IO_User(userDTO_IO));
      UserEntitiy saveEntitiy= userReporistory.save(userEntitiy);
     return  userMapper.Convert_User_DTO(saveEntitiy);
      
   }

   @Override
   public Boolean DeleteUser(Long id) {
       Optional<UserEntitiy> user = userReporistory.findById(id);
       if (user.isPresent()) {
           userReporistory.deleteById(id);
           return true;
       }
       else {
           return false;
     }
   }

   @Override
   public UserDTO FindUser(Long id) {
    Optional<UserEntitiy> user = userReporistory.findById(id);
    if (user.isPresent()) {
        return userMapper.Convert_User_DTO(user.get());
    }
    return null;// exception
   }

   @Override
   public UserDTO UpdateUser(UserDTU_IO userDTO_IO, Long id) {
       Optional<UserEntitiy> user = userReporistory.findById(id);
       if (user.isPresent()) {
           userDTO_IO.setId(id);
           UserEntitiy saveUser = userReporistory.save(userMapper.Convert_IO_User(userDTO_IO));
           return userMapper.Convert_User_DTO(saveUser);
       }
       return null;

   }
    
}
