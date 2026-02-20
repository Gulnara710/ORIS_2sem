package ru.itis.service;

import ru.itis.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String name) {
        userRepository.createUser(name);
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void updateUser (Long id, String name) {
        userRepository.updateUser(id, name);
    }

    public void deleteUser (Long id) {
        userRepository.deleteUser(id);
    }
}
