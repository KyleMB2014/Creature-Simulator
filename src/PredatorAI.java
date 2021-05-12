/*
            Explore which method is better, abstract usage in BasicAI or interface usage in Predator AI.
 */
interface PredatorAI {

    boolean paths(String direction);

    void move();

    void foodLocated();

    boolean foodDetection();

    void restart();

    Predator reproduce();

    int getX();

    int getY();

    String predatorLocation();



}
