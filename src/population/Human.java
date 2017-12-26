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

    private double age = 0;
    private double maxAge;
    private Sex sex;

    public double life = 100;

    public static final int AGE_MAJORITY = 18;
    public static final int CHANCES_TO_HAVE_CHILD = 10;
    public static final int RAPIDITY = 2;
    public static final int MAX_AGE = 120;

    /**
     * Perimeter who human can have sex with another
     */
    private static final int SEX_AREA = 1;

    public static Vector<Human> getInstances() {
        return instances;
    }

    /**
     * @return count of humans
     */
    public static int count() {
        return instances.size();
    }

    public static Human getInstance(int index) {
        return instances.get(index);
    }

    /**
     * Create a human with a random sex & random max age
     */
    public Human() {
        Random rand = new Random();
        maxAge = (double) rand.nextInt(MAX_AGE);
        sex = rand.nextBoolean() ? Sex.Male : Sex.Female;

        setRandomPosition();
        instances.add(this);
    }

    public Human(int _age, Sex _sex) {
        this(_age);
        this.sex = _sex;
    }

    public Human(int _age) {
        this();
        age = _age;
        // set max age between current age & max age
        Random rand = new Random();
        maxAge = (double) rand.nextInt(MAX_AGE - (int) age) + age;
    }

    public void setAge(int _age) {
        age = _age;
    }

    public Baby haveSex(Human otherHuman) throws Exception {
        if (!canHaveSex(otherHuman)) {
            throw new Exception("Can't have sex");
        }

        return new Baby(this);
    }

    public void addLoop() {
        age += Population.LOOP_VALUE;
    }

    public boolean isMajor() {
        return age > (float) AGE_MAJORITY;
    }

    public boolean canHaveSex() {
        return isMajor() && isAlive();
    }

    /**
     * Check if human can have sex with given human
     *
     * @param human
     * @return
     */
    public boolean canHaveSex(Human human) {
        // check all basics rules
        if (!canHaveSex() || !human.canHaveSex() || sex == human.sex) {
            return false;
        }

        // check area to have sex
        return Math.abs(human.x - x) <= SEX_AREA && Math.abs(human.y - y) <= SEX_AREA;
    }

    @Override

    public boolean isAlive() {
        return age < maxAge && life > 0.0;
    }

    @Override
    public void fight(Human human) {
        throw new UnsupportedOperationException("Human can't fight against human");
    }

    @Override
    public void fight(Zombie zombie) {
        if (isMajor() && isAlive()) {
            zombie.setDied();
        } else {
            this.setDied();
        }
    }

    /**
     * Human will try to: move next to closest human if he can, move randomly or
     * have sex if closest human is in `SEX_PERIMETER`
     */
    @Override
    public void move() {
        addLoop();

        try {
            // try to escape from closest zombie .. if found
            Zombie closestZombie = findClosestZombie();
            escape(closestZombie);
            return;
        } catch (Exception e) {
            // no zombie found :)
        }

        // try to find the closest human
        Human closestHuman;
        try {
            closestHuman = findClosestHuman();
        } catch (Exception e) {
            // move randomly if exception are found
            super.move();
            return;
        }

        if (canHaveSex(closestHuman)) {
            // if human can have sex with closest human, he have sex
            try {
                haveSex(closestHuman);
            } catch (Exception e) {
                moveTo(closestHuman);
            }
        } else {
            // if he can't have sex, he move to the closest human
            moveTo(closestHuman);
        }
    }

    /**
     * Escape from given zombie.
     */
    public void escape(Zombie zombie) {
        int _x = x;
        int _y = y;

        if (zombie.x > _x) {
            _x -= RAPIDITY;
        } else if (zombie.x != _x) {
            _x += RAPIDITY;
        }

        if (zombie.y > _y) {
            _y -= RAPIDITY;
        } else if (zombie.y != _y) {
            _y += RAPIDITY;
        }

        setCoordinates(_x, _y);
    }

    @Override
    public void setDied() {
        life = 0;
    }

    /**
     * Remove human from instances
     *
     * @todo try to free memory
     */
    @Override
    public void die() {
        life = 0;
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

        return human.x == x && human.y == y && human.age == age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.age) ^ (Double.doubleToLongBits(this.age) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.maxAge) ^ (Double.doubleToLongBits(this.maxAge) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.sex);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.life) ^ (Double.doubleToLongBits(this.life) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Human: %s/%s age @ %s", age, maxAge, getCoordinates());
    }
}
