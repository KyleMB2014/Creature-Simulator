import java.util.ArrayList;
/*
    Future Changes: 1) Explore possibility of implementing sex of creatures.
                    2) Explore possibility of requiring creatures to seek out other creatures for reproduction to take
                       place.
                    3) Add additional traits. Possibilities: hunger, predator detection, stealthiness, robustness.
                    4) Implement death script where if creature does not find food it has a chance of starving.
                    5) Explore possibility of sickness - can spread to predators who eat sick creatures.
 */
public class Creature implements BasicAI{
        public int speed;
        public int maxEnergy;
        public World world;
        protected int eaten;
        private int x;
        private int y;
        private boolean spawned = true;
        private static int creaturesSpawned;
        private boolean stealth;

        public Creature(int speed, int maxEnergy, World world) {
                this.speed = speed;
                this.maxEnergy = maxEnergy;
                this.world = world;
                this.eaten = 0;
                this.x = world.randomizer();
                this.y = world.randomizer();

        }

        public Creature(int speed, int maxEnergy, World world, boolean stealth){
                this.speed = speed;
                this.maxEnergy = maxEnergy;
                this.world = world;
                this.eaten = 0;
                this.x = world.randomizer();
                this.y = world.randomizer();
                this.stealth = true;
        }
        /*
                Creatures are able to detect food up to one tile away
         */
        public Food foodDetection() {
                ArrayList<Food> food = world.getFoodList();
                for (Food f : food) {
                        if (f.foodLocation().equals((x + 1) + "," + y) || f.foodLocation().equals((x - 1) + "," + y) ||
                                f.foodLocation().equals(x + "," + (y + 1)) || f.foodLocation().equals(x + "," + (y - 1))) {
                                //System.out.println("Food Found: " + f.foodLocation());
                                return f;
                        }
                }
                return null;
        }

        /*
                Creature finds the food and then eats the food, removing it from the world.

         */
        public void foodLocated() {
                ArrayList<Food> food = world.getFoodList();
                for (Food f : food) {
                        if (f.foodLocation().equals(creatureLocation())) {
                                //System.out.println("Eating Food: " + f.foodLocation());
                                f.removed();
                                eaten++;
                        }
                }
                for (int j = 0; j < world.getFoodList().size(); j++) {
                        if (!world.getFoodList().get(j).spawned()) {
                                world.despawnFood(world.getFoodList().get(j));
                        }
                }
        }

        /*
                Determines possible paths for Creatures to take, making sure that they are
                within the bounds of the world.
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
                Resets Creature location on spawn or reproduction
         */
        public void restart() {
                eaten = 0;
        }

        /*
                Determines how Creatures move, direction chosen, and if food has been located/eaten
         */
        public void move() {
                int direction;
                int energy = maxEnergy;
                while (energy > 0) {
                        energy -= speed;
                        direction = (int) (4 * Math.random());
                        for (int i = 0; i < speed; i++) {
                                if (foodDetection()) {
                                        ArrayList<Food> food = world.getFoodList();
                                        //System.out.println("Moving to food, My Location: " + x + "," + y);
                                        for (Food f : food) {
                                                if (f.getX() - x == - 1) { x--; break; }
                                                else if (f.getX() - x == 1) { x++; break; }
                                                else if (f.getY() - y == -1) { y--; break; }
                                                else if (f.getY() - y == 1) { y++; break; }
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
                Determines if Creatures can reproduce depending on food intake
         */
        public Creature reproduce() {
                if (eaten < 2) return null;
                else {
                        if ((int) (Math.random() * 100) >= 80) {
                                creaturesSpawned++;
                                world.appendCreatureList(((int)((speed + Math.random())*5)), ((int)(maxEnergy + Math.random()
                                *50)), world, (Math.random() * 2) > 1);
                        }
                        creaturesSpawned++;
                        return new Creature(speed, maxEnergy, world, stealth);
                }
        }

        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public String creatureLocation() {
                return "" + x + "," + y;
        }

        public boolean isSpawned(){
                return spawned;
        }

        public void removed(){
                spawned = false;
        }

        public static int cReproduced(){
                return creaturesSpawned;
        }

        public boolean isStealth(){ return this.stealth; }
}