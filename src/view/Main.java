/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import population.Human;
import population.Population;
import population.Zombie;

/**
 *
 * @author arousseau
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Frame frame = new Frame();
        Population pop = new Population();
        Land land = new Land(pop);
        frame.setLand(land);

        while (pop.countHumans() > 0) {
            for (Human human : Human.getInstances()) {
                human.move();
            }
            for (Zombie zombie : pop.getZombie()) {
                zombie.move();
            }
            frame.land.repaint();

            pop.removeDiedPeople();

            Thread.sleep(100);
        }
    }
}
