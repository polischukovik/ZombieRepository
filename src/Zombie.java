package src;

import javafx.geometry.Point2D;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

class Zombie extends Character {
	private static int speed = 400;
	public Point2D nextPosition;

	public Zombie(int id, Point2D position){
		super(id, position);
	}
	
	public Zombie(int id, Point2D position, Point2D nextPosition) {
		super(id, position);
		this.nextPosition = nextPosition;
		//System.err.println(this.toString() + " nextPosition=" + nextPosition);
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public Point2D getVelocityVector() {
		return nextPosition.subtract(position);
	}

	public void setNextPosition(Point2D nextPosition) {
		this.nextPosition = nextPosition;
	}

	public Human findClosestHuman(Set<Human> humans){
		return humans.stream()
				.collect(Collectors.minBy(Comparator.comparing(this::turnsToReach)))
				.get();
	}

	public void prepare(Set<Human> humans) {
		Point2D nextPosition = this.move(this.findClosestHuman(humans).position, 1);
		this.setNextPosition(nextPosition);
	}

	@Override
	public String toString() {
		return super.toString() + " [nextPosition=" + nextPosition + "]";
	}
}