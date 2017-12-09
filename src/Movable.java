package src;

import javafx.geometry.Point2D;

public abstract class Movable {

    public Point2D position;

    public Double turnsToReach(Character other){
        if(getSpeed() == 0) {
            return (double) -1;
        }
        return this.position.distance(other.position) / getSpeed();
    }

    public void moveTo(Point2D target, double time){
        this.position = move(target.subtract(this.position), 1);
    }

    public Point2D move(Point2D velocity, double time) {
        return move(this.position, velocity, time);
    }

    public Point2D getDirection(Point2D target) {
        return getDirection(this.position, target);
    }

    public static Point2D move(Point2D position, Point2D velocity, double time){
        double x = Math.floor(velocity.getX()*time + position.getX());
        double y = Math.floor(velocity.getY()*time + position.getY());
        Point2D moved = new Point2D(x, y);
        System.err.println(String.format("velocity.getX()=%f time=%f position.getX()=%f",velocity.getX(),time, position.getX()));
        System.err.println("x = "+ x);
        System.err.println("y = "+ y);
        System.err.println("Moved: " + moved);
        return moved;
    }

    public static Point2D getDirection(Point2D source, Point2D target){
        System.err.println(String.format("Direction vector from %.2f,%.2f to %.2f,%.2f is:", source.getX(),source.getY(),target.getX(),target.getY(), target.subtract(source).normalize()));
        return target.subtract(source).normalize();
    }

    public abstract int getSpeed();

    public abstract Point2D getVelocityVector();
}
