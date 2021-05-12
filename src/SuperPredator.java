/*
    Future Changes: 1) Fix pathing issue for Super Predators - currently broken and not moving.
                    2) Change algorithm of pathing for all classes so that food detection range can be increased.
                    3) Explore possibility of vectors being utilized.
                    4) Implement hereditary traits that can be passed on to offspring.
                    5) Implement mutations that can cause positive or negative traits.
                    6) Fix inheritance issues with Predator class.

    Bug report: Same problem encountered here as in Creature parent class. Inherited function foodDetection does not
    increment correctly leading super predators to never reproduce.
 */
public class SuperPredator extends Predator{

    private static int spSpawned;
    private boolean aggressive;

    public SuperPredator(int speed, int maxEnergy, World world, boolean aggressive) {
        super(speed, maxEnergy, world);
        this.aggressive = aggressive;
    }
/*
            Overrides parent class function since reproduce is now spawning Super Predators.
 */
    @Override
    public SuperPredator reproduce() {
        if (eaten < 2) { return null; }
        else {
            if ((int) (Math.random() * 100) >= 80) {
                spSpawned++;
                return new SuperPredator(speed + (((int) ((speed + Math.random()) * 5))), ((int) (maxEnergy + Math.random()
                        * 50)), world, true);
            }
            spSpawned++;
            return new SuperPredator(speed, maxEnergy, world, false);
        }
    }

    public static int spReproduced(){ return spSpawned; }
}

