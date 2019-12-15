package ru.yellowblacksnek.service;

import ru.yellowblacksnek.model.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
}
