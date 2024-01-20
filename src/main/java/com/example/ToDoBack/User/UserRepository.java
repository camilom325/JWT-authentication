package com.example.ToDoBack.User;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'username' : ?0 }")
    Optional<User> findByUsername(String username);

    @Query("{ 'id' : ?0 , 'username' : ?1 , 'firstName' : ?2 , 'lastName' : ?3 , 'password' : ?4 }")
    void updateUser(@Param(value = "id") String id,
            @Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName,
            @Param(value = "pasword") String password);
}
