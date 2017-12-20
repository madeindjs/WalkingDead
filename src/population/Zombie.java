/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import java.util.Random;

public class Zombie extends Point implements Fighter, Walker {

    public int strenght = 30;
    public int life = 20;

    static final int RAPIDITY = 1;

    public Zombie(Human human) {
        x = human.x;
        y = human.y;
    }

    @Override
    public void fight(Human human) {
        if (human.isMajor() && isAlive()) {
            human.life = human.life - strenght;
            human.fight(this);
        } else {
            human.life = 0;
        }
    }

    @Override
    public boolean isAlive() {
        return life > 0;
    }

    @Override
    public void fight(Zombie zombie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move() {
        Random rand = new Random();
        int direction = rand.nextBoolean() ? 1 : -1;

        int _x = x + (RAPIDITY * direction);
        int _y = y + (RAPIDITY * direction);

        setCoordinates(_x, _y);
    }

}
