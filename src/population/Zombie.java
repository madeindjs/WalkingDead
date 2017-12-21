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

    private static Vector<Zombie> instances = new Vector();

    public static Vector<Zombie> getInstances() {
        return instances;
    }

    public Zombie(Human human) {
        x = human.x;
        y = human.y;
        instances.add(this);
    }

    @Override
    public void fight(Human human) {
        if (human.isMajor() && isAlive()) {
            human.life = human.life - strenght;

            if (human.isAlive()) {
                human.fight(this);
            }
        } else {
            human.die();
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void fight(Zombie zombie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void die() {
        instances.remove(this);
    }

}
