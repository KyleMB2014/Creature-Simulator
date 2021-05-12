import java.util.ArrayList;
/*
    Future Changes: 1) Implement pathing algorithm so that predators can target nearby animals further out.
                    2) Implement hereditary genes and traits.
                    3) Explore possibility of aggression trait - whereby predators can begin targeting other predators.
                    4) Explore possibility of predator hunger levels having an impact on the amount of energy they have.
                    5) Modify reproduction so that predators must seek out other predators before reproduction
                       can take place.
                    6) Explore possibility if implementing sex of predator.

 */
public class Predator implements PredatorAI{

    private int x;
    private int y;
    protected int eaten;
    public int maxEnergy;
    public int speed;
    public World world;
    public static int predatorsSpawned;
    private boolean hardy = false;

    public Predator(int speed, int maxEnergy, World world) {
        this.maxEnergy = maxEnergy;
        this.speed = speed;
        this.world = world;
        this.eaten = 0;
        this.x = world.randomizer();
        this.y = world.randomizer();
    }

    public Predator(int speed, int maxEnergy, World world, boolean hardy) {

        this.hardy = true;
        this.speed = speed;
        this.maxEnergy = maxEnergy;
        this.world = world;
        this.eaten = 0;
        this.x = world.randomizer();
        this.y = world.randomizer();
    }
/*
        Keeps the predator within the bounds of the world while moving. Note:I removed this for testing and the predators
        went well outside the bounds.
 */
    public boolean paths(String direction) {
        switch (direction) {
            case "up": if (y + 1 > world.findWorldSize()) { return false; }
                break;
            case "down": if (y - 1 < 0) { return false; }
                break;
            case "left": if (x - 1 < 0) { return false; }
                break;
            case "right": if (x + 1 > world.findWorldSize()) { return false; }
                break;
        }
        return true;
    }
/*
        Determines how the predator moves. This is an exact copy of how the creatures move,
        but instead of food, predators seek out and eat creatures.
 */
    public void move() {
        int direction;
        int energy = maxEnergy;
        while (energy > 0) {
            energy -= speed;
            direction = (int) (4 * Math.random());
            for (int i = 0; i < speed; i++) {
                if (foodDetection()) {
                    ArrayList<Creature> creatures = world.getCreatureList();
                    //System.out.println("Moving to food, My Location: " + x + "," + y);
                    for (Creature c : creatures) {
                        if (c.isStealth()) break;
                        else if (c.getX() - x == - 1) { x--; break; }
                        else if (c.getX() - x == 1) { x++; break; }
                        else if (c.getY() - y == -1) { y--; break; }
                        else if (c.getY() - y == 1) { y++; break; }
                    }
                    foodLocated();
                } else {
                    if (direction == 0 && paths("up")) { y++; }
                    else if (direction == 1 && paths("down")) { y--; }
                    else if (direction == 2 && paths("left")) { x--; }
                    else if (direction == 3 && paths("right")) { x++; }
                    else direction = (int) (4 * Math.random());
                }
            }
        }
    }
/*
        Determines if a creature is nearby.
 */
    public void foodLocated() {
        ArrayList<Creature> creatures = world.getCreatureList();
        for (Creature c : creatures) {
            if (c.creatureLocation().equals(predatorLocation())) {
                //System.out.println("Eating Creature: " + c.creatureLocation());
                c.removed();
                eaten++;
            }
        }
        for (int j = 0; j < world.getCreatureList().size(); j++) {
            if (!world.getCreatureList().get(j).isSpawned()) {
                world.despawnCreature(world.getCreatureList().get(j));
            }
        }
    }
/*
        Determines if there are nearby creatures to the predator and notifies if there is.
 */
    public boolean foodDetection() {
        ArrayList<Creature> creatures = world.getCreatureList();
        for (Creature c : creatures) {
            if (c.creatureLocation().equals((x + 1) + "," + y) || c.creatureLocation().equals((x - 1) + "," + y) ||
                    c.creatureLocation().equals(x + "," + (y + 1)) || c.creatureLocation().equals(x + "," + (y - 1))) {
                //System.out.println("Creature Found: " + c.creatureLocation());
                return true;
            }
        }
        return false;
    }
/*
          Resets the predators after each generation and on spawn.
 */
    public void restart() {
        eaten = 0;
    }
/*
        Determines if predators can reproduce based on food intake and if so, gives a percentage chance
        for a new predator to spawn.
 */
    public Predator reproduce() {
        if(eaten < 2) return null;
        else {
            if ((int) (Math.random() * 100) >= 80) {
                predatorsSpawned++;
                return new Predator(((int) ((speed + Math.random()) * 5)), ((int) (maxEnergy + Math.random()
                        * 50)), world, true);
            }
            predatorsSpawned++;
            return new Predator(speed, maxEnergy, world);
        }
    }

    public int getX(){ return this.x; }

    public int getY(){ return this.y; }

    public String predatorLocation(){ return "" + x + "," + y; }

    public static int reproduced(){ return predatorsSpawned; }

}
