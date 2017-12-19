/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

public class Zombie {

    private int strenght = 30;
    private int life = 50;

    public Zombie() {
        // todo generate random skills
    }

    public void fight(Human human) {
        if (human.isMajor()) {

            human.life -= strenght;
            // human fight again
            if (human.isAlive()) {
                life -= human.life;
            }
        } else {
            human.life = 0;
        }

    }

    public boolean isAlive() {
        return life > 0;
    }

}
