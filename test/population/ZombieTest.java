package population;

import junit.framework.TestCase;

public class ZombieTest extends TestCase {

    /**
     * Assert that human are added to instances on creation
     */
    public void testZombie() {
        int initialCount = Zombie.count();
        Human human = new Human();
        Zombie zombie = new Zombie(human);
        assertEquals(initialCount + 1, Zombie.count());
    }

    public void testDie() {
        int initialCount = Zombie.count();
        Human human = new Human();
        Zombie zombie = new Zombie(human);
        assertEquals("Zombie was not created", initialCount + 1, Zombie.count());
        zombie.die();
        assertEquals(initialCount, Zombie.count());
    }

    public void testFight() {
        // minor human
        Human human = new Human();
        Zombie zombie = new Zombie(human);

        zombie.fight(human);
        assertFalse(human.isAlive());
    }

}
