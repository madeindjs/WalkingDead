/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import population.Population;

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

            pop.addYear();
            pop.removeDiedPeople();
            frame.land.repaint();

            Thread.sleep(200);
        }
    }
}
