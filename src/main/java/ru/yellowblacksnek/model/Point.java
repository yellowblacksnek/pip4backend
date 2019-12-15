package ru.yellowblacksnek.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="points")
public class Point {

    private @Id @GeneratedValue int id;
    private @NotNull Double x;
    private @NotNull Double y;
    private @NotNull Double r;
    private @NotNull boolean result;

    @ManyToOne
    private @NotNull User user;

    public Point() {}

    public Point(User user, Double x, Double y, Double r, boolean result) {
        this.user = user;
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public Point setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean validate() {
        if(x == null || y == null || r == null) return false;
        double[] correctRs = {-2.0, -1.5, -1.0, -0.5, 0, 0.5, 1.0, 1.5, 2.0};
        boolean rCorrect = false;
        for(double r : correctRs) {
            if(this.r.compareTo(r) == 0) {
                rCorrect = true;
                break;
            }
        }
        return rCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return result == point.result &&
                x.equals(point.x) &&
                y.equals(point.y) &&
                r.equals(point.r) &&
                user.equals(point.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, result, user);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", r='" + r + '\'' +
                ", result=" + result +
                ", user=" + user +
                '}';
    }
}
