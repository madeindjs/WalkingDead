package population;

import java.util.Random;
import java.util.Vector;

public class Population {

    private final Vector<Human> humans = new Vector();
    private final Vector<Zombie> zombies = new Vector();
    private int year = 0;

    static final int NUMBER_START = 100;

    /**
     * Create a population with 10 humans
     */
    public Population() {
        for (int i = 0; i < NUMBER_START; i++) {
            humans.add(new Human(Human.AGE_MAJORITY));
        }
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
        for (Human human : humans) {
            human.addYear();
        }

        int count = humans.size();

        // 2. zombies fight again humans
        for (Zombie zombie : zombies) {
            int index = rand.nextInt(count);
            zombie.fight(humans.get(index));
        }

        // 3. remove died zombies & humans
        removeDiedPeople();

        // each human try to have sex ... if he can
        for (Human human : humans) {
            int index = rand.nextInt(count);
            try {
                Human baby = human.haveSex(humans.get(index));
                babies.add(baby);
            } catch (Exception e) {
            }
        }
        humans.addAll(babies);

        year++;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] Population: %s humans & %s zombies",
                year,
                humans.size(),
                zombies.size()
        );
    }

    protected void removeDiedPeople() {
        Vector<Human> humansDied = new Vector();

        zombies.removeIf((z) -> !z.isAlive());

        for (Human human : humans) {
            if (!human.isAlive()) {
                humansDied.add(human);
            }
        }

        for (Human human : humansDied) {
            humans.remove(human);
            zombies.add(new Zombie());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Population pop = new Population();

        while (pop.humans.size() > 0) {
            pop.addYear();
            System.out.println(pop);
        }
    }
}
