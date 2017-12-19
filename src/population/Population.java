package population;

import java.util.Random;
import java.util.Vector;

public class Population {

    private Vector<Human> humans = new Vector();
    private Vector<Zombie> zombies = new Vector();
    private int year = 0;

    /**
     * Create a population with 10 humans
     */
    public Population() {
        for (int i = 0; i < 10; i++) {
            humans.add(new Human());
        }
    }

    /**
     * - zombies fight again humans - remove died zombies & humans - humans try
     * Add one year to eac humans & check you remains alive
     */
    public void addYear() {
        Random rand = new Random();

        Vector<Human> babies = new Vector();

        // Add one year to each human
        for (Human human : humans) {
            human.addYear();
        }

        int count = humans.size();

        // zombies try to kill humans
        for (Zombie zombie : zombies) {
            int index = rand.nextInt(count);
            zombie.fight(humans.get(index));
        }

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
