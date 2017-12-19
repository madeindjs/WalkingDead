/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

public class Zombie implements Fighter {

    public int strenght = 30;
    public int life = 20;

    public Zombie() {
        // todo generate random skills
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

}
