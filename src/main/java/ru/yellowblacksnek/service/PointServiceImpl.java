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
        User user = userService.find(principal.getName());
        return pointRepository.findAllPointsByUser(user);
    }

    @Override
    public boolean validate(Point p) {
        if(p.getX() == null || p.getY() == null || p.getR() == null) return false;
        double[] correctRs = {0.05, 1.0, 2.0, 3.0, 4.0, 5.0};
        boolean rCorrect = false;
        for(double r : correctRs) {
            if(p.getR().compareTo(r) == 0) {
                rCorrect = true;
                break;
            }
        }
        return rCorrect;
    }
}
