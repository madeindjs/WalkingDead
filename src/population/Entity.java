package population;

import java.util.Random;

/**
 * Represent something who can be placed on 2D plan
 */
public abstract class Entity implements Walker, Living {

    /**
     * Set number of pixel moved per year
     */
    protected static final int RAPIDITY = 1;

    /**
     * Position on X axe
     */
    public int x = 0;

    /**
     * Position on Y axe
     */
    public int y = 0;

    protected static final int xMax = 400;
    protected static final int yMax = 400;

    /**
     * Set a random position on the map
     */
    protected void setRandomPosition() {
        Random rand = new Random();
        x = rand.nextInt(xMax);
        y = rand.nextInt(yMax);
    }

    /**
     * Check & set coordinates
     *
     * @param _x
     * @param _y
     */
    protected void setCoordinates(int _x, int _y) {
        if (_x > 0 && _x < xMax) {
            x = _x;
        }

        if (_y > 0 && _y < yMax) {
            y = _y;
        }
    }

    /**
     * Move completly randomnely on the 2D plan
     */
    @Override
    public void move() {
        Random rand = new Random();
        int directionX = rand.nextBoolean() ? 1 : -1;
        int directionY = rand.nextBoolean() ? 1 : -1;

        int _x = x + (RAPIDITY * directionX);
        int _y = y + (RAPIDITY * directionY);

        setCoordinates(_x, _y);
    }

    /**
     * Move in direction of the given entity
     *
     * @param entity
     */
    protected void moveTo(Entity entity) {
        int _x = x;
        int _y = y;

        if (entity.x > _x) {
            _x++;
        } else {
            _x--;
        }

        if (entity.y > _y) {
            _y++;
        } else {
            _y--;
        }

        setCoordinates(_x, _y);
    }

}
