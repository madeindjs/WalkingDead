package population;

import java.util.Random;

/**
 *
 * @author arousseau
 */
public class Human implements Fighter {

    private int age;
    private final int maxAge;
    private final Sex sex;

    public int life = 100;

    private Human mother;
    private Human father;

    static final int AGE_MAJORITY = 18;
    static final int CHANCES_TO_HAVE_CHILD = 5;

    /**
     * Create a human with a random sex & random max age
     */
    public Human() {
        Random rand = new Random();

        maxAge = rand.nextInt(120);
        age = 0;
        sex = rand.nextBoolean() ? Sex.Male : Sex.Female;
    }

    public Human(int _age) {
        this();
        age = _age;
    }

    public void setAge(int _age) {
        age = _age;
    }

    public void setMother(Human _mother) {
        mother = _mother;
    }

    public void setFather(Human _father) {
        father = _father;
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
            zombie.life = 0;
        } else {
            this.life = 0;
        }
    }

}
