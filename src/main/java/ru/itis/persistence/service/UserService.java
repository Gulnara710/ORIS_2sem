package ru.itis.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itis.persistence.entity.UserEntity;
import ru.itis.persistence.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    public Optional<UserEntity> findById(UUID id) {
        return userRepository.getById(id);
    }

    public List<UserEntity> findAll() {
        return userRepository.getAll();
    }

    public void update(UserEntity user) {
        userRepository.update(user);
    }

    public boolean deleteById(UUID id) {
        return userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

}
