package src;

import java.util.HashSet;
import java.util.Set;

class Utils{
    public static Set<Double> getQuadraticRoots(double a, double b, double c){
		Set<Double> result = new HashSet<>();
		double d = b*b - 4*a*c;
		
		if(d < 0){
			throw new IllegalArgumentException("Discriminant is negative");
		} else if(d == 0){
			double singleRoot = -b/(2*a);
			result.add(singleRoot);
		} else {
			double x1 = (-b + Math.sqrt(d))/(2*a);
			double x2 = (-b - Math.sqrt(d))/(2*a);
			result.add(x1);
			result.add(x2);
		}
		return result;
	}
}