package com.example.ToDoBack.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Retrieves a UserDetailDTO object for the given user ID.
     *
     * @param id the ID of the user
     * @return the UserDetailDTO object containing user details, or null if the user
     *         is not found
     */
    public UserDetailDTO getUser(String id) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return UserDetailDTO.builder()
                    .id(user.id)
                    .username(user.username)
                    .firstName(user.firstName)
                    .lastName(user.lastName)
                    .build();
        }
        return null;
    }

    /**
     * Updates the user information based on the provided UserRequest object.
     * 
     * @param userRequest The UserRequest object containing the updated user
     *                    information.
     * @return An Object representing the updated user.
     */
    @Transactional
    public Object updateUser(UserRequest userRequest) {
        User user = User.builder()
                .id(userRequest.id)
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.lastName)
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return new UserResponse("El usuario fue registrad");

    }

}
