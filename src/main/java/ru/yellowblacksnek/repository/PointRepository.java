package ru.yellowblacksnek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yellowblacksnek.model.Point;
import ru.yellowblacksnek.model.User;


import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {
    Collection<Point> findAllPointsByUser(User user);
}