package population;

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

    static final int AGE_MAJORITY = 18;
    static final int CHANCES_TO_HAVE_CHILD = 5;
    static final int RAPIDITY = 2;

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

    @Override
    public void move() {
        super.move();
        addYear();
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

}
