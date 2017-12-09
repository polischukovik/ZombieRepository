import javafx.geometry.Point2D;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("Generating Characters");
        Ash ash = new Ash(0, getRandomLocatin());
        Human human1 = (Human) CharacterFactory.create(Human.class, getRandomLocatin());
        Human human2 = (Human) CharacterFactory.create(Human.class, getRandomLocatin());
        Human human3 = (Human) CharacterFactory.create(Human.class, getRandomLocatin());

        Zombie zombie1 = (Zombie) CharacterFactory.create(Zombie.class, getRandomLocatin());
        Zombie zombie2 = (Zombie) CharacterFactory.create(Zombie.class, getRandomLocatin());

        Set<? extends Character> characters = new HashSet<>(Arrays.asList(ash, human1, human2, human3, zombie1, zombie2));

        Simulator simulator = new Simulator(new Scene(characters));

        System.out.println("------------------------------------------");
        System.out.println("Playing simulation");
        simulator.nextStep();

    }

    public static Point2D getRandomLocatin(){
        return new Point2D(
                ThreadLocalRandom.current().nextInt(Constants.ZONE_HEIGTH + 1),
                        ThreadLocalRandom.current().nextInt(Constants.ZONE_WIDTH + 1));
    }
}
