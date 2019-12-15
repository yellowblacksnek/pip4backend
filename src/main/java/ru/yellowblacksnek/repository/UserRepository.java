package ru.yellowblacksnek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yellowblacksnek.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
