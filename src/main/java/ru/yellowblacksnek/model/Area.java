package ru.yellowblacksnek.model;

public class Area {

//        map.put("-R", -2);
//        map.put("-R/2", -1);
//        map.put("0", 0);
//        map.put("R/2", 1);
//        map.put("R", 2);

    public final Rect rect = new Rect(-2, 0, 2, 1);
    public final Triangle triangle = new Triangle(0, 2, 1, 0);
    public final Arc arc = new Arc(2, 1);

    public Area() {}

    public static boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        return (x <= 0 && y <= 0 && (x/r) >= -r && (y/r) >= -r/2) ||
                (x >= 0 && y >= 0 && y <= (r-2*x)) ||
                (x >= 0 && y <=0 && Math.sqrt(x*x + y*y) <= r/2);
    }

    public static class Rect {
        private int x;
        private int y;
        private int width;
        private int height;

        public Rect() {}

        public Rect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class Triangle {
        private int firstX;
        private int firstY;
        private int secondX;
        private int secondY;

        public Triangle() {}

        public Triangle(int firstX, int firstY, int secondX, int secondY) {
            this.firstX = firstX;
            this.firstY = firstY;
            this.secondX = secondX;
            this.secondY = secondY;
        }

        public int getFirstX() {
            return firstX;
        }

        public void setFirstX(int firstX) {
            this.firstX = firstX;
        }

        public int getFirstY() {
            return firstY;
        }

        public void setFirstY(int firstY) {
            this.firstY = firstY;
        }

        public int getSecondX() {
            return secondX;
        }

        public void setSecondX(int secondX) {
            this.secondX = secondX;
        }

        public int getSecondY() {
            return secondY;
        }

        public void setSecondY(int secondY) {
            this.secondY = secondY;
        }
    }

    public static class Arc {
        private int sector;
        private int radius;

        public Arc(){}

        public Arc(int sector, int radius) {
            this.sector = sector;
            this.radius = radius;
        }

        public int getSector() {
            return sector;
        }

        public void setSector(int sector) {
            this.sector = sector;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }
    }
}
