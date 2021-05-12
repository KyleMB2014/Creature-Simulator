/*
            Future Changes: 1) Begin relocating basic framework for AI's to this class.
                            2) Utilize readability and changeability.
                            3)
 */

interface BasicAI {


    abstract Food foodDetection();

    abstract void foodLocated();

    abstract boolean paths(String direction);

    abstract void restart();

    abstract void removed();

    abstract boolean isSpawned();

    abstract String creatureLocation();

    abstract int getX();

    abstract int getY();

    abstract Creature reproduce();

    abstract void move();





}
