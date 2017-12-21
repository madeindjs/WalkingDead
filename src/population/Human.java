package population;

import java.util.Objects;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author arousseau
 */
public class Human extends Entity implements Fighter {

    /**
     * Represent all humans
     */
    private static Vector<Human> instances = new Vector();

    private int age;
    private final int maxAge;
    private final Sex sex;

    public int life = 100;

    public static final int AGE_MAJORITY = 18;
    public static final int CHANCES_TO_HAVE_CHILD = 5;
    public static final int RAPIDITY = 2;
    /**
     * Allow uman to find the closest human only from a given perimeter. In this
     * way we reduce amount of geometry computed.
     */
    private static final int VISION = 20;

    public static Vector<Human> getInstances() {
        return instances;
    }

    public static Human getInstance(int index) {
        return instances.get(index);
    }

    /**
     * Create a human with a random sex & random max age
     */
    public Human() {
        Random rand = new Random();

        maxAge = rand.nextInt(120);
        age = 0;
        sex = rand.nextBoolean() ? Sex.Male : Sex.Female;

        setRandomPosition();

        instances.add(this);
    }

    public Human(int _age) {
        this();
        age = _age;
    }

    public void setAge(int _age) {
        age = _age;
    }

    public Human haveSex(Human otherHuman) throws Exception {
        if (sex == otherHuman.sex) {
            throw new Exception("Can't have sex with same sex");
        }

        if (!canHaveSex() || !otherHuman.canHaveSex()) {
            throw new Exception("Father & mother have to be major or to be alive");
        }

        Random rand = new Random();
        if (rand.nextInt(CHANCES_TO_HAVE_CHILD) == 1) {
            return new Human();
        }

        throw new Exception("Do it again...");
    }

    public void addYear() {
        age++;
    }

    public boolean isMajor() {
        return age > AGE_MAJORITY;
    }

    public boolean canHaveSex() {
        return isMajor() && isAlive();
    }

    @Override
    public boolean isAlive() {
        return true;
        // return age < maxAge && life > 0;
    }

    @Override
    public void fight(Human human) {
        throw new UnsupportedOperationException("Human can't fight against human");
    }

    @Override
    public void fight(Zombie zombie) {
        if (isMajor() && isAlive()) {
            zombie.die();
        } else {
            this.die();
        }
    }

    /**
     * Human will try to move next to closest human
     */
    @Override
    public void move() {
        Human closestHuman = findClosestHuman();

        if (closestHuman == this) {
            super.move();
        } else {
            moveTo(closestHuman);
        }

        addYear();
    }

    /**
     * Return the closet human on the map
     *
     * @return
     */
    protected Human findClosestHuman() {
        int count = instances.size();

        // if alone, return him :'(
        if (count < 2) {
            return this;
        }

        double[] distances = new double[count];

        // compute distances between all humans
        for (int i = 0; i < count; i++) {
            Human human = instances.get(i);
            if (this.equals(human) && canISeeHim(human)) {
                distances[i] = this.distanceFrom(instances.get(i));
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

        return instances.get(lowestIndex);
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
    protected double distanceFrom(Human h) {
        double xPow = Math.pow((h.x - this.x), 2);
        double yPow = Math.pow((h.y - this.y), 2);

        return Math.sqrt((xPow + yPow));
    }

    protected boolean canISeeHim(Human human) {

        int xDist = Math.abs(x - human.x);
        int yDist = Math.abs(y - human.y);

        return xDist < VISION && yDist < VISION;
    }

    /**
     * Remove human from instances
     *
     * @todo try to free memory
     */
    @Override
    public void die() {
        instances.remove(this);
        new Zombie(this);
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Human)) {
            return false;
        }
        Human human = (Human) object;

        return human.x != x && human.y != y && human.age == age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.age;
        hash = 79 * hash + this.maxAge;
        hash = 79 * hash + Objects.hashCode(this.sex);
        hash = 79 * hash + this.life;
        return hash;
    }

}
