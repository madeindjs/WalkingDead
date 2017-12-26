/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import java.util.Vector;

public class Zombie extends Entity implements Fighter, Walker, Living {

    public int strenght = 30;
    public int life = 20;
    /**
     * Perimeter who entity can figt against entity
     */
    private static final int FIGHT_AREA = 2;

    private static Vector<Zombie> instances = new Vector();

    public static Vector<Zombie> getInstances() {
        return instances;
    }

    /**
     * @return count of humans
     */
    public static int count() {
        return instances.size();
    }

    public Zombie(Human human) {
        this(human.x, human.y);
    }

    public Zombie(int _x, int _y) {
        x = _x;
        y = _y;
        instances.add(this);
    }

    @Override
    public void fight(Human human) {
        if (human.isMajor() && isAlive()) {
            human.life = human.life - strenght;

            if (human.isAlive()) {
                human.fight(this);
            } else {
                System.out.println("population.Zombie.fight()");
            }
        } else {
            human.setDied();
        }
    }

    @Override
    public boolean isAlive() {
        return life > 0.0;
    }

    @Override
    public void fight(Zombie zombie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void die() {
        life = 0;
        instances.remove(this);
    }

    /**
     * Human will try to: move next to closest human if he can, move randomly or
     * have sex if closest human is in `SEX_PERIMETER`
     */
    @Override
    public void move() {
        Human closestHuman;

        try {
            closestHuman = findClosestHuman();
        } catch (Exception e) {
            // move randomly if exception are found
            super.move();
            return;
        }

        if (canFight(closestHuman)) {
            fight(closestHuman);
        } else {
            moveTo(closestHuman);
        }
    }

    protected boolean canFight(Human human) {
        return Math.abs(human.x - x) <= FIGHT_AREA && Math.abs(human.y - y) <= FIGHT_AREA;
    }

    @Override
    public void setDied() {
        System.out.println("population.Zombie.setDied()");
        life = 0;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Zombie)) {
            return false;
        }
        Zombie zombie = (Zombie) object;

        return zombie.x == x && zombie.y == y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.strenght;
        hash = 29 * hash + this.life;
        return hash;
    }

}
