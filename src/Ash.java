package src;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Set;

class Ash extends src.Human {
	int speed = 1000; 
	int shootingRange = 2000;
	
	public Ash(int id, Point2D position) {
		super(id, position);
		//System.err.println(this.toString());
	}

	public void updatePosition(Point2D position){
		this.position = position;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public Point2D getVelocityVector() {
		throw new IllegalArgumentException("Not applicable for Ash");
	}
	
	public Point2D intercept(ArrayList<src.Zombie> targets){
		src.Zombie target = targets.get(0);
		System.err.println("Intercepting zombie " + target.id);
		Point2D Pa = this.position;
		// consider shotgun range when no next target
//		Pa = (targets.size() == 0) ? this.move(this.getDirection(target.position).multiply(this.shootingRange), 1)
//				: this.position;
		if(targets.size() < 2){
			System.err.println("Last zombie");
			Pa = this.move(this.getDirection(target.position).multiply(this.shootingRange), this.speed);
		}
		
		double Vzm = target.getSpeed();
		double Vam = this.getSpeed();
		Point2D D = Pa.subtract(target.position);
		Point2D Vz = target.getVelocityVector();
		double Dm = D.magnitude();
		
		double a = Vam*Vam - Vzm*Vzm;
		double b = 2*(D.dotProduct(Vz));
		double c = -Dm*Dm;
		
		Set<Double> timePair= null;
		try{
			timePair = src.Utils.getQuadraticRoots(a, b, c);
		}catch(IllegalArgumentException e){
			System.err.println(e.getMessage());
			throw new IllegalArgumentException("Imposible to reach target");
		}	
		
		
		double time = timePair.stream().filter(p -> p > 0).min(Double::compare).get(); 
		System.err.println("Time to reach target: " + time);
		
		Point2D intersection = target.move(Vz, time);
		// consider shotgun range when there is next target
//		intersection = (targets.size() == 0) ? intersection : Character.move(intersection
//				, Character.getDirection(intersection, targets.getFirst().position), time);
		if(targets.size() >= 2){
			System.err.println("More zombies left - shifting to next target");

			targets.stream().forEach(f -> System.err.println("\t"+f));
			intersection = src.Character.move(intersection, src.Character.getDirection(intersection, targets.get(1).position), this.shootingRange-1500);
		}
		return intersection;
	}
}