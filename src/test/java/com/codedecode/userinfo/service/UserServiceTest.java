package com.codedecode.userinfo.service;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;
import com.codedecode.userinfo.mapper.UserMapperInterface;
import com.codedecode.userinfo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private UserMapperInterface userMapperInterface = Mappers.getMapper(UserMapperInterface.class);

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, userMapperInterface);
    }

     // Implement test methods for UserService here
    @Test
    void testCreateUser() {
        // Implement test logic for createUser method
        //Arrange
        User user = new User(1L, "John Doe",
                "1234567890", "address", "dummy city");

        //Act
        when(userRepository.save(any())).thenReturn(user);
        UserDTO userDTO = userService.addUser(userMapperInterface.userToUserDTO(user));

        //Assert
        verify(userRepository, times(1)).save(any());
        assert userDTO != null;
        assert userDTO.userPassword().equals(user.getUserPassword());
        assert userDTO.userName().equals(user.getUserName());
        assert userDTO.address().equals(user.getAddress());
        assert userDTO.city().equals(user.getCity());
    }

    @Test
     void testGetUserById() {
        // Implement test logic for getUserById method
        var userId = 1L;
        User user = new User(userId, "John Doe",
                "1234567890", "address", "dummy city");

        //Act
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        UserDTO userDTO = userService.getUserById(userId);

        //Assert
        verify(userRepository, times(1)).findById(userId);
        assert userDTO != null;
        assert userDTO.userPassword().equals(user.getUserPassword());
        assert userDTO.userName().equals(user.getUserName());
        assert userDTO.address().equals(user.getAddress());
        assert userDTO.city().equals(user.getCity());
    }

     void testGetAllUsers() {
        // Implement test logic for deleteUser method
         //Arrange
        var userId = 1L;
         List<User> users = List.of(
                 new User(userId, "John Doe",
                         "1234567890", "address", "dummy city")
         );

         //Act
         when(userRepository.findAll()).thenReturn(users);
         var userDTOList = userService.getAllUsers();

            //Assert
            verify(userRepository, times(1)).findAll();
            assert userDTOList != null;
            assert userDTOList.size() == users.size();
            for (int i=0; i<users.size(); i++) {
                assert userDTOList.get(i).userPassword().equals(users.get(i).getUserPassword());
                assert userDTOList.get(i).userName().equals(users.get(i).getUserName());
                assert userDTOList.get(i).address().equals(users.get(i).getAddress());
                assert userDTOList.get(i).city().equals(users.get(i).getCity());
            }
    }
}
