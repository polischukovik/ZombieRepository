package src;

import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("Generating Characters");
        src.Ash ash = new src.Ash(0, getRandomLocatin());
        src.Human human1 = (src.Human) src.CharacterFactory.create(src.Human.class, getRandomLocatin());
        src.Human human2 = (src.Human) src.CharacterFactory.create(src.Human.class, getRandomLocatin());
        src.Human human3 = (src.Human) src.CharacterFactory.create(src.Human.class, getRandomLocatin());

        src.Zombie zombie1 = (src.Zombie) src.CharacterFactory.create(src.Zombie.class, getRandomLocatin());
        src.Zombie zombie2 = (src.Zombie) src.CharacterFactory.create(src.Zombie.class, getRandomLocatin());

        Set<? extends src.Character> characters = new HashSet<>(Arrays.asList(ash, human1, human2, human3, zombie1, zombie2));

        src.Simulator simulator = new src.Simulator(new src.Scene(characters));

        System.out.println("------------------------------------------");
        System.out.println("Playing simulation");
        simulator.nextStep();

    }

    public static Point2D getRandomLocatin(){
        return new Point2D(
                ThreadLocalRandom.current().nextInt(src.Constants.ZONE_HEIGTH + 1),
                        ThreadLocalRandom.current().nextInt(src.Constants.ZONE_WIDTH + 1));
    }
}
