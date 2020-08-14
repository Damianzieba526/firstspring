package com.house.firstspring.service;

import com.house.firstspring.domain.User;
import com.house.firstspring.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserExist(User user) {
        return userRepository.findById(user.getId()).isPresent();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
