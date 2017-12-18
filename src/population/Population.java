package population;

import java.util.Random;
import java.util.Vector;

public class Population {

    private Vector<Human> humans = new Vector();
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
     * Add one year to eac humans & check you remains alive
     */
    public void addYear() {
        Vector<Human> humansDied = new Vector();
        Vector<Human> babies = new Vector();

        for (Human human : humans) {
            human.addYear();
            if (!human.isAlive()) {
                humansDied.add(human);
            }
        }

        for (Human human : humansDied) {
            humans.remove(human);
        }

        Random rand = new Random();
        int count = humans.size();

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
        return String.format("[%s] Population: %s", year, humans.size());
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
