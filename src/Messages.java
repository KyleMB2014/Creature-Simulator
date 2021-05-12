import javax.swing.*;
import java.util.ArrayList;

public class Messages {

    private static JTextArea jt;
    private static int foodCap;
    private static int creatureCap;
    private static int enhancedCreatureCap;
    private static int predatorCap;
    private static int superPCap;
    private static int worldSize;
    private static ArrayList<Creature> creatureList;
    private static ArrayList<Predator> predatorList;
    private static ArrayList<EnhancedCreature> enhancedCreatureList;
    private static ArrayList<SuperPredator> spList;

    public Messages(JTextArea jt, int foodCap, int creatureCap, int enhancedCreatureCap, int predatorCap, int superPCap,
                    int worldSize, ArrayList<Creature> creatureList, ArrayList<Predator> predatorList,
                    ArrayList<EnhancedCreature> enhancedCreatureList, ArrayList<SuperPredator> spList) {
        this.jt = jt;
        this.foodCap = foodCap;
        this.creatureCap = creatureCap;
        this.enhancedCreatureCap = enhancedCreatureCap;
        this.predatorCap = predatorCap;
        this.superPCap = superPCap;
        this.worldSize = worldSize;
        this.creatureList = creatureList;
        this.predatorList = predatorList;
        this.enhancedCreatureList = enhancedCreatureList;
        this.spList = spList;

    }

    public static void Greetings(){
        System.out.println("\t\tGreetings! Welcome to Creature Simulator!\n");
        System.out.println("\t\t\tHere's how it works:\n");
        System.out.println("\tThis world is populated with different types of creatures that have the soul purpose");
        System.out.println("\tseeking out food to survive and reproducing to continue on their species.");
        Main.pause(6);
        jt.setText(null);
        System.out.println("\n\n\tEach creature has the chance of passing on their traits to their offspring. There is");
        System.out.println("\talso the possibility of new creatures mutating to be born with new genes, increasing");
        System.out.println("\ttheir survivability.");
        Main.pause(6);
        jt.setText(null);
        System.out.println("\n\n\n\t\t\tLet us begin...");
        Main.pause(6);
        jt.setText(null);
        System.out.println("\n\nInitializing world with:\n");
        System.out.println(worldSize + " x " + worldSize + " world.");
        System.out.println(foodCap + " food");
        System.out.println(creatureCap + " creatures");
        System.out.println(enhancedCreatureCap + " enhanced creatures.");
        System.out.println(predatorCap + " predators");
        System.out.println(superPCap + " super predators");
        Main.pause(6);
        jt.setText(null);
    }

    public static void updateInfo(){
        Main.pause(3);
        System.out.println("Current Creatures: " + creatureList.size());
        System.out.println("Current Enhanced Creatures: " + enhancedCreatureList.size());
        System.out.println("Current Predators: " + predatorList.size());
        System.out.println("Current Super Predators: " + spList.size());
        System.out.println("Creatures reproduced: " + Creature.cReproduced());
        System.out.println("Enhanced Creatures reproduced: "+ EnhancedCreature.enReproduced());
        System.out.println("Predators reproduced: " + Predator.reproduced());
        System.out.println("Super Predators reproduced: " + SuperPredator.spReproduced());
        Main.pause(3);
    }

}
