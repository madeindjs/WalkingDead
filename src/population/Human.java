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

    /**
     * Create a human with a random sex & random max age
     */
    public Human() {
        Random rand = new Random();

        maxAge = rand.nextInt(120);
        age = 0;
        sex = rand.nextBoolean() ? Sex.Male : Sex.Female;
    }

    public void addYear() {
        age++;
    }

    public boolean isAlive() {
        return age < maxAge;
    }

}
