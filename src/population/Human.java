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
    private Sex sex;

    public int life = 100;

    public static final int AGE_MAJORITY = 18;
    public static final int CHANCES_TO_HAVE_CHILD = 10;
    public static final int RAPIDITY = 2;

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
        maxAge = rand.nextInt(120);
        age = 0;
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

    public void addYear() {
        age++;
    }

    public boolean isMajor() {
        return age > AGE_MAJORITY;
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
        return age < maxAge && life > 0;
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
     * Human will try to: move next to closest human if he can, move randomly or
     * have sex if closest human is in `SEX_PERIMETER`
     */
    @Override
    public void move() {
        addYear();
        Human closestHuman;

        try {
            closestHuman = findClosestHuman();
        } catch (Exception e) {
            // move randomly if exception are found
            super.move();
            return;
        }

        if (canHaveSex(closestHuman)) {
            try {
                haveSex(closestHuman);
            } catch (Exception e) {
                moveTo(closestHuman);
            }
        } else {
            moveTo(closestHuman);
        }
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
        hash = 79 * hash + this.age;
        hash = 79 * hash + this.maxAge;
        hash = 79 * hash + Objects.hashCode(this.sex);
        hash = 79 * hash + this.life;
        return hash;
    }

    public String toString() {
        return String.format("Human: %s/%s age @ %s", age, maxAge, getCoordinates());
    }

}
