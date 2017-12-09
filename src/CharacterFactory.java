package src;

import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {

    public static Map<Class<? extends Character>, Integer> sequence = new HashMap<>();

    public static Character create(Class<? extends Character> clazz, Point2D position) {
        if(!sequence.containsKey(clazz)){
            sequence.put(clazz, 0);
        }

        int id = sequence.get(clazz);

        Character character = null;

        if(clazz.isAssignableFrom(Human.class)){
            character = new Human(id, position);
        } else if(clazz.isAssignableFrom(Zombie.class)) {
            character = new Zombie(id, position);
        } else{
            throw new IllegalArgumentException("Unknown requested Character type");
        }

        sequence.put(clazz, sequence.get(clazz) + 1);
        return character;
    }
}
