package src;

import javafx.geometry.Point2D;

class Human extends Character {

	public Human(int id, Point2D position) {
		super(id, position);
		//System.err.println(this.toString());
	}

	@Override
	public int getSpeed() {
		return 0;
	}

	@Override
	public Point2D getVelocityVector() {
		return Point2D.ZERO;
	} 
}