package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.User;

public interface IUserRepository {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
}
