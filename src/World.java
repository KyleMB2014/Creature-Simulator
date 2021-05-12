import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/*
    Future Changes: 1) Study up on other possible engines or GUI's instead of Java Swing.
                    2) Look into multi-threading to see if possible for this application.
                    3) Explore possibility if implementing time aspect, where creatures can live by time.
                    4) Explore replacements for ArrayList's.
                    5) Look into java swing animations to see if showing iterations as they happen is possible.

    Notes:: ArrayList's have limitations in that there are issues with modifying them while iterating over them. This
            can be overcome by clever manipulation, but requires extra work.
 */
import static java.awt.Color.*;

public class World extends JComponent {

    final private int worldSize;
    final private int foodCap;
    public static ArrayList<Food> foodList;
    private ArrayList<Creature> creatureList;
    private ArrayList<Predator> predatorList;
    private ArrayList<EnhancedCreature> enhancedCreatureList;
    private ArrayList<SuperPredator> spList;

    private World(int foodCap, int worldSize, ArrayList<Food> foodList, ArrayList<Creature> creatureList, ArrayList<Predator> predatorList,
                 ArrayList<EnhancedCreature> enhancedCreatureList, ArrayList<SuperPredator> spList){
        this.foodCap = foodCap;
        this.worldSize = worldSize;
        this.foodList = foodList;
        this.creatureList = creatureList;
        this.predatorList = predatorList;
        this.enhancedCreatureList = enhancedCreatureList;
        this.spList = spList;
    }
/*
            JavaSwing component that displays what is happening with the creatures/predators
 */
    public void paintComponent(Graphics g){
        Graphics2D gg = (Graphics2D) g;

            for (Creature creature: creatureList) {
                int creature_Location_x = creature.getX();
                int creature_Location_y = creature.getY();

                gg.setColor(BLUE);
                gg.drawOval(creature_Location_x, creature_Location_y, 5, 5);
                gg.fillOval(creature_Location_x, creature_Location_y, 5, 5);
            }

            for(Predator predator : predatorList){
                int predator_Location_x = predator.getX();
                int predator_Location_y = predator.getY();

                gg.setColor(RED);
                gg.drawOval(predator_Location_x, predator_Location_y, 5, 5);
                gg.fillOval(predator_Location_x, predator_Location_y, 5, 5);
            }

            for(EnhancedCreature creature : enhancedCreatureList){
                int enhanced_Location_x = creature.getX();
                int enhanced_Location_y = creature.getY();

                gg.setColor(YELLOW);
                gg.drawOval(enhanced_Location_x, enhanced_Location_y, 5, 5);
                gg.fillOval(enhanced_Location_x, enhanced_Location_y, 5, 5);
            }

            for(SuperPredator sp : spList){
                int sp_Location_x = sp.getX();
                int sp_Location_y = sp.getY();

                gg.setColor(BLACK);
                gg.drawOval(sp_Location_x, sp_Location_y, 5, 5);
                gg.fillOval(sp_Location_x, sp_Location_y, 5, 5);
            }

            for (Food food: foodList) {
                int food_location_x = food.getX();
                int food_location_y = food.getY();

                gg.setColor(GREEN);
                gg.drawOval(food_location_x, food_location_y, 5, 5);
                gg.fillOval(food_location_x, food_location_y, 5, 5);
            }
        }

    public int foodAmount(){
        return foodList.size();
    }

    public int findWorldSize(){
        return worldSize;
    }

    public ArrayList<Food> getFoodList(){
        return foodList;
    }

    public ArrayList<Creature> getCreatureList(){
        return creatureList;
    }

    public ArrayList<EnhancedCreature> getEnhancedCreatureList(){
        return enhancedCreatureList;
    }

    public void appendCreatureList(int speed, int maxEnergy, World world, boolean stealth){
        this.enhancedCreatureList.add(new EnhancedCreature(speed, maxEnergy, world, stealth));
    }

    public ArrayList<Predator> getPredatorList(){
        return predatorList;
    }

    public ArrayList<SuperPredator> getSpList(){
        return spList;
    }

    public void despawnFood(Food f){
        foodList.remove(f);
    }

    public void despawnCreature(Creature c){
        creatureList.remove(c);
    }

    public void despawnPredator(Predator p){
        predatorList.remove(p);
    }

    public int randomizer(){
        return (int)(worldSize * Math.random());
    }

}
