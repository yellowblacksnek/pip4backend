package ru.yellowblacksnek.controller;

import org.hibernate.id.IdentifierGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import ru.yellowblacksnek.model.Area;
import ru.yellowblacksnek.model.Point;
import ru.yellowblacksnek.model.User;
import ru.yellowblacksnek.service.PointService;
import ru.yellowblacksnek.service.UserService;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private PointService pointService;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User req) {
        if(req.getPassword() == null)
            return new ResponseEntity<>("Wrong syntax.", HttpStatus.BAD_REQUEST);
        User user = userService.findByUsername(req.getUsername());
        System.out.println(req.getUsername() + " " + req.getPassword());
        if(user == null) {
            userService.save(req);
            System.out.println("user registered: " + req.getUsername());
            return new ResponseEntity<>("User registered.", HttpStatus.CREATED);
        } else {
            System.out.println("user exists: " + req.getUsername());
            return new ResponseEntity<>("User already exists.", HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/history")
    public Collection<Point> getPoints(Principal user) {
        Collection<Point> points = pointService.getPoints(user);
        points.parallelStream().forEach(p -> p.setUser(null));
        return points;
    }

    @CrossOrigin
    @PostMapping("/point")
    public ResponseEntity<?> addPoint(@RequestBody Point point, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        point.setUser(user);
        if(!point.validate())
            return new ResponseEntity<>("Wrong syntax.", HttpStatus.BAD_REQUEST);
        point.setResult(Area.contains(point));
        System.out.println(point);

        return new ResponseEntity<>(pointService.save(point).setUser(null), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/area")
    public Area getArea() {
        return new Area();
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, IdentifierGenerationException.class, TransactionSystemException.class})
    public String handleParsingException(Exception e) {
        return "Wrong syntax.";
    }
}
