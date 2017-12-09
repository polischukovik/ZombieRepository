import java.util.Set;
import java.util.stream.Collectors;

public class Scene {
    private Set<? extends Character> characters = null;

    public Scene(Set<? extends Character> characters) {
        this.characters = characters;
    }

    public Set<? extends Character> getAllCharacters() {
        return characters;
    }

    public Set<Zombie> getZombies() {
        return characters.stream()
                .filter(f -> f.getClass().isAssignableFrom(Zombie.class))
                .map( m -> (Zombie) m)
                .collect(Collectors.toSet());
    }

    public Set<Human> getHumans() {
        return characters.stream()
                .filter(f -> f.getClass().isAssignableFrom(Human.class))
                .map( m -> (Human) m)
                .collect(Collectors.toSet());
    }

    public Ash getAsh() {
        return characters.stream()
                .filter(f -> f.getClass().isAssignableFrom(Ash.class))
                .map( m -> (Ash) m)
                .findFirst().get();
    }
}
