package com.example.ToDoBack.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    String id;
    String username;
    String firstName;
    String lastName;
    String password;
}
