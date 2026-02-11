package com.codedecode.userinfo.mapper;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;

public class UserMapper {
    public static UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(user.getUserId(), user.getUserName(),
            user.getUserPassword(), user.getAddress(), user.getCity());
    }

    public static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.userId());
        user.setUserName(userDTO.userName());
        user.setUserPassword(userDTO.userPassword());
        user.setAddress(userDTO.address());
        user.setCity(userDTO.city());
        return user;
    }
}
