package population;

import java.util.Random;

/**
 * Represent something who can be placed on 2D plan
 */
public class Point {

    public int x = 0;
    public int y = 0;

    protected static final int xMax = 400;
    protected static final int yMax = 400;

    protected void setRandomPosition() {
        Random rand = new Random();
        x = rand.nextInt(xMax);
        y = rand.nextInt(yMax);
    }

}
