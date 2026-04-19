package com.codedecode.userinfo.mapper;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void testMapUserToUserDTO() {
        // Arrange
        User user = new User();
        user.setUserId(1L);
        user.setUserName("John");
        user.setUserPassword("pass123");
        user.setAddress("Street 1");
        user.setCity("Pune");

        // Act
        UserDTO userDTO = UserMapper.mapUserToUserDTO(user);

        // Assert
        assertNotNull(userDTO);
        assertEquals(user.getUserId(), userDTO.userId());
        assertEquals(user.getUserName(), userDTO.userName());
        assertEquals(user.getUserPassword(), userDTO.userPassword());
        assertEquals(user.getAddress(), userDTO.address());
        assertEquals(user.getCity(), userDTO.city());
    }

    @Test
    void testMapUserDTOToUser() {
        // Arrange
        UserDTO userDTO = new UserDTO(
                2L,
                "Alice",
                "secret",
                "Street 2",
                "Mumbai"
        );

        // Act
        User user = UserMapper.mapUserDTOToUser(userDTO);

        // Assert
        assertNotNull(user);
        assertEquals(userDTO.userId(), user.getUserId());
        assertEquals(userDTO.userName(), user.getUserName());
        assertEquals(userDTO.userPassword(), user.getUserPassword());
        assertEquals(userDTO.address(), user.getAddress());
        assertEquals(userDTO.city(), user.getCity());
    }

    @Test
    void testPrivateConstructor() throws Exception {
        // Cover private constructor
        var constructor = UserMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Exception exception = assertThrows(Exception.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
    }

}
