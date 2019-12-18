package ru.yellowblacksnek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yellowblacksnek.model.User;
import ru.yellowblacksnek.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User save(User user) {
        User existing = find(user.getUsername());
        if(existing != null) throw new RuntimeException("user already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean saveIfNotExists(User user) {
        boolean result = false;
        try {
            if(save(user) != null) result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public User find(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean validate(User user) {
        if(user.getPassword() == null ||
            user.getPassword().length() < 4 ||
            user.getUsername() == null ||
            user.getUsername().length() < 4)
            return false;
        else return true;
    }
}
