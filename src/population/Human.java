package population;

import java.util.Random;

/**
 *
 * @author arousseau
 */
public class Human {

    private int age;
    private final int maxAge;
    private final Sex sex;

    public int life = 100;

    private Human mother;
    private Human father;

    static final int AGE_MAJORITY = 18;
    static final int CHANCES_TO_HAVE_CHILD = 12;

    /**
     * Create a human with a random sex & random max age
     */
    public Human() {
        Random rand = new Random();

        maxAge = rand.nextInt(120);
        age = 0;
        sex = rand.nextBoolean() ? Sex.Male : Sex.Female;
    }

    public Human(Human _mother, Human _father) {
        this();
        setFather(_father);
        setMother(_mother);
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
            if (this.sex == Sex.Male) {
                return new Human(this, otherHuman);
            } else {
                return new Human(otherHuman, this);
            }
        }

        throw new Exception("Do it again...");
    }

    public void addYear() {
        age++;
    }

    public boolean isAlive() {
        return age < maxAge && life > 0;
    }

    public boolean isMajor() {
        return age > AGE_MAJORITY;
    }

    public boolean canHaveSex() {
        return isMajor() && isAlive();
    }

}
