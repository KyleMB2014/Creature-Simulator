import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
/*
    Future Changes: 1) Change user input to include input on panel2 of JFrame instead of stand-alone pop-up windows.
                    2) Create catches for inputs that would cause parameters to go outside the bounds.
                    3) Explore possible multi-threading, if possible, to have creature move in unison.
                    4) Modify JFrame so that world size may be changed.
                    5) Clean up swing implements to be more user friendly overall and cleaner.
                    6) Consolidate JFrame implements into a class of its own.

 */
public class Main {

    public static void main(String[] args){
/*
        String str1 = JOptionPane.showInputDialog("Enter the amount of food you want available (1-300)");
        String str2 = JOptionPane.showInputDialog("Enter the number of starting creatures (1-300)");
        String str6 = JOptionPane.showInputDialog("Enter the number of starting enhanced creatures (1-100)");
        String str3 = JOptionPane.showInputDialog("Enter the number of starting predators (1-100)");
        String str5 = JOptionPane.showInputDialog("Enter the number of starting super predators (1-50)");
        String str4 = JOptionPane.showInputDialog("Enter the number of generations (1-100)");
*/
/*
            Parameters of the world. World Size should be left alone at this moment for changing can move it
            outside the bounds of the JFrame.
            Recommended Parameters:
                0 < foodCap < 400;
                0 < predatorCap < 100;
                0 < creatureCap < 300;
                0 < enhancedCreatureCap < 100;
                0 < superPCap < 60;
                1 < generations < 20;
            Generations can take considerable time to complete if near max creatures/predators are present within
            the world.
 */
        int worldSize = 580; //Leave this at 580 for the time being. Changing it now could cause issues.
        int foodCap = 300;
        int predatorCap = 20;
        int creatureCap = 300;
        int enhancedCreatureCap = 50;
        int superPCap = 10;
        int generations = 10;
        //int foodCap = Integer.parseInt(str1);
        //int creatureCap = Integer.parseInt(str2);
        //int enhancedCreatureCap = Integer.parseInt(str6);
        //int predatorCap = Integer.parseInt(str3);
        //int generations = Integer.parseInt(str4);
        //int superPCap = Integer.parseInt(str5);
/*
            Establishes all arrays to be used within the program, then creates the world based on given parameters.
 */
        ArrayList<Creature> creatureList = new ArrayList<>();
        ArrayList<Predator> predatorList = new ArrayList<>();
        ArrayList<EnhancedCreature> enhancedCreatureList = new ArrayList<>();
        ArrayList<SuperPredator> spList = new ArrayList<>();
        ArrayList<Food> foodList = new ArrayList<>();

        World world = new World(foodCap, worldSize, foodList, creatureList, predatorList, enhancedCreatureList, spList);
/*
            Start of JFrame establishment. First defining of its parameters.
 */
        JFrame frame = new JFrame("Creature Simulator");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.GRAY);
/*
            JTextArea for diverting output from console to JFrame
 */
        JTextArea jt = new JTextArea(10,60);
        PrintStream printStream = new PrintStream(new CustomOutputStream(jt));
        System.setOut(printStream);
        System.setErr(printStream);
        Messages messages = new Messages(jt, foodCap, creatureCap, enhancedCreatureCap, predatorCap, superPCap,
                worldSize, creatureList, predatorList, enhancedCreatureList, spList);
/*
            Creating Scrollable JTextArea to display diverted output
 */
        JScrollPane scroll = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.add(scroll);
/*
            Labels created for JFrame
 */
        JLabel label1 = new JLabel("Creature");
        label1.setText("Blue Dots Are Creatures");
        label1.setForeground(Color.BLUE);
        panel2.add(label1);
        JLabel label2 = new JLabel("Predator");
        label2.setText("Red Dots Are Predators");
        label2.setForeground(Color.RED);
        panel2.add(label2);
        JLabel label3 = new JLabel("Enhanced Creature");
        label3.setText("Yellow Dots Are Enhanced Creatures");
        label3.setForeground(Color.YELLOW);
        panel2.add(label3);
        JLabel label9 = new JLabel("Super Predators");
        label9.setText("Black dots are Super Predators");
        label9.setForeground(Color.BLACK);
        panel2.add(label9);
        JLabel label4 = new JLabel("Blank");
        label4.setText("--------------------------------");
        panel2.add(label4);
        JLabel label5 = new JLabel("Explanation");
        label5.setText("Creatures eat food (green dots)");
        panel2.add(label5);
        JLabel label6 = new JLabel("Explanation 2");
        label6.setText("Predators eat creatures.");
        panel2.add(label6);
        JLabel label7 = new JLabel("Explanation 3");
        label7.setText("Enh. Creatures eat food.");
        panel2.add(label7);
        JLabel label8 = new JLabel("Explanation 4");
        label8.setText("Super Predators eat everything.");
        panel2.add(label8);
        JLabel label10 = new JLabel("Explanation 5");
        label10.setText("World will update between each iteration of creatures");
        label10.setForeground(Color.RED);
        panel1.add(label10);


/*
            Modifying JFrame by adding new panels, modifying colors, establishing borders, and
            centering it upon opening.
 */
        panel1.setBackground(Color.ORANGE);
        panel2.setBackground(Color.ORANGE);
        panel1.setPreferredSize(new Dimension(20,215));
        panel2.setPreferredSize(new Dimension(220, 200));
        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.add(panel1, BorderLayout.SOUTH);
        frame.add(panel2, BorderLayout.EAST);
        frame.setSize(820, 840);
        frame.add(world, BorderLayout.CENTER);
        world.setBounds(0, 400, 400, 400);
        world.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
/*
            Message to the user telling them about the program.
 */
        Messages.Greetings();
/*
            Continuation of Main class code. Establishes all arrays based on specified parameters.
 */
        for(int i = 0; i < foodCap; i++){
            foodList.add(new Food((int)(worldSize * Math.random()), (int)(worldSize * Math.random())));
        }

        for(int i = 0; i < creatureCap; i++){
            creatureList.add(new Creature(5, 1000, world));
        }

        for(int i = 0; i < enhancedCreatureCap; i++){
            enhancedCreatureList.add(new EnhancedCreature(5, 1200, world, false));
        }

        for(int i = 0; i < predatorCap; i++){
            predatorList.add(new Predator(8, 1200, world));
        }

        for(int i = 0; i < superPCap; i++){
            spList.add(new SuperPredator(10, 1600, world, false));
        }
        world.repaint();
/*
            Actually start of the main program. Starts each generation with a full board of food,
            then pauses for 3 seconds between each generation.
 */
        for(int i = 0; i < generations; i++){
            System.out.println("\n");
            System.out.println("Generation Start " + i + "\n");
            System.out.println("\n");
            pause(2);
            if(world.foodAmount() < foodCap){
                System.out.println("Food resetting...");
                for(int j = world.foodAmount(); j < foodCap; j++){
                    World.foodList.add(new Food((int)(worldSize * Math.random()), (int)(worldSize * Math.random())));
                }
                world.repaint();
            }

            System.out.println("Food: " + world.getFoodList().size());
            System.out.println("\n");
            System.out.println("Iterating Creatures...");
/*
                Iterating Creatures
 */
            for(Creature c : creatureList){
                c.restart();
                c.move();
                //world.repaint();
            }
            System.out.println("Updating...");
            world.repaint();
            pause(1);
            System.out.println("Iterating Predators...");
            pause(1);
/*
                Iterating Predators
 */
            for(Predator p : predatorList){
                p.restart();
                p.move();
                //world.repaint();
            }
            System.out.println("Updating...");
            world.repaint();
            pause(1);
            System.out.println("Iterating Enhanced Creatures...");
            pause(1);
/*
                Iterating Enhanced Creatures
 */
            for(EnhancedCreature e : enhancedCreatureList){
                e.restart();
                e.move();
                //world.repaint();
            }
            System.out.println("Updating...");
            world.repaint();
            pause(1);
            System.out.println("Iterating Super Predators...");
            pause(1);
/*
                Iterating Super Predators
 */
            for(SuperPredator sp : spList){
                sp.restart();
                sp.move();
                //world.repaint();
            }
            System.out.println("Updating...");
            world.repaint();
            pause(1);
/*
                Checks to see if creatures, predators, or enhanced creatures are capable of replicating. This will be
                changed later to ensure animals seek each other out to breed instead of just self-replicating.
 */
            for(int j = 0; j < creatureList.size(); j++){
                Creature creatureOffSpring = creatureList.get(j).reproduce();
                if(creatureOffSpring !=null){
                    creatureList.add(creatureOffSpring);
                    //System.out.println("Creature Spawned");
                }
            }
            for(int k = 0; k < predatorList.size(); k++){
                Predator predatorOffSpring = predatorList.get(k).reproduce();
                if(predatorOffSpring != null){
                    predatorList.add(predatorOffSpring);
                    //System.out.println("Predator Spawned");
                }
            }
            for(int h = 0; h < enhancedCreatureList.size(); h++){
                var enhancedOffSpring = enhancedCreatureList.get(h).reproduce();
                if(enhancedOffSpring != null){
                    enhancedCreatureList.add(enhancedOffSpring);
                    //System.out.println("Enhanced Creature Spawned");
                }
            }
            for(int l = 0; l < spList.size(); l++){
                SuperPredator spOffSpring = spList.get(l).reproduce();
                if(spOffSpring != null){
                    spList.add(spOffSpring);
                    //System.out.println("Super Predator Spawned");
                }

            }
/*
                End of a generation. Pauses for 3 seconds before starting a new and displays updated info to user.
 */
            System.out.println("Generation Complete " + i + "\n");
            Messages.updateInfo();


        }
/*
                Displays the end result to the user after final iteration. Will be changed later to provide updates
                between each generation.
 */
        System.out.println("\n");
        System.out.println("-----------------");
        System.out.println("End of program...");
    }

    public static void pause(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
