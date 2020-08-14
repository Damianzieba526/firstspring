package com.house.firstspring.service;

import com.house.firstspring.domain.User;
import com.house.firstspring.domain.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserExist(User user) {
        return userRepository.findByName(user.getName()).isPresent();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getOneById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
