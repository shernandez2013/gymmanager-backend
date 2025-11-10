package com.mycompany.gymmanager.controller;

import com.mycompany.gymmanager.entity.User;
import com.mycompany.gymmanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController(userService);
    }

    @Test
    void createUser_returnsCreated() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<User> resp = controller.createUser(user);

        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertSame(user, resp.getBody());
        verify(userService).createUser(user);
    }

    @Test
    void getAllUsers_returnsList() {
        User user = new User();
        List<User> list = List.of(user);
        when(userService.getAllUsers()).thenReturn(list);

        ResponseEntity<List<User>> resp = controller.getAllUsers();

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(list, resp.getBody());
        verify(userService).getAllUsers();
    }

    @Test
    void getUserById_found() {
        UUID id = UUID.randomUUID();
        User user = new User();
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<User> resp = controller.getUserById(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(user, resp.getBody());
        verify(userService).getUserById(id);
    }

    @Test
    void getUserById_notFound() {
        UUID id = UUID.randomUUID();
        when(userService.getUserById(id)).thenReturn(Optional.empty());

        ResponseEntity<User> resp = controller.getUserById(id);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertNull(resp.getBody());
        verify(userService).getUserById(id);
    }

    @Test
    void updateUser_success() {
        UUID id = UUID.randomUUID();
        User input = new User();
        User updated = new User();
        when(userService.updateUser(id, input)).thenReturn(updated);

        ResponseEntity<User> resp = controller.updateUser(id, input);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(updated, resp.getBody());
        verify(userService).updateUser(id, input);
    }

    @Test
    void updateUser_notFound() {
        UUID id = UUID.randomUUID();
        User input = new User();
        when(userService.updateUser(id, input)).thenThrow(new RuntimeException());

        ResponseEntity<User> resp = controller.updateUser(id, input);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        assertNull(resp.getBody());
        verify(userService).updateUser(id, input);
    }

    @Test
    void deleteUser_success() {
        UUID id = UUID.randomUUID();
        doNothing().when(userService).deleteUser(id);

        ResponseEntity<Void> resp = controller.deleteUser(id);

        assertEquals(HttpStatus.NO_CONTENT, resp.getStatusCode());
        verify(userService).deleteUser(id);
    }

    @Test
    void deleteUser_notFound() {
        UUID id = UUID.randomUUID();
        doThrow(new RuntimeException()).when(userService).deleteUser(id);

        ResponseEntity<Void> resp = controller.deleteUser(id);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
        verify(userService).deleteUser(id);
    }
}

