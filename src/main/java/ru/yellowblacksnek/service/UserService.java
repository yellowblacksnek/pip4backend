package ru.yellowblacksnek.service;

import ru.yellowblacksnek.model.User;

public interface UserService {
    // save(User user);
    User find(String username);
    boolean validate(User user);
    boolean saveIfNotExists(User user);
}
