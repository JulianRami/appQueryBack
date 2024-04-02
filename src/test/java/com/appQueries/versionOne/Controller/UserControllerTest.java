package com.appQueries.versionOne.Controller;

import com.appQueries.versionOne.controller.UserController;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;
    /**
     * Unit test for the {@link UserController#getAllUsers()} method.
     */
    @Test
    void getAllUsers() {
        List<User> userList = Arrays.asList(
                new User("user1"),
                new User("user2")
        );
        when(userService.getAllUsers()).thenReturn(userList);
        List<User> result = userController.getAllUsers();
        assertEquals(userList, result);
        verify(userService, times(1)).getAllUsers();
    }
    /**
     * Test for the getUserById method in the UserController class.
     */
    @Test
    void getUserById() {
        Long userId = 1L;
        User user = new User("testUser");
        when(userService.getUserById(userId)).thenReturn(user);
        ResponseEntity<User> responseEntity = userController.getUserById(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
        verify(userService, times(1)).getUserById(userId);
    }
    /**
     * Test for the createUser method in the UserController class.
     */
    @Test
    void createUser() {
        User userToCreate = new User("newUser");
        User createdUser = new User("newUser");
        when(userService.createUser(userToCreate)).thenReturn(createdUser);
        ResponseEntity<User> responseEntity = userController.createUser(userToCreate);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdUser, responseEntity.getBody());
        verify(userService, times(1)).createUser(userToCreate);
    }
    /**
     * Test for the updateUser method in the UserController class.
     */
    @Test
    void updateUser() {
        Long userId = 1L;
        User userToUpdate = new User("updatedUser");
        User updatedUser = new User("updatedUser");
        when(userService.updateUser(userId, userToUpdate)).thenReturn(updatedUser);
        ResponseEntity<User> responseEntity = userController.updateUser(userId, userToUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUser, responseEntity.getBody());
        verify(userService, times(1)).updateUser(userId, userToUpdate);
    }
    /**
     * Test for the deleteUser method in the UserController class.
     */
    @Test
    void deleteUser() {
        Long userId = 1L;
        ResponseEntity<Void> responseEntity = userController.deleteUser(userId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

}

