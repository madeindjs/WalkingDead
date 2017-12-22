package population;

import junit.framework.TestCase;

public class HumanTest extends TestCase {

    /**
     * Assert that human are added to instances on creation
     */
    public void testHuman() {
        Human human = new Human();
        assertEquals(1, Human.getInstances().size());
        assertTrue(human.equals(Human.getInstances().firstElement()));
    }

    /**
     * Assert that human is removed from instances on die & a zombie is born
     */
    public void testDie() {
        Human human = new Human();
        assertEquals(1, Human.getInstances().size());
        human.die();
        assertEquals(0, Human.getInstances().size());
        assertEquals(1, Zombie.getInstances().size());
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
        assertEquals(4, h1.x);
        assertEquals(4, h1.y);
    }
}
