package population;

import java.util.Random;
import java.util.Vector;

/**
 * Represent something who can be placed on 2D plan
 */
public abstract class Entity implements Walker, Living {

    /**
     * Set number of pixel moved per year
     */
    protected static final int RAPIDITY = 1;
    /**
     * Allow uman to find the closest human only from a given perimeter. In this
     * way we reduce amount of geometry computed.
     */
    protected static final int VISION = 20;

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
    public void setCoordinates(int _x, int _y) {
        if (_x >= 0 && _x <= xMax) {
            x = _x;
        }

        if (_y >= 0 && _y <= yMax) {
            y = _y;
        }
    }

    public String getCoordinates() {
        return String.format("%s*%s", x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
            _x += RAPIDITY;
        } else if (entity.x != _x) {
            _x -= RAPIDITY;
        }

        if (entity.y > _y) {
            _y += RAPIDITY;
        } else if (entity.y != _y) {
            _y -= RAPIDITY;
        }

        setCoordinates(_x, _y);
    }

    /**
     * Return the closet human on the map
     *
     * @return
     */
    public Human findClosestHuman() throws Exception {
        int count = Human.count();

        if (count < 2) {
            throw new Exception("There are not enought human");
        }

        Vector<Human> humans = Human.getInstances();

        double[] distances = new double[count];

        // compute distances between all humans
        for (int i = 0; i < count; i++) {
            Human human = humans.get(i);
            if (!this.equals(human) && canISeeHim(human)) {
                distances[i] = this.distanceFrom(humans.get(i));
            } else {
                distances[i] = 100000;
            }
        }

        // find lowest index
        double lowest = 100000;
        int lowestIndex = 1;
        for (int i = 0; i < count; i++) {
            if (distances[i] < lowest) {
                lowest = distances[i];
                lowestIndex = i;
            }
        }

        return humans.get(lowestIndex);
    }

    /**
     * Distance between A & B are equal to square of (xb -xa)^2 + (ya-yb)^2
     *
     * @param h
     * @see
     * https://fr.wikipedia.org/wiki/Distance_entre_deux_points_sur_le_plan_cart%C3%A9sien
     * @param human
     * @return distance from human
     */
    protected double distanceFrom(Entity h) {
        double xPow = Math.pow((h.x - this.x), 2);
        double yPow = Math.pow((h.y - this.y), 2);

        return Math.sqrt((xPow + yPow));
    }

    public boolean canISeeHim(Human human) {
        int xDist = Math.abs(x - human.x);
        int yDist = Math.abs(y - human.y);

        return xDist < VISION && yDist < VISION;
    }

}
