package ru.yellowblacksnek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yellowblacksnek.model.Point;
import ru.yellowblacksnek.model.User;
import ru.yellowblacksnek.repository.PointRepository;

import java.security.Principal;
import java.util.Collection;

@Service
public class PointServiceImpl implements PointService {
    private PointRepository pointRepository;
    private UserService userService;

    @Autowired
    PointServiceImpl(PointRepository pointRepository, UserService userService) {
        this.pointRepository = pointRepository;
        this.userService = userService;
    }

    @Override
    public Point save(Point point) {
        return pointRepository.save(point);
    }

    @Override
    public Collection<Point> getPoints(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return pointRepository.findAllPointsByUser(user);
    }
}
