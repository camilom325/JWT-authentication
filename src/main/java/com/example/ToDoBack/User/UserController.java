package com.example.ToDoBack.User;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user's details if found, or a 404 Not Found response if the user
     *         does not exist.
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDetailDTO> getUser(@PathVariable String id) {
        UserDetailDTO userDTO = userService.getUser(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Updates a user.
     *
     * @param userRequest the user request object containing the updated user
     *                    information
     * @return a ResponseEntity with the updated user information
     */
    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

}
