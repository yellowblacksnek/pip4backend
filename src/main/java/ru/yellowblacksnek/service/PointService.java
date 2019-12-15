package ru.yellowblacksnek.service;

import ru.yellowblacksnek.model.Point;

import java.security.Principal;
import java.util.Collection;

public interface PointService {
    Point save(Point point);
    Collection<Point> getPoints(Principal user);
}
