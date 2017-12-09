import javafx.geometry.Point2D;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Save humans, destroy zombies!
 **/
class Player {
	static Map<Integer, Zombie> zombies = null;
	static Map<Integer, Human> humans = null;
	static Ash ash = null;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        ArrayList<Zombie> targets = new ArrayList<>();
        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            ash = new Ash(-1, new Point2D(x, y));
            int humanCount = in.nextInt();
            humans = new TreeMap<>();
            for (int i = 0; i < humanCount; i++) {
            	
                int humanId = in.nextInt();                
                int humanX = in.nextInt();
                int humanY = in.nextInt();
                humans.put(humanId, new Human(humanId, new Point2D(humanX, humanY)));
            }
            
            int zombieCount = in.nextInt();
            zombies = new TreeMap<>();
            for (int i = 0; i < zombieCount; i++) {            	
                int zombieId = in.nextInt();
                int zombieX = in.nextInt();
                int zombieY = in.nextInt();
                int zombieXNext = in.nextInt();
                int zombieYNext = in.nextInt();
                zombies.put (zombieId, new Zombie(zombieId, new Point2D(zombieX, zombieY)
                		, new Point2D(zombieXNext, zombieYNext)));
            }

            // Write an action using System.err.println()
            // To debug: System.err.println("Debug messages...");
            System.err.println("============================");            
            //&& !zombies.containsKey(target.id)
            targets = selectTarget();
            System.err.println();
    		
            if(targets != null){
            	double distance = ash.position.distance(targets.get(0).position);
            	System.err.println("Zombie is " + distance + " away");
            	if(distance <= ash.shootingRange){
            		System.err.println("Target zombie is killed");
            		targets.remove(0);
            	}            	
            }                
            
        	Point2D moveTo = ash.intercept(targets);            	
        	System.out.println((int) moveTo.getX() + " " + (int) moveTo.getY());
        }
    }

    public static ArrayList<Zombie> selectTarget(){
    	System.err.println("\tSelecting target...");
    	System.err.print("\t[zombie]=[human:turns to reach human],...");
    	ArrayList<Zombie> list = new ArrayList<>(zombies.values().stream()
    			//.peek(f -> System.err.print("\n\t[" + f.id +"]="))
    			.peek(f -> System.err.print("\n\t[" + f +"]="))
    			.flatMap(z -> humans.values().stream()
    					.peek(h -> System.err.print("[" + h.id + ":" + z.turnsToReach(h) + "]"))
    					.map(h -> new SimpleImmutableEntry<>(z, h)))
    			.sorted((s1,s2)
    					-> Double.compare(s1.getKey().turnsToReach(s1.getValue())
    							, s2.getKey().turnsToReach(s2.getValue())))
    			.map(r -> r.getKey())
    			.collect(Collectors.toList()));
    	list.remove(0);
    	return list;
    }
}