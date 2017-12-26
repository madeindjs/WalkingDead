package population;

import java.util.Vector;
import junit.framework.TestCase;

public class HumanTest extends TestCase {

    /**
     * Remove all humans before each tests
     */
    @Override
    public void setUp() {
        Vector<Human> humansDied = new Vector();
        for (Human human : Human.getInstances()) {
            humansDied.add(human);
        }
        humansDied.forEach((e) -> e.die());
    }

    /**
     * Assert that human are added to instances on creation
     */
    public void testHuman() {
        int initialHumanCount = Human.count();
        Human human = new Human();
        assertEquals(initialHumanCount + 1, Human.count());
    }

    /**
     * Assert that human is removed from instances on die & a zombie is born
     */
    public void testDie() {
        int initialHumanCount = Human.count();
        int initialZombieCount = Zombie.count();
        Human human = new Human();
        assertEquals("Human was not created", initialHumanCount + 1, Human.count());
        human.die();
        assertEquals(initialHumanCount, Human.count());
        assertEquals(initialZombieCount + 1, Zombie.count());
    }

    /**
     * Assert that coordinates are controled when change & can't be outside of
     * plan
     */
    public void testSetCoordinates() {
        Human human = new Human();
        int oldX = human.x;
        int oldY = human.y;

        // set too high position & assert nothing change
        human.setCoordinates(10000, 10000);
        assertEquals(oldY, human.y);
        assertEquals(oldX, human.x);

        // set negative position & assert nothing change
        human.setCoordinates(-1, -1);
        assertEquals(oldY, human.y);
        assertEquals(oldX, human.x);

        // set correct position & assert something change
        human.setCoordinates(1, 1);
        assertEquals(1, human.y);
        assertEquals(1, human.x);
    }

    /**
     * Create to human & move one from to second. Assert the first moved at
     * excepted coordinates
     */
    public void testMoveTo() {
        Human h1 = new Human();
        Human h2 = new Human();
        h1.setCoordinates(0, 0);
        h2.setCoordinates(10, 10);

        h1.moveTo(h2);
        assertEquals(1, h1.x);
        assertEquals(1, h1.y);
    }

    public void testEquals() {
        Human h1 = new Human();
        assertTrue(h1.equals(h1));
    }

    public void testCanISeeHim() {
        Human h1 = new Human();
        Human h2 = new Human();
        Human h3 = new Human();
        h1.setCoordinates(1, 1);
        h2.setCoordinates(5, 5);
        h3.setCoordinates(100, 100);

        assertTrue(h1.canISeeHim(h2));
        assertFalse(h1.canISeeHim(h3));
    }

    public void testFindClosestHuman() {
        Human h1 = new Human();
        Human h2 = new Human();
        Human h3 = new Human();
        h1.setCoordinates(0, 0);
        h2.setCoordinates(5, 5);
        h3.setCoordinates(6, 6);

        assertSame(h2, h1.findClosestHuman());
    }

    public void testIsAlive() {
        Human h = new Human(1000);
        assertFalse(h.isAlive());
    }
}
