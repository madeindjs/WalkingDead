package population;

import java.util.Random;
import java.util.Vector;

public class Population {

    private int year = 0;
    static final int NUMBER_START = 100;

    /**
     * Create a population with 10 humans
     */
    public Population() {
        for (int i = 0; i < NUMBER_START; i++) {
            new Human(Human.AGE_MAJORITY);
        }
    }

    public int countHumans() {
        return Human.getInstances().size();
    }

    /**
     * 1. add year to each humans
     *
     * 2. zombies fight again humans
     *
     * 3. remove died zombies & humans
     *
     * 4. humans have sex
     */
    public void addYear() {
        Random rand = new Random();

        Vector<Human> babies = new Vector();

        // 1. add year to each humans
        for (Human human : Human.getInstances()) {
            human.addYear();
        }

        int count = countHumans();

        // 2. zombies fight again humans
        for (Zombie zombie : Zombie.getInstances()) {
            int index = rand.nextInt(count);
            zombie.fight(Human.getInstance(index));
        }

        // 3. remove died zombies & humans
        removeDiedPeople();

        // each human try to have sex ... if he can
        for (Human human : Human.getInstances()) {
            int index = rand.nextInt(count);
            try {
                Human baby = human.haveSex(Human.getInstance(index));
                babies.add(baby);
            } catch (Exception e) {
            }
        }

        year++;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] Population: %s humans & %s zombies",
                year,
                Human.getInstances().size(),
                Zombie.getInstances().size()
        );
    }

    /**
     * Check all instances of humans & zombies & remove died instances
     */
    public void removeDiedPeople() {

        Vector<Human> humansDied = new Vector();
        for (Human human : Human.getInstances()) {
            if (!human.isAlive()) {
                humansDied.add(human);
            }
        }
        humansDied.forEach((e) -> e.die());

        Vector<Zombie> zombieDied = new Vector();
        for (Zombie zombie : Zombie.getInstances()) {
            if (!zombie.isAlive()) {
                zombieDied.add(zombie);
            }
        }
        zombieDied.forEach((e) -> e.die());

    }
}
