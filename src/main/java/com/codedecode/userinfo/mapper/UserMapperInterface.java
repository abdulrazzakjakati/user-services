package com.codedecode.userinfo.mapper;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperInterface {

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
