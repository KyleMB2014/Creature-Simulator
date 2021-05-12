public class Food {
/*
    Future Changes: 1) Implement different foods that increment eaten differently.
                    2) Explore possibility for animal count to impact food availability.

 */
    private boolean spawned;
    private int x;
    private int y;

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
        this.spawned = true;
    }

    public String foodLocation() {
        return "" + x + "," + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean spawned() {
        return spawned;
    }

    public void removed() {
        spawned = false;

    }
}
