/*
    Future Changes: 1) Fix inheritance issues from Creature class.
                    2) Fix pathing issues so that EC's prioritize certain foods.
                    3) Implement hereditary traits that can be passed on to offspring.
                    4) Implement mutations that can cause positive or negative traits.

    Bug Reports: Eaten is not incrementing like it should from the inherited function foodLocation. Had to copy/paste
    function to this class for it to work correctly. Otherwise, enhanced creatures would never reproduce.

 */
public class EnhancedCreature extends Creature {

    private static int ecSpawned;

    public EnhancedCreature(int speed, int maxEnergy, World world, boolean stealth) {
        super(speed, maxEnergy, world);

    }
/*
            Override parent class function since reproduce is now spawning Enhanced Creatures.
 */
    @Override
    public EnhancedCreature reproduce() {
        if (eaten < 2) { return null; }
        else {
            if ((int) (Math.random() * 100) >= 80) {
                ecSpawned++;
                return new EnhancedCreature(((int) ((speed + Math.random()) * 5)), ((int) (maxEnergy + Math.random()
                        * 50)), world, true);
            }
            ecSpawned++;
            return new EnhancedCreature(speed, maxEnergy, world, false);
        }
    }

    public static int enReproduced(){ return ecSpawned; }
}