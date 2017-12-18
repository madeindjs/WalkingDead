package population;

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

        for (Human human : humans) {
            human.addYear();
            if (!human.isAlive()) {
                humansDied.add(human);
            }
        }

        for (Human human : humansDied) {
            humans.remove(human);
        }
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
