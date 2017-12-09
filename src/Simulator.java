public class Simulator {
    private int turns = 0;

    private Scene scene = null;

    public Simulator(Scene scene) {
        this.scene = scene;
        init();
    }

    private void init() {
        scene.getZombies()
                .forEach(z -> z.prepare(scene.getHumans()) );
    }

    public void nextStep(){
        //Zombies move towards their targets.
        scene.getZombies().parallelStream().forEach(z -> z.moveTo(z.nextPosition, 1));
        //Ash moves towards his target.

        //Any zombie within a 2000 unit range around Ash is destroyed.

        //Zombies eat any human they share coordinates with.

        ++turns;
    }


    /**
     * Get the number of turns this Simaltor has passed
     * @return - the number of turns passed
     */
    public int getTurnCount(){
        return turns;
    }
}
