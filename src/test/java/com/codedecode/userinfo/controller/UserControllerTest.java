package com.codedecode.userinfo.controller;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void testAddUser() {
        // Implement test logic for addUser method
        // Arrange
        UserDTO userDTO = new UserDTO(null, "John Doe", "1234567890", "address", "dummy city");

        when(userService.addUser(userDTO)).thenReturn(userDTO);
        // Act
        ResponseEntity<UserDTO> userDTOResponseEntity = userController.addUser(userDTO);

        // Assert
        assertNotNull(userDTOResponseEntity);
        assertTrue(userDTOResponseEntity.getStatusCode().is2xxSuccessful());

        assertNotNull(userDTOResponseEntity.getBody());

        assertEquals(userDTO.userName(), userDTOResponseEntity.getBody().userName());
        assertEquals(userDTO.userPassword(), userDTOResponseEntity.getBody().userPassword());
        assertEquals(userDTO.address(), userDTOResponseEntity.getBody().address());
        assertEquals(userDTO.city(), userDTOResponseEntity.getBody().city());
        assertEquals(HttpStatus.CREATED, userDTOResponseEntity.getStatusCode());
    }

    @Test
    void testGetUserById() {
        long id = 1L;

        //Arrange
        UserDTO userDTO = new UserDTO(id, "John Doe", "1234567890", "address", "dummy city");
        when(userService.getUserById(id)).thenReturn(userDTO);

        //Act
        ResponseEntity<UserDTO> userDTOResponseEntity = userController.getUserById(id);

        //Assert
        assertNotNull(userDTOResponseEntity);
        assertTrue(userDTOResponseEntity.getStatusCode().is2xxSuccessful());
        assertNotNull(userDTOResponseEntity.getBody());
        assertEquals(userDTO.userName(), userDTOResponseEntity.getBody().userName());
        assertEquals(userDTO.userPassword(), userDTOResponseEntity.getBody().userPassword());
        assertEquals(userDTO.address(), userDTOResponseEntity.getBody().address());
        assertEquals(userDTO.city(), userDTOResponseEntity.getBody().city());
    }

    @Test
    void testGetAllUsers() {
        // Implement test logic for getAllUsers method
        // Arrange
        List<UserDTO> list = Arrays.asList(new UserDTO(null, "John Doe", "1234567890", "address", "dummy city"),
                new UserDTO(null, "Jane Doe", "0987654321", "address2", "dummy city2"),
                new UserDTO(null, "Jim Doe", "1111111111", "address3", "dummy city3"),
                new UserDTO(null, "Jack Doe", "2222222222", "address4", "dummy city4"));

        when(userService.getAllUsers()).thenReturn(list);

        // Act
        ResponseEntity<List<UserDTO>> userDTOResponseEntity = userController.getAllUsers();

        // Assert
        assertNotNull(userDTOResponseEntity);
        assertTrue(userDTOResponseEntity.getStatusCode().is2xxSuccessful());

        var body = userDTOResponseEntity.getBody();
        assertNotNull(body);
        assertEquals(list.size(), body.size());

        for (int i = 0; i < list.size(); i++) {
            var expected = list.get(i);
            var actual = body.get(i);

            assertAll(
                    () -> assertEquals(expected.userName(), actual.userName()),
                    () -> assertEquals(expected.userPassword(), actual.userPassword()),
                    () -> assertEquals(expected.address(), actual.address()),
                    () -> assertEquals(expected.city(), actual.city())
            );
        }
    }
}
