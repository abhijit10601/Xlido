package com.crio.xlido.services;

import java.util.Collections;
import java.util.List;

import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IUserRepository;

public class UserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user with the specified name.
     * @param name Name of the user.
     * @return Created User object.
     */
    public User createUser(String email, String password) {
        User newUser = new User(email, password);

        User user = userRepository.save(newUser);

        return user;
    }
}